package cn.eleven.collection;

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
                                .configure().addClass(User.class).buildSessionFactory();

    @Test
    public  void save(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        User user = new User();
        user.setName("张三");
        user.getAddressSet().add("航中路");
        user.getAddressSet().add("七宝");
        user.getAddressList().add("安庆");
        user.getAddressList().add("上海");
        user.getAddressList().add("怀宁");
        user.setAddressArray(new String[]{"芜湖","合肥"});
        user.getAddressMap().put("公司","耀华路");
        user.getAddressMap().put("家","航中路");
        user.getAddressBag().add("XXX");
        user.getAddressBag().add("YYY");
        try {
            session.save(user);

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
        try {
            User user = session.get(User.class,4);
            System.out.println("name="+user.getName());
            System.out.println("set="+user.getAddressSet());
            System.out.println("list="+user.getAddressList());
            System.out.println("array="+user.getAddressArray());
            System.out.println("map="+user.getAddressMap().get("家"));
        }catch (Exception e){
            tx.rollback();
            throw e;
        }finally {
            tx.commit();
        }
        session.close();
    }
}
