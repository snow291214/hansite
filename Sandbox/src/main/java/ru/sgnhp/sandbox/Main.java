package ru.sgnhp.sandbox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sgnhp.entity.FileBean;
import ru.sgnhp.entity.OutgoingFileBean;
import ru.sgnhp.entity.OutgoingMailBean;
import ru.sgnhp.entity.TaskBean;
import ru.sgnhp.services.IOutgoingFileService;
import ru.sgnhp.services.IOutgoingMailService;
import ru.sgnhp.services.ITaskManagerService;
import ru.sgnhp.services.IUploadManagerService;

public class Main {

    private static String getYearFromDate(Date date) {
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
        return simpleDateformat.format(date);
    }

    private static String getMonthFromDate(Date date) {
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("MM");
        return simpleDateformat.format(date);
    }

    private static void makeTaskRepository(ClassPathXmlApplicationContext ctx, String repositoryPath) throws FileNotFoundException, IOException, ParseException {
        ITaskManagerService taskManagerService = (ITaskManagerService) ctx.getBean("taskManagerService");
        IUploadManagerService uploadManagerService = (IUploadManagerService) ctx.getBean("uploadManagerService");
        List<TaskBean> taskBeans = taskManagerService.getAll();
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
        for (TaskBean taskBean : taskBeans) {
//            String path = "\\" + getYearFromDate(taskBean.getStartDate())
//                    + "\\" + getMonthFromDate(taskBean.getStartDate())
//                    + "\\" + fmt.format(taskBean.getStartDate()) + "\\"
//                    + taskBean.getUid().toString();
            String path = "/TaskFiles/" + getYearFromDate(taskBean.getStartDate())
                    + "/" + getMonthFromDate(taskBean.getStartDate())
                    + "/" + fmt.format(taskBean.getStartDate()) + "/"
                    + taskBean.getUid().toString();
            List<FileBean> fileBeans = uploadManagerService.getFilesByTaskUid(taskBean);
            if (fileBeans == null) {
                continue;
            }

            File directory = new File(repositoryPath + path);
            if (!directory.exists()) {
                System.out.println("creating directory: " + repositoryPath + path);
                directory.mkdirs();
            }

            for (FileBean fileBean : fileBeans) {

                String filename = "";
                if (!fileBean.getFileName().contains(".")) {
                    filename = fileBean.getFileName() + ".pdf";
                } else {
                    filename = fileBean.getFileName();
                }
                if (fileBean.getBlobField() != null) {
                    FileOutputStream fos = new FileOutputStream(repositoryPath + path + "/" + filename);
                    fos.write(fileBean.getBlobField());//1645
                    fos.close();
                }
                fileBean.setFilePath(path + "/" + filename);
                uploadManagerService.save(fileBean);
            }
        }
        taskBeans = null;
    }

    private static void makeOutgoingMailRepository(ClassPathXmlApplicationContext ctx, String repositoryPath) throws FileNotFoundException, IOException {
        IOutgoingMailService outgoingMailService = (IOutgoingMailService) ctx.getBean("outgoingMailService");
        IOutgoingFileService outgoingFileService = (IOutgoingFileService) ctx.getBean("outgoingFileService");
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
        List<OutgoingMailBean> outgoingMailBeans = outgoingMailService.getAll();
        Logger logger = Logger.getRootLogger();
        for (OutgoingMailBean outgoingMailBean : outgoingMailBeans) {
//            String path = "\\" + getYearFromDate(outgoingMailBean.getOutgoingDate())
//                    + "\\" + getMonthFromDate(outgoingMailBean.getOutgoingDate())
//                    + "\\" + fmt.format(outgoingMailBean.getOutgoingDate()) + "\\"
//                    + outgoingMailBean.getUid().toString();
            String path = "/OutgoingMailFiles/" + getYearFromDate(outgoingMailBean.getOutgoingDate())
                    + "/" + getMonthFromDate(outgoingMailBean.getOutgoingDate())
                    + "/" + fmt.format(outgoingMailBean.getOutgoingDate()) + "/"
                    + outgoingMailBean.getUid().toString();
            
            List<OutgoingFileBean> fileBeans = outgoingFileService.getFilesByOutgoingMail(outgoingMailBean);
            if (fileBeans == null) {
                continue;
            }

            File directory = new File(repositoryPath + path);
            if (!directory.exists()) {
                logger.info("creating directory: " + repositoryPath + path);
                directory.mkdirs();
            }

            for (OutgoingFileBean fileBean : fileBeans) {

                String filename = "";
                if (!fileBean.getFileName().contains(".")) {
                    filename = fileBean.getFileName() + ".pdf";
                } else {
                    filename = fileBean.getFileName();
                }
                if (fileBean.getBlobField() != null) {
                    FileOutputStream fos = new FileOutputStream(repositoryPath + path + "/" + filename);
                    fos.write(fileBean.getBlobField());//1645
                    fos.close();
                }
                fileBean.setFilePath(path + "/" + filename);
                outgoingFileService.save(fileBean);
            }
        }
        outgoingMailBeans = null;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        makeTaskRepository(ctx, "/home/alexey/repository");
        makeOutgoingMailRepository(ctx, "/home/alexey/repository");
    }
}
