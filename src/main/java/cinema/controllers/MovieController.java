package cinema.controllers;

import static java.util.stream.Collectors.toList;
import cinema.dto.MovieDto;
import cinema.model.Movie;
import cinema.service.MovieService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping(value = "/add")
    private void addMovie(@RequestBody MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movieService.add(movie);
    }

    @GetMapping
    private List<MovieDto> getAllMoveis() {
        List<Movie> movies = movieService.getAll();
        return movies.stream().map(this::getMovieDto).collect(toList());
    }

    private MovieDto getMovieDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle(movie.getTitle());
        movieDto.setDescription(movie.getDescription());
        return movieDto;
    }
}
