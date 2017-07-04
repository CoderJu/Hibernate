package cn.eleven.manyToMany;

import cn.eleven.oneToMany.Department;
import cn.eleven.oneToMany.Employee;
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
                                .configure().addClass(Teacher.class).addClass(Student.class).buildSessionFactory();

    @Test
    public  void save(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Student student1 = new Student();
            student1.setName("王同学");
            Student student2 = new Student();
            student2.setName("找同学");

            Teacher teacher1 = new Teacher();
            teacher1.setName("张老师");
            Teacher teacher2 = new Teacher();
            teacher2.setName("刘老师");

            student1.getTeachers().add(teacher1);
            student1.getTeachers().add(teacher2);
            student2.getTeachers().add(teacher1);
            student2.getTeachers().add(teacher2);

            teacher1.getStudents().add(student1);
            teacher1.getStudents().add(student2);
            teacher2.getStudents().add(student1);
            teacher2.getStudents().add(student2);

            session.save(student1);
            session.save(student2);
            session.save(teacher1);
            session.save(teacher2);

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
            Student student1 = session.get(Student.class,1L);
            System.out.println("name="+student1.getName());
            System.out.println("name="+student1.getTeachers());
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
            Student student = session.get(Student.class,1L);
            student.getTeachers().clear();

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
            Student student = session.get(Student.class,4L);
            session.delete(student);

        }catch (Exception e){
            tx.rollback();
            throw e;
        }finally {
            tx.commit();
        }
        session.close();
    }

}
