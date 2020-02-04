package cinema.service.impl;

import java.util.List;
import cinema.dao.MovieDao;
import cinema.exception.DataProcessingException;
import cinema.lib.Inject;
import cinema.lib.Service;
import cinema.model.Movie;
import cinema.service.MovieService;
import org.apache.log4j.Logger;

@Service
public class MovieServiceImpl implements MovieService {

    @Inject
    private MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        try {
            return movieDao.getAll();
        } catch (DataProcessingException e) {
            throw new RuntimeException("Can not get all Movie entity", e);
        }
    }
}
