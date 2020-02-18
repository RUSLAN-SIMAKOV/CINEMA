package cinema.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import cinema.dao.CinemaHallDao;
import cinema.exception.DataProcessingException;
import cinema.model.CinemaHall;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImpl implements CinemaHallDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long itemId = (Long) session.save(cinemaHall);
            transaction.commit();
            cinemaHall.setId(itemId);
            return cinemaHall;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can not add CinemaHall entity", e);
        }
    }

    @Override
    public List<CinemaHall> getAll() throws DataProcessingException {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CinemaHall> query = builder.createQuery(CinemaHall.class);
            Root<CinemaHall> root = query.from(CinemaHall.class);
            query.select(root);
            Query<CinemaHall> q = session.createQuery(query);
            return q.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can not get all cinema halls", e);
        }
    }
}
