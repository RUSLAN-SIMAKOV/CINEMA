package cinema.controllers;

import static java.util.stream.Collectors.toList;
import cinema.dto.MovieSessionDto;
import cinema.exception.BadRequestException;
import cinema.model.MovieSession;
import cinema.service.CinemaHallService;
import cinema.service.MovieService;
import cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/moviesessions")
public class MovieSessionController {

    @Autowired
    private CinemaHallService cinemaHallService;

    @Autowired
    private MovieSessionService movieSessionService;

    @Autowired
    private MovieService movieService;

    @PostMapping(value = "/")
    private void addMovieSession(@RequestBody MovieSessionDto movieSessionDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHallService.getAll().stream()
                .filter(c -> c.getDescription().equals(movieSessionDto.getCinemaHallDescription()))
                .findFirst()
                .orElseThrow(() -> new BadRequestException("Wrong name of cinemahall")));
        movieSession.setMovie(movieService.getAll().stream()
                .filter(m -> m.getId().toString().equals(movieSessionDto.getMovieId()))
                .findFirst()
                .orElseThrow(() -> new BadRequestException("Wrong movieId")));
        movieSession.setShowTime(LocalDateTime.parse(movieSessionDto.getShowTime()));
        movieSessionService.add(movieSession);
    }

    @GetMapping(value = "/available")
    private List<MovieSessionDto> getAllMovieSessions(@RequestParam(name = "movieId") String movieId,
                                          @RequestParam(name = "date") String date) {
        List<MovieSession> movieSessions = movieSessionService
                .findAvailableSessions(Long.parseLong(movieId), LocalDate.parse(date));
        return movieSessions.stream().map(m -> getMovieSessionDto(m)).collect(toList());
    }

    private MovieSessionDto getMovieSessionDto(MovieSession movieSession) {
        MovieSessionDto movieSessionDto = new MovieSessionDto();
        movieSessionDto.setCinemaHallDescription(movieSession.getCinemaHall().getDescription());
        movieSessionDto.setMovieId("" + movieSession.getId());
        movieSessionDto.setShowTime("" + movieSession.getShowTime());
        return movieSessionDto;
    }
}
