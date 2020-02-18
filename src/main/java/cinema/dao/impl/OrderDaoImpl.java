package cinema.dao.impl;

import java.util.List;
import cinema.dao.OrderDao;
import cinema.exception.DataProcessingException;
import cinema.model.Order;
import cinema.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Order add(Order order) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long orderId = (Long) session.save(order);
            transaction.commit();
            order.setId(orderId);
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can not add Order", e);
        }
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<Order> query = session
                    .createQuery("select o from Order o where o.user.id = :id");
            query.setParameter("id", user.getId());
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can not get all orders", e);
        }
    }
}
