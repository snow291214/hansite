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
import ru.sgnhp.entity.DocumentBean;
import ru.sgnhp.entity.DocumentFileBean;
import ru.sgnhp.services.IDocumentFileService;
import ru.sgnhp.services.IDocumentService;
import ru.sgnhp.services.IDocumentTypeService;
import ru.sgnhp.services.IUserManagerService;

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
        
        String typeAndYear = "P2010";
        Long documentType = 2L;

        IDocumentService documentService = (IDocumentService) ctx.getBean("documentService");
        IDocumentFileService documentFileService = (IDocumentFileService) ctx.getBean("documentFileService");
        IDocumentTypeService documentTypeService = (IDocumentTypeService) ctx.getBean("documentTypeService");
        IUserManagerService userManagerService = (IUserManagerService) ctx.getBean("userManagerService");

        CsvReader reader = new CsvReader(new InputStreamReader(new FileInputStream("c:\\temp\\"+typeAndYear+".csv"), "cp1251"));
        reader.setDelimiter(';');
        while (reader.readRecord()) {
            DocumentBean documentBean = new DocumentBean();
            DateFormat date = DateFormat.getDateInstance(DateFormat.SHORT);
            documentBean.setDocumentNumber(Integer.parseInt(reader.get(0)));
            documentBean.setDocumentDate(date.parse(reader.get(1)));
            documentBean.setDescription(reader.get(2));
            documentBean.setDocumentTypeBean(documentTypeService.get(documentType));
            documentBean.setContactPerson(userManagerService.get(Long.parseLong(reader.get(3))));
            documentBean.setControlPerson(userManagerService.get(Long.parseLong(reader.get(4))));
            documentBean = documentService.save(documentBean);
            String path = "C:\\temp\\"+typeAndYear+"\\" + reader.get(0) + ".pdf";
            boolean exists = (new File(path)).exists();
            if (exists) {
                DocumentFileBean documentFileBean = new DocumentFileBean();
                documentFileBean.setBlobField(getBytesFromFile(new File(path)));
                documentFileBean.setFileName(reader.get(0) + ".pdf");
                documentFileBean.setDocumentBean(documentBean);
                documentFileService.save(documentFileBean);
            }
            System.out.println(documentBean.getDocumentNumber());
        }
    }
}
