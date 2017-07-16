package cn.eleven.Inherit2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;


/**
 * Created by User on 2017/6/29.
 */
public class App {

    private  static SessionFactory sessionFactory = new Configuration()
                                .configure().
                    addClass(Article.class).
                    buildSessionFactory();

    @Test
    public  void save(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
          Article article = new Article();
          article.setTitle("这是一个article");

          Topic topic = new Topic();
          topic.setTitle("这是一个topic");

          Reply reply = new Reply();
          reply.setTitle("这是一个reply");


          session.save(article);
          session.save(topic);
          session.save(reply);


        }catch (Exception e){
            tx.rollback();
            throw e;
        }finally {
            tx.commit();
        }
        session.close();
    }


    @Test
    public  void get(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Article article = session.get(Article.class,1);
        Topic topic = session.get(Topic.class,2);
        Reply reply = session.get(Reply.class,3);

        System.out.println("article="+article);
        System.out.println("topic="+topic);
        System.out.println("reply="+reply);


        Article topic1 = session.get(Article.class,2);
        Article reply1 = session.get(Article.class,3);

        System.out.println("topic1="+topic1);
        System.out.println("reply1="+reply1);

        tx.commit();
        session.close();
    }
}
