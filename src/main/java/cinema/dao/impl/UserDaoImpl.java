package cinema.dao.impl;

import cinema.dao.UserDao;
import cinema.exception.DataProcessingException;
import cinema.lib.Dao;
import cinema.model.User;
import cinema.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class UserDaoImpl implements UserDao {

    @Override
    public User add(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long itemId = (Long) session.save(user);
            transaction.commit();
            user.setId(itemId);
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can not add User", e);
        }
    }

    @Override
    public User findByEmail(String email) throws DataProcessingException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("SELECT u FROM User u WHERE u.email = :email");
            query.setParameter("email", email);
            return query.getSingleResult();
        }
        catch (Exception e) {
            throw new DataProcessingException("Can not get user by email", e);
        }
    }
}
