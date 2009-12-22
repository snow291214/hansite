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
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sgnhp.entity.OutgoingFileBean;
import ru.sgnhp.entity.OutgoingMailBean;
import ru.sgnhp.services.IOutgoingFileService;
import ru.sgnhp.services.IOutgoingMailService;

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
        IOutgoingFileService fileService = (IOutgoingFileService) ctx.getBean("outgoingFileService");
        IOutgoingMailService outgoingMailService = (IOutgoingMailService) ctx.getBean("outgoingMailService");

        CsvReader reader = new CsvReader(new InputStreamReader(new FileInputStream("d:\\temp\\out.csv"), "cp1251"));
        //CsvReader reader = new CsvReader(new InputStreamReader(new FileInputStream("/media/win_d/temp/doc1.csv"), "cp1251"));
        reader.setDelimiter(';');
        while (reader.readRecord()) {

            OutgoingMailBean outgoingMailBean = new OutgoingMailBean();
            outgoingMailBean.setOutgoingNumber(Long.parseLong(reader.get(0)));
            DateFormat date = DateFormat.getDateInstance(DateFormat.SHORT);
            outgoingMailBean.setOutgoingDate(date.parse(reader.get(1)));
            outgoingMailBean.setDescription(reader.get(2));
            outgoingMailBean.setReceiverCompany(reader.get(3));
            outgoingMailBean.setReceiverName(reader.get(4));
            outgoingMailBean.setResponsibleName(reader.get(5));
            if (!reader.get(6).equals("")) {
                outgoingMailBean.setDueDate(date.parse(reader.get(6)));
            }
            outgoingMailBean.setDocumentumNumber(reader.get(7));
            outgoingMailService.save(outgoingMailBean);

            String path = "D:\\temp\\out\\" + reader.get(0) + ".pdf";
            boolean exists = (new File(path)).exists();
            if (exists) {
                OutgoingFileBean outgoingFileBean = new OutgoingFileBean();
                outgoingFileBean.setBlobField(getBytesFromFile(new File(path)));
                outgoingFileBean.setFileName(reader.get(0) + ".pdf");
                outgoingFileBean.setOutgoingMailBean(outgoingMailBean);
                fileService.save(outgoingFileBean);
            }
        }
    }
}
