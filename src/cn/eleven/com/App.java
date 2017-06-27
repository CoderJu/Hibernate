package cn.eleven.com;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;


/**
 * Created by User on 2017/6/21.
 */
public class App {

    private  static SessionFactory sessionFactory;
    static {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        sessionFactory = cfg.buildSessionFactory();
    }


    public  void Save(){
        User user = new User();
        user.setName("张三");

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        session.close();
    }

    public void Get(){
        Session session = null;
        User user = session.get(User.class,1);
        System.out.print("user="+user);
    }

}
