/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sandbox;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sgnhp.entity.Groups;
import ru.sgnhp.services.IGroupsService;

/**
 *
 * @author 48han
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/ru/sgnhp/applicationContext.xml");
        IGroupsService service = (IGroupsService) ctx.getBean("groupsService");
        Groups entity = new Groups();
        entity.setId(1L);
        entity.setName("Администраторы");
        entity.setDescription("Группа администраторов");
        //service.saveEntity(entity);
        service.save(entity);
    }
}
