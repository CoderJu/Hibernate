package cn.eleven.qbc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


/**
 * Created by User on 2017/6/29.
 */
public class App {

    private  static SessionFactory sessionFactory = new Configuration()
                                .configure()
                                .addClass(Department.class)
                                .addClass(Employee.class)
                                .buildSessionFactory();

    @Test
    public  void save(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        for (int i=1;i<=10;i++) {
            Department department = new Department();
            department.setName("开发部_"+i);
            session.save(department);
        }

        for (int i=1;i<=20;i++) {
            Employee employee = new Employee();
            employee.setName("张三_"+i);
            session.save(employee);
        }

        tx.commit();
        session.close();
    }


    /**
     * QBC的方式查询：Query By Criteria
     *
     */
    @Test
    public  void getByHql(){
        Session session = sessionFactory.openSession();
         session.beginTransaction();

         //创建Criteria对象
        Criteria criteria = session.createCriteria(Employee.class);
        //增加过滤条件
        criteria.add(Restrictions.gt("id",1));
        criteria.add(Restrictions.le("id",5));

                List list = criteria.list();

        for (Object obj :list) {
            if(obj.getClass().isArray()) {
                System.out.println(Arrays.toString((Object[]) obj));
            }else{
                System.out.println(obj);
            }

        }
        session.getTransaction().commit();
        session.close();
    }



}
