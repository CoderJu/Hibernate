package cn.eleven.com;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.Date;


/**
 * Created by User on 2017/6/21.
 */
public class AppTest {
    private  static SessionFactory sessionFactory;
    static {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        sessionFactory = cfg.buildSessionFactory();
    }
    @Test
    public void save() throws Exception {
        User user = new User();
        user.setName("张三");
        user.setBrithday(new Date());
        user.setAge(18);
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        session.close();
    }

    @Test
    public void get() throws Exception {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class,1);
        System.out.print("user="+user.getName());
        session.close();
    }

}