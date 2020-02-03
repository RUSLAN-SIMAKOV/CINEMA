package cinema.service.impl;

import java.util.List;
import cinema.dao.MovieDao;
import cinema.exception.DataProcessingException;
import cinema.lib.Inject;
import cinema.lib.Service;
import cinema.model.Movie;
import cinema.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

    @Inject
    private MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() throws DataProcessingException {
        return movieDao.getAll();
    }
}
