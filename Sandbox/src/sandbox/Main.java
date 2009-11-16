/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sandbox;

import com.csvreader.CsvReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sgnhp.entity.Tasks;
import ru.sgnhp.services.ITasksService;

/**
 *
 * @author 48han
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/ru/sgnhp/applicationContext.xml");
        ITasksService service = (ITasksService) ctx.getBean("tasksService");

        CsvReader reader = new CsvReader(new InputStreamReader(new FileInputStream("d:\\temp\\doc.csv"), "cp1251"));
        reader.setDelimiter(';');
        int counter = 1;
        while (reader.readRecord()) {
            Tasks task = new Tasks();
            task.setExternalCompany(reader.get(3));
            task.setExternalAssignee(reader.get(4));
            task.setInternalNumber(counter);
            task.setIncomingNumber(Integer.valueOf(reader.get(0).trim()).intValue());
            //DateFormat date = DateFormat.getDateInstance(DateFormat.SHORT);
            //task.setStartDate(date.parse(reader.get(1)));
            //task.setDueDate(date.parse(reader.get(5)));
            task.setDescription(reader.get(2));
            service.save(task);
            counter ++;
        }

        reader.close();
//        task.setUid(43);
//        task.setDescription("test!!!");
//        service.save(task);
//        FileReader fr = new FileReader("d:\\temp\\doc.csv");
//        BufferedReader br = new BufferedReader(fr);
//        String s = "";
//        while ((s = br.readLine()) != null) {
//            String UTF8Str = new String(s.getBytes(), "UTF-8");
//            String[] a = UTF8Str.split(";");
//            System.out.println(a[0] + " : " + a[2]);
//            Tasks task = new Tasks();
//            task.setExternalCompany(a[3]);
//            task.setExternalAssignee(a[4]);
//            task.setIncomingNumber(Integer.valueOf(a[0].trim()).intValue());
//            //DateFormat date = DateFormat.getDateInstance(DateFormat.SHORT);
//            //task.setStartDate(date.parse(a[1]));
//            //task.setDueDate(date.parse(a[5]));
//            task.setDescription(a[2]);
//            //service.save(task);
//        }
//
//        br.close();
//        fr.close();

//        Groups entity = new Groups();
//        entity.setId(1L);
//        entity.setName("Администраторы");
//        entity.setDescription("Группа администраторов");
        //service.saveEntity(entity);
//        service.save(entity);
    }
}
