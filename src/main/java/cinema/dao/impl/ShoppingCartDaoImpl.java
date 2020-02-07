package cinema.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import cinema.dao.ShoppingCartDao;
import cinema.exception.DataProcessingException;
import cinema.lib.Dao;
import cinema.model.ShoppingCart;
import cinema.model.Ticket;
import cinema.model.User;
import cinema.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long itemId = (Long) session.save(shoppingCart);
            transaction.commit();
            shoppingCart.setId(itemId);
            return shoppingCart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can not add ShoppingCart", e);
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ShoppingCart> criteria = builder.createQuery(ShoppingCart.class);
            Root<ShoppingCart> shoppingCartRoot = criteria.from(ShoppingCart.class);
            criteria.select(shoppingCartRoot);
            criteria.where(builder.equal(shoppingCartRoot.get("user"), user));
            ShoppingCart shoppingCart = session.createQuery(criteria).uniqueResult();
            Query<ShoppingCart> query = session
                    .createQuery("from ShoppingCart where user = :user", ShoppingCart.class);
            query.setParameter("user", user);
            ShoppingCart shoppingCart1 = query.uniqueResult();
            List<Ticket> tickets = session
                    .createQuery("select t from ShoppingCart as sc join sc.tickets "
                            + "as t where sc.user.id = :userId", Ticket.class)
                    .setParameter("userId", user.getId())
                    .list();
            shoppingCart.setTickets(tickets);
            return shoppingCart;
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can not update shopping cart ", e);
        }
    }
}
