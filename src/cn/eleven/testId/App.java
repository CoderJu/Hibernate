package cn.eleven.testId;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * Created by User on 2017/6/27.
 */
public class App {

    private  static SessionFactory sessionFactory = new Configuration()
            .configure()
            .addClass(User.class)
            .buildSessionFactory();

    @Test
    public void save(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = new User();
        //user.setId(1);
        user.setName("lisi");
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }
}
