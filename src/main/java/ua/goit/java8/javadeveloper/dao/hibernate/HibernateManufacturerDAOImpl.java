package ua.goit.java8.javadeveloper.dao.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.java8.javadeveloper.dao.ManufacturerDAO;
import ua.goit.java8.javadeveloper.dao.utils.HibernateUtil;
import ua.goit.java8.javadeveloper.model.Manufacturer;

import java.util.List;
import java.util.UUID;

/**
 * Created by t.oleksiv on 30/11/2017.
 */
public class HibernateManufacturerDAOImpl implements ManufacturerDAO {

    @Override
    public Manufacturer getById(UUID uuid) {
        Session session = null;
        Transaction tx = null;
        Manufacturer result = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            result = (Manufacturer) session.get(Manufacturer.class,uuid);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return result;
    }

    @Override
    public List<Manufacturer> getAll() {
        Session session = null;
        Transaction tx = null;
        List<Manufacturer> result = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            // HQL
            result = (List<Manufacturer>) session.createQuery("FROM Manufacturer order by name").list();

            /* SQL Entity query
            SQLQuery query = session.createSQLQuery("SELECT * FROM manufacturers ORDER BY name");
            query.addEntity(Manufacturer.class);
            result = query.list();
            */

            /* SQL Scalar query
            SQLQuery query = session.createSQLQuery("SELECT id, name FROM manufacturers ORDER BY name");
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = query.list();
            for(Object object : data) {
                Map row = (Map)object;
                System.out.print("Id: " + row.get("id"));
                System.out.println(", Name: " + row.get("name"));
            }
            */

            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return result;
    }

    @Override
    public List<Manufacturer> getByName(String name) {
        Session session = null;
        Transaction tx = null;
        List<Manufacturer> results = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            // HQL
            Query query = session.createQuery("FROM Manufacturer WHERE name = :name");
            query.setParameter("name",name);
            results = (List<Manufacturer>) query.list();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return results;
    }

    @Override
    public void create(Manufacturer manufacturer) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();


            // HQL
            // перевірка чи виробник з таким name існує
            Query query = session.createQuery("FROM Manufacturer WHERE name = :name");
            query.setParameter("name",manufacturer.getName());
            List<Manufacturer> results = (List<Manufacturer>) query.list();
            if (results.size() > 0){
                System.out.println("Виробник з такою назвою вже існує.");
            } else {
                session.save(manufacturer);
                System.out.println("Виробника створено успішно.");
            }

            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            System.out.println("Неможливо створити виробника.");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void update(Manufacturer manufacturer) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(manufacturer);
            System.out.println("Виробника змінено успішно.");
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            System.out.println("Неможливо змінити виробника.");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Manufacturer manufacturer) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(manufacturer);
            System.out.println("Виробника вилучено успішно.");
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            System.out.println("Неможливо вилучити виробника.");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

}
