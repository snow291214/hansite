/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sandbox;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sgnhp.services.IGenericService;

/**
 *
 * @author 48han
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/ru/sgnhp/applicationContext.xml");
        IGenericService service = (IGenericService) ctx.getBean("groupsService");

        FileReader fr = new FileReader("d:\\temp\\doc.csv");
        BufferedReader br = new BufferedReader(fr);
        String tmp;
        tmp = br.readLine(); // read first line of file.
        while (tmp != null) { // read a line until end of file.
            String[] a = tmp.split(";");
            System.out.println(a[0]+":"+a[2]);	// system out before tmp = br.readLine();
            tmp = br.readLine();
        }

        br.close();


//        Groups entity = new Groups();
//        entity.setId(1L);
//        entity.setName("Администраторы");
//        entity.setDescription("Группа администраторов");
        //service.saveEntity(entity);
//        service.save(entity);
    }
}
