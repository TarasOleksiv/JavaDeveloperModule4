package ua.goit.java8.javadeveloper.dao.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.java8.javadeveloper.dao.ProductDAO;
import ua.goit.java8.javadeveloper.dao.utils.HibernateUtil;
import ua.goit.java8.javadeveloper.model.Product;

import java.util.List;
import java.util.UUID;

/**
 * Created by t.oleksiv on 30/11/2017.
 */
public class HibernateProductDAOImpl implements ProductDAO {

    @Override
    public Product getById(UUID uuid) {
        Session session = null;
        Transaction tx = null;
        Product result = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            result = (Product) session.get(Product.class,uuid);
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
    public List<Product> getAll() {
        Session session = null;
        Transaction tx = null;
        List<Product> result = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            // HQL
            result = (List<Product>) session.createQuery("FROM Product order by name").list();

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
    public void create(Product product) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            // HQL
            // перевірка чи продукт з таким name існує
            Query query = session.createQuery("FROM Product WHERE name = :name AND manufacturer = :manufacturer");
            query.setParameter("name",product.getName());
            query.setParameter("manufacturer",product.getManufacturer());
            List<Product> results = (List<Product>) query.list();
            if (results.size() > 0){
                System.out.println("Продукт даного виробника з такою назвою вже існує.");
            } else {
                session.save(product);
                System.out.println("Продукт створено успішно.");
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            System.out.println("Неможливо створити продукт.");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void update(Product product) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(product);
            System.out.println("Продукт змінено успішно.");
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            System.out.println("Неможливо змінити продукт.");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Product product) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(product);
            System.out.println("Продукт вилучено успішно.");
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            System.out.println("Неможливо вилучити продукт.");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
