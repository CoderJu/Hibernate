package cn.eleven.session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;




public class App {

    private  static SessionFactory sessionFactory = new Configuration()
            .configure()
            .addClass(Person.class).addClass(IdCard.class)
            .buildSessionFactory();


    @Test
    public  void save(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Person person = new Person();
        person.setName("张三");

        IdCard idCard = new IdCard();
        idCard.setNumber("1000000010101X");

        person.setIdCard(idCard);
        idCard.setPerson(person);

        session.save(person);
        session.save(idCard);

        tx.commit();
        session.close();
    }



    @Test
    public  void get(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Person person = session.get(Person.class,1);
        IdCard idCard = session.get(IdCard.class,1);
        System.out.println("person="+person.getName()+"||idcard="+person.getIdCard().getNumber());
        System.out.println("idCard="+idCard.getNumber());
        tx.commit();
        session.close();
    }



    @Test
    public  void Remove(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        //有外键方解除关系可以
        //IdCard idCard = session.get(IdCard.class,1);
        //idCard.setPerson(null);

        //无外键方解除关系不可以
        Person person = session.get(Person.class,1);
        person.setIdCard(null);
        tx.commit();
        session.close();
    }


    @Test
    public  void delete(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        IdCard idCard = session.get(IdCard.class,2);
        session.delete(idCard);

        //Person person = session.get(Person.class,1);
        //session.delete(person);

        tx.commit();
        session.close();
    }

}
