package cn.eleven.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * Created by User on 2017/6/25.
 */
public class SessionUtil {

    private static SessionFactory sessionFactory;

    static {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        sessionFactory = cfg.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }


    public static Session openSessionFactory(){
        return sessionFactory.openSession();
    }

}
