/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sandbox;

import com.csvreader.CsvReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sgnhp.entity.DocumentBean;
import ru.sgnhp.entity.DocumentFileBean;
import ru.sgnhp.entity.FileBean;
import ru.sgnhp.entity.TaskBean;
import ru.sgnhp.services.IDocumentFileService;
import ru.sgnhp.services.IDocumentService;
import ru.sgnhp.services.IDocumentTypeService;
import ru.sgnhp.services.ITaskManagerService;
import ru.sgnhp.services.IUserManagerService;

/**
 *
 * @author 48han
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    byte[] getBytesFromFile(File file) throws FileNotFoundException, IOException {
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

    private static String getYearFromDate(Date date) {
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
        return simpleDateformat.format(date);
    }

    private static String getMonthFromDate(Date date) {
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("MM");
        return simpleDateformat.format(date);
    }

    void writeOrders(ClassPathXmlApplicationContext ctx) throws FileNotFoundException, IOException, ParseException {
        String typeAndYear = "P2010";
        Long documentType = 2L;

        IDocumentService documentService = (IDocumentService) ctx.getBean("documentService");
        IDocumentFileService documentFileService = (IDocumentFileService) ctx.getBean("documentFileService");
        IDocumentTypeService documentTypeService = (IDocumentTypeService) ctx.getBean("documentTypeService");
        IUserManagerService userManagerService = (IUserManagerService) ctx.getBean("userManagerService");

        CsvReader reader = new CsvReader(new InputStreamReader(new FileInputStream("c:\\temp\\" + typeAndYear + ".csv"), "cp1251"));
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
            String path = "C:\\temp\\" + typeAndYear + "\\" + reader.get(0) + ".pdf";
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

    static void makeRepository(ClassPathXmlApplicationContext ctx, String repositoryPath) throws FileNotFoundException, IOException {
        ITaskManagerService taskManagerService = (ITaskManagerService) ctx.getBean("taskManagerService");
        SessionFactory sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
        //ITaskDao taskDao = (ITaskDao)ctx.getBean("taskDao");
        //IUploadManagerService uploadManagerService = (IUploadManagerService)ctx.getBean("uploadManagerService");
        //List<TaskBean> taskBeans = taskManagerService.getAll();
        List<TaskBean> taskBeans = sessionFactory.openSession().getNamedQuery("TaskBean.findAll").list();
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
        for (TaskBean taskBean : taskBeans) {
            String path = repositoryPath + "\\" + getYearFromDate(taskBean.getStartDate())
                    + "\\" + getMonthFromDate(taskBean.getStartDate())
                    + "\\" + fmt.format(taskBean.getStartDate()) + "\\"
                    + taskBean.getUid().toString();
            File directory = new File(path);
            if (!directory.exists()) {
                System.out.println("creating directory: " + path);
                directory.mkdirs();
            }

            Set fileBeans = taskBean.getFilesSet();
            for (Object fileBean : fileBeans) {
                String filename = "";
                if (!((FileBean) fileBean).getFileName().contains(".")) {
                    filename = ((FileBean) fileBean).getFileName() + ".pdf";
                } else {
                    filename = ((FileBean) fileBean).getFileName();
                }
                FileOutputStream fos = new FileOutputStream(path + "\\" + filename);
                if (((FileBean) fileBean).getBlobField() != null) {
                    fos.write(((FileBean) fileBean).getBlobField());
                }
                fos.close();
                ((FileBean)fileBean).setBlobField(null);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/ru/sgnhp/applicationContext.xml");
        makeRepository(ctx, "c:\\temp\\repository\\tasks");
    }
}
