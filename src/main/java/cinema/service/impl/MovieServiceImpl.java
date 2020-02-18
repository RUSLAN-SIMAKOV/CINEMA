package cinema.service.impl;

import java.util.List;
import cinema.dao.MovieDao;
import cinema.exception.DataProcessingException;
import cinema.model.Movie;
import cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
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
