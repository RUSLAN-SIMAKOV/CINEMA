package cinema.controllers;

import static java.util.stream.Collectors.toList;
import cinema.dto.CinemaHallDto;
import cinema.model.CinemaHall;
import cinema.service.CinemaHallService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cinemahalls")
public class CinemaHallController {

    @Autowired
    private CinemaHallService cinemaHallService;

    @PostMapping(value = "/")
    private void addCinemaHall(@RequestBody CinemaHallDto cinemaHallDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setDescription(cinemaHallDto.getDescription());
        cinemaHall.setCapacity(Integer.parseInt(cinemaHallDto.getCapacity()));
        cinemaHallService.add(cinemaHall);
    }

    @GetMapping(value = "/")
    private List<CinemaHallDto> getAllCinemaHall() {
        List<CinemaHall> cinemaHall = cinemaHallService.getAll();
        return cinemaHall.stream().map(c -> getCinemaHallDto(c)).collect(toList());
    }

    private CinemaHallDto getCinemaHallDto(CinemaHall cinemaHall) {
        CinemaHallDto cinemaHallDto = new CinemaHallDto();
        cinemaHallDto.setDescription(cinemaHall.getDescription());
        cinemaHallDto.setCapacity("" + cinemaHall.getCapacity());
        return cinemaHallDto;
    }
}
