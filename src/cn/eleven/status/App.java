package cn.eleven.status;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;


/**
 * Created by User on 2017/6/21.
 */
public class App {

    private  static SessionFactory sessionFactory = new Configuration()
            .configure()
            .addClass(User.class)
            .buildSessionFactory();

    //save()：把临时状态转化为持久化状态(交给session管理)
    //会生成：insert。。
    @Test
    public  void save(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        User user = new User();//临时状态
        user.setName("张三");
        session.save(user);//持久化状态

       // user.setName("李四");

        tx.commit();
        session.close();

        user.setName("李四");
        System.out.println("name="+user.getName());//游离状态
    }

    //update()：把游离状态转化为持久化状态
    //会生成：update。。。
    @Test
    public  void update(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        User user = session.get(User.class,1);
        System.out.println("name="+user.getName());//持久化状态

//        user.setName("王五22");
//        session.flush();//刷到数据库
//        session.clear();//游离状态，清除session中的所有对象

        session.evict(user); //清除一个指定对想
        user.setName("王五2");
        session.update(user);
        session.flush();//刷到数据库

        tx.commit();
        session.close();
    }

    //saveOrUpdate()：把临时或者游离状态转化为持久化状态
    //会生成：update。。。或者insert into。。
    //本方法根据id判断是什么状态，如果id是原始值（对象是null，原始类型数字为0），就是临时状态
    @Test
    public  void saveOrUpdate(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        User user = new User();
        user.setId(3);//自己生成一个游离状态的对象
        user.setName("XXX");

        session.saveOrUpdate(user);

        tx.commit();
        session.close();
    }

    //delete()：把游离或者是持久状态转化为删除状态
    //会生成：delete 。。。
    @Test
    public  void delete(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

       // User user = session.get(User.class,4);//持久化状态
        User user = new User();
        user.setId(3);//游离状态
        session.delete(user);
        session.flush();
        System.out.println("---------");
        tx.commit();
        session.close();
    }

    //get()：把游离或者是持久状态转化为伤处状态
    //会生成：select 。。。
    //如果对象数据不存在就返回null，会马上执行SQL语句
    @Test
    public  void get(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        User user = session.get(User.class,4);//持久化状态

        System.out.println("---------");
        tx.commit();
        session.close();
    }

    //load()：获取数据，持久化状态，返回的是一个代理对象，要求类不能是fianl，否则不能生成子类代理，不能使用懒加载
    //懒加载失效：1、实体类写成fianl，2、hbm.xml中lazy=“false”
    //不会马上执行sql语句，而是在第一次使用非id或者class属性时执行
    //结果不存在抛出异常
    @Test
    public  void load(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        User user = session.load(User.class,5);//持久化状态

        System.out.println("---------");
        tx.commit();
        session.close();
    }

}
