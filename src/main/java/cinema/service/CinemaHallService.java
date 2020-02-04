package cinema.service;

import java.util.List;
import cinema.model.CinemaHall;

public interface CinemaHallService {

    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();
}
