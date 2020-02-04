package cinema.service;

import java.time.LocalDate;
import java.util.List;
import cinema.model.MovieSession;

public interface MovieSessionService {

        MovieSession add(MovieSession movieSession);

        List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);
}
