package cn.eleven.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import javax.management.Query;
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
     * HQL：Hibernate Query Language
     * 特点：
     * 1、与sql相似，SQL基本语法使用
     * 2、SQL查询的是表和表中的列，HQL查询的事对象与对象中的属性
     * 3、HQL的关键字不区分大小写，类名与属性名区分大小写
     * 4、SELECT可以省略
     */
    @Test
    public  void getByHql(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        String hql = null;

        // hql=" FROM Employee As e ";
        // hql=" FROM Employee As e  where id<10";
        // hql=" FROM Employee As e  where e.id<10 order by e.name desc ";
        // hql=" FROM Employee As e  where e.id<10 order by e.name,e.id ASC ";
        // hql="select e.name FROM Employee As e  where e.id<10 order by e.name,e.id ASC ";
       // hql="select e.id,e.name FROM Employee As e  where e.id<10 order by e.name,e.id ASC ";//返回的事一个数组
       // hql="select new Employee(e.id,e.name ) FROM Employee As e  where e.id<10 order by e.name,e.id ASC ";//用对象封装，但是需要构造方法

        //执行查询，获取结果（list,uniqyeResult、分页）
        List list = session.createQuery("FROM Employee  e")
                .setFirstResult(0)
                .setMaxResults(10)
                .list();
        for (Object obj :list) {
            if(obj.getClass().isArray()) {
                System.out.println(Arrays.toString((Object[]) obj));
            }else{
                System.out.println(obj);
            }

        }
       /* for (int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }*/
        session.close();
    }


    @Test
    public  void getByHql2(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = null;

        //集合函数
        /*hql="Select COUNT(*) FROM Employee e";
        hql="Select MAX(id) FROM Employee e";
        Number count =  (Number) session.createQuery(hql).uniqueResult();
        System.out.println("count="+count.intValue());*/

        //分组
        //hql = "SELECT e.name,count(*) FROM Employee e where id<9 group by e.name";
        // hql = "SELECT e.name,count(*) FROM Employee e group by e.name having count(e.id)>1";
        /*hql = "SELECT e.name,count(*) as c " +
                "FROM Employee e " +
                "where id <9 " +
                "group by e.name " +
                "having count(e.id)>1 " +//在having中不能使用列别名
                "order by c ASC";//在order by 中可以使用列别名
        print(hql, session );*/

        //链接查询
/*        hql = "SELECT e.id,e.name,d.name FROM Employee e JOIN e.department d";
        hql = "SELECT e.id,e.name,d.name FROM Employee e LEFT OUTER JOIN e.department d";
        hql = "SELECT e.id,e.name,d.name FROM Employee e RIGHT OUTER JOIN e.department d";
        hql = "SELECT e.id,e.name,e.department.name FROM Employee e ";
        print(hql, session );*/

        //查询时使用参数
        //1、使用问号占位符
       /* hql="FROM Employee WHERE id BETWEEN ? AND ? ";
        List list = session.createQuery(hql)
                .setParameter(0,5)
                .setParameter(1,15)
                .list();*/
        //2、使用变量名
/*        hql="FROM Employee e where id between :idMin and :idMax";
        List list = session.createQuery(hql)
                .setParameter("idMin",5)
                .setParameter("idMax",15)
                .list();*/
        //当参数是集合时
/*        hql="FROM Employee e where id IN (:ids)";
        List list = session.createQuery(hql)
                .setParameterList("ids",new Object[]{1,2,3,5,8})
                .list();*/

        //使用命名查询
/*        List list =  session.getNamedQuery("queryHql")
                .setParameter("idMin",5)
                .setParameter("idMax",15)
                .list();*/

        //update与delete,不会通知session缓存
/*        int result = session.createQuery("update  Employee e set e.name =? where id >15")
                .setParameter(0,"找网吧").executeUpdate();
        System.out.println("result=="+result);*/
        int result = session.createQuery("DELETE  Employee e  where id >15")
                .executeUpdate();
        System.out.println("result=="+result);


/*        for (Object obj :list) {
            if(obj.getClass().isArray()) {
                System.out.println(Arrays.toString((Object[]) obj));
            }else{
                System.out.println(obj);
            }

        }*/
        session.getTransaction().commit();
        session.close();
    }


    public  void print(String hql,Session session ){
        List list = session.createQuery(hql)
                .setFirstResult(0)
                .setMaxResults(10)
                .list();
        for (Object obj :list) {
            if(obj.getClass().isArray()) {
                System.out.println(Arrays.toString((Object[]) obj));
            }else{
                System.out.println(obj);
            }

        }
    }
}
