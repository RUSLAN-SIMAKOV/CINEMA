package cinema.service;

import java.time.LocalDate;
import java.util.List;
import cinema.model.MovieSession;

public interface MovieSessionService {

        MovieSession add(MovieSession movieSession);

        MovieSession getByMovieSessionId(Long movieSessionId);

        List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);
}
