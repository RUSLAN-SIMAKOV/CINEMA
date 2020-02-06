package cinema.service.impl;

import java.util.List;
import cinema.dao.CinemaHallDao;
import cinema.exception.DataProcessingException;
import cinema.lib.Inject;
import cinema.lib.Service;
import cinema.model.CinemaHall;
import cinema.service.CinemaHallService;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {

    @Inject
    private static CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        try {
            return cinemaHallDao.getAll();
        } catch (DataProcessingException e) {
            throw new RuntimeException("Can not get all cinema halls", e);
        }
    }
}
