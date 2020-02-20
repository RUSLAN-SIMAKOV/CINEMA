package cinema.dao;

import java.time.LocalDate;
import java.util.List;
import cinema.exception.DataProcessingException;
import cinema.model.MovieSession;

public interface MovieSessionDao {

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) throws DataProcessingException;

    MovieSession add(MovieSession session);

    MovieSession getByMovieSessionId(Long movieSessionId);
}
