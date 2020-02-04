package cinema.dao;

import java.util.List;
import cinema.exception.DataProcessingException;
import cinema.model.CinemaHall;

public interface CinemaHallDao {

    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll() throws DataProcessingException;
}
