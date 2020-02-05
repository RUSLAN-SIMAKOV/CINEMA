package cinema.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import cinema.dao.MovieSessionDao;
import cinema.exception.DataProcessingException;
import cinema.lib.Dao;
import cinema.model.MovieSession;
import cinema.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long itemId = (Long) session.save(movieSession);
            transaction.commit();
            movieSession.setId(itemId);
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can not add MovieSession entity", e);
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date)
            throws DataProcessingException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<MovieSession> query = builder.createQuery(MovieSession.class);
            Root<MovieSession> root = query.from(MovieSession.class);
            query.select(root);
            query.where(builder.equal(root.get("id"), movieId));
            query.where(builder.between(root.get("showTime"),
                    LocalDateTime.of(date, LocalTime.of(00, 00)),
                    LocalDateTime.of(date, LocalTime.of(23, 59))));
            Query<MovieSession> q = session.createQuery(query);
            return q.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can not get all movies", e);
        }
    }
}
