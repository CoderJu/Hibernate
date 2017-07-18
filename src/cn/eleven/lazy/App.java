package cn.eleven.lazy;

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
                                .configure().addClass(Department.class).addClass(Employee.class).buildSessionFactory();

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


    @Test
    public  void get(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Department department = session.get(Department.class,1);

      // Department department_load = session.load(Department.class,1);

        System.out.println("name="+department.getName());
        System.out.println("name="+department.getEmployees().size());
        System.out.println("name="+department.getEmployees());
        session.getTransaction().commit();
        session.close();
    }


}
