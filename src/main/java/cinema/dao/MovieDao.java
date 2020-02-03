package cinema.dao;

import java.util.List;
import cinema.exception.DataProcessingException;
import cinema.model.Movie;

public interface MovieDao {

    Movie add(Movie movie);

    List<Movie> getAll() throws DataProcessingException;
}
