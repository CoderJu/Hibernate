package cn.eleven.dao;

import cn.eleven.com.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by User on 2017/6/21.
 */
public class UserDao {
   
    public void save(User user) throws Exception {
        Session session =  SessionUtil.openSessionFactory();
        Transaction tx = null;
        try {
            tx =  session.beginTransaction();
            session.save(user);
            tx.commit();
        }catch (RuntimeException e){
            tx.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

   
    public void update(User user) throws Exception {
        Session session =  SessionUtil.openSessionFactory();
        Transaction tx = null;
        try {
            tx =  session.beginTransaction();
            session.update(user);
            tx.commit();
        }catch (RuntimeException e){
            tx.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

   
    public void delete(int id) throws Exception {
        Session session =  SessionUtil.openSessionFactory();
        Transaction tx = null;
        try {
            tx =  session.beginTransaction();
            User user =  session.get(User.class,id);
            session.delete(user);
            tx.commit();
        }catch (RuntimeException e){
            tx.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

   
    public User getById(int id) throws Exception {
        Session session =  SessionUtil.openSessionFactory();
        Transaction tx = null;
        User user = null;
        try {
            tx =  session.beginTransaction();
            user = (User)session.get(User.class,id);
            tx.commit();
            return user;
        }catch (RuntimeException e){
            tx.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

   
    public List<User> findAll() throws Exception {
        Session session =  SessionUtil.openSessionFactory();
        Transaction tx = null;

        try {
            tx =  session.beginTransaction();
            List list = session.createQuery("FROM User").list();
            tx.commit();
            return list;
        }catch (RuntimeException e){
            tx.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

   
    public QueryResult findAllPage(int firstResult,int maxResult) throws Exception {
        Session session = SessionUtil.openSessionFactory();
        Transaction tx = null;
        QueryResult queryResult = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM User");
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResult);
            List<User> list = query.list();

            Long count = (Long) session.createQuery("select  count(*) from User ").uniqueResult();

            queryResult = new QueryResult(count.intValue(), list);
            tx.commit();
            return queryResult;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

}
