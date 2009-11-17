/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sandbox;

import com.csvreader.CsvReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sgnhp.entity.Files;
import ru.sgnhp.entity.Tasks;
import ru.sgnhp.services.IFilesService;
import ru.sgnhp.services.ITasksService;

/**
 *
 * @author 48han
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    private static byte[] getBytesFromFile(File file) throws FileNotFoundException, IOException {
        InputStream is = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/ru/sgnhp/applicationContext.xml");
        ITasksService tasksService = (ITasksService) ctx.getBean("tasksService");
        IFilesService filesService = (IFilesService) ctx.getBean("filesService");

        //CsvReader reader = new CsvReader(new InputStreamReader(new FileInputStream("d:\\temp\\doc1.csv"), "cp1251"));
        CsvReader reader = new CsvReader(new InputStreamReader(new FileInputStream("/media/win_d/temp/doc1.csv"), "cp1251"));
        reader.setDelimiter(';');
        int counter = 1;
        while (reader.readRecord()) {

            Files file = new Files();
            boolean exists = (new File("/media/win_d/temp/in/" + reader.get(0) + ".pdf")).exists();
            if (exists) {
                //file.setBlobField(getBytesFromFile(new File("D:\\temp\\in\\" + reader.get(0) + ".pdf")));
                file.setBlobField(getBytesFromFile(new File("/media/win_d/temp/in/" + reader.get(0) + ".pdf")));
            }
            file.setFileName(reader.get(0) + ".pdf");
            //ArrayList<Files> files = new ArrayList<Files>();
            //files.add(file);
            
            Tasks task = new Tasks();
            task.setExternalCompany(reader.get(3));
            task.setExternalAssignee(reader.get(4));
            task.setInternalNumber(counter);
            task.setIncomingNumber(Integer.valueOf(reader.get(0).trim()).intValue());
            DateFormat date = DateFormat.getDateInstance(DateFormat.SHORT);
            task.setStartDate(date.parse(reader.get(1)));
            if (!reader.get(5).equals("")) {
                task.setDueDate(date.parse(reader.get(5)));
            }
            task.setDescription(reader.get(2));
            task.getFilesSet().add(file);
            file.setTaskUid(task);
            tasksService.save(task);
            filesService.save(file);
            
            counter++;
        }
        reader.close();
    }
}
