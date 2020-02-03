package cinema.service;

import java.util.List;
import cinema.exception.DataProcessingException;
import cinema.model.Movie;

public interface MovieService {

    Movie add(Movie movie);

    List<Movie> getAll() throws DataProcessingException;
}
