package cinema.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import cinema.dao.MovieDao;
import cinema.exception.DataProcessingException;
import cinema.lib.Dao;
import cinema.model.Movie;
import cinema.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieDaoImpl implements MovieDao {

    private static final Logger LOGGER = Logger.getLogger(MovieDaoImpl.class);

    public Movie add(Movie movie) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long itemId = (Long) session.save(movie);
            transaction.commit();
            movie.setId(itemId);
            return movie;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can not add Movie entity", e);
        }
    }

    public List<Movie> getAll() throws DataProcessingException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
// 1 - Create a CriteriaBuilder instance by calling the Session.getCriteriaBuilder() method.
            CriteriaBuilder builder = session.getCriteriaBuilder();
// 2 - Create a query object by creating an instance of the CriteriaQuery interface.
            CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
// 3 - Set the query Root by calling the from() method on the CriteriaQuery object
// to define a range variable in FROM clause.
            Root<Movie> root = query.from(Movie.class);
// 4 - Specify what the type of the query result will be by calling the select() method
// of the CriteriaQuery object.
            query.select(root);
// 5 - Prepare the query for execution by creating a org.hibernate.query.Query instance
// by calling the Session.createQuery() method, specifying the type of the query result.
            Query<Movie> q = session.createQuery(query);
// 6 - Execute the query by calling the getResultList() or getSingleResult() method on the
// org.hibernate.query.Query object.
            List<Movie> list = q.getResultList();
            return list;

// Код из лекции выкидывает ощибку на 63 строке " multiple selections: use Tuple or array "
//            CriteriaQuery<Movie> criteriaQuery = session.getCriteriaBuilder()
//                    .createQuery(Movie.class);
//            criteriaQuery.from(Movie.class);
//            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can not get all movies", e);
        }
    }
}
