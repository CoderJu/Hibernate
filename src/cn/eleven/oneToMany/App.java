package cn.eleven.oneToMany;

import cn.eleven.collection.User;
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

        Department department = new Department();
        department.setName("开发部");

        Employee employee = new Employee();
        employee.setName("张三");

        Employee employee1 = new Employee();
        employee1.setName("李四");

        department.getEmployees().add(employee);
        department.getEmployees().add(employee1);

        try {
            session.save(employee);
            session.save(employee1);
            session.save(department);


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
            Department department = session.get(Department.class,1);
            System.out.println("name="+department.getName());

            Employee employee = session.get(Employee.class,1);
            System.out.println("name="+employee.getName());
        }catch (Exception e){
            tx.rollback();
            throw e;
        }finally {
            tx.commit();
        }
        session.close();
    }


    @Test
    public  void RemoveRelation(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            //从员工方解除关系
            //Employee employee = session.get(Employee.class,1);
            //employee.setDepartment(null);

            //从部门方解除（inverse="false"可以解除）
            Department department = session.get(Department.class,1);
            department.getEmployees().clear();

        }catch (Exception e){
            tx.rollback();
            throw e;
        }finally {
            tx.commit();
        }
        session.close();
    }


    @Test
    public  void Delete(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {

            //Employee employee = session.get(Employee.class,3);
           // session.delete(employee);


            Department department = session.get(Department.class,2);
            session.delete(department);
        }catch (Exception e){
            tx.rollback();
            throw e;
        }finally {
            tx.commit();
        }
        session.close();
    }

}
