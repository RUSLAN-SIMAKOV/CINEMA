package cinema.service.impl;

import java.time.LocalDate;
import java.util.List;
import cinema.dao.MovieSessionDao;
import cinema.exception.DataProcessingException;
import cinema.lib.Inject;
import cinema.model.MovieSession;
import cinema.service.MovieSessionService;

public class MovieSessionServiceImpl implements MovieSessionService {

    @Inject
    private static MovieSessionDao movieSessionDao;

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try {
            return movieSessionDao.findAvailableSessions(movieId, date);
        } catch (DataProcessingException e) {
            throw new RuntimeException("Can not find Available Sessions", e);
        }
    }

    @Override
    public MovieSession add(MovieSession session) {
        return movieSessionDao.add(session);
    }
}
