package ru.sgnhp.sandbox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sgnhp.entity.FileBean;
import ru.sgnhp.entity.TaskBean;
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

    static void makeRepository(ClassPathXmlApplicationContext ctx, String repositoryPath) throws FileNotFoundException, IOException, ParseException {
        ITaskManagerService taskManagerService = (ITaskManagerService) ctx.getBean("taskManagerService");
        IUploadManagerService uploadManagerService = (IUploadManagerService) ctx.getBean("uploadManagerService");
        List<TaskBean> taskBeans = taskManagerService.getAll();
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
        for (TaskBean taskBean : taskBeans) {
            String path = repositoryPath + "\\" + getYearFromDate(taskBean.getStartDate())
                    + "\\" + getMonthFromDate(taskBean.getStartDate())
                    + "\\" + fmt.format(taskBean.getStartDate()) + "\\"
                    + taskBean.getUid().toString();
            List<FileBean> fileBeans = uploadManagerService.getFilesByTaskUid(taskBean);
            if (fileBeans == null) {
                continue;
            }

            File directory = new File(path);
            if (!directory.exists()) {
                System.out.println("creating directory: " + path);
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
                    FileOutputStream fos = new FileOutputStream(path + "\\" + filename);
                    fos.write(fileBean.getBlobField());//1645
                    fos.close();
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        makeRepository(ctx, "d:\\temp\\repository\\tasks");
    }
}
