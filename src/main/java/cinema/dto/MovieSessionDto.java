package cinema.dto;

public class MovieSessionDto {

    private String movieId;
    private String cinemaHallDescription;
    private String showTime;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getCinemaHallDescription() {
        return cinemaHallDescription;
    }

    public void setCinemaHallDescription(String cinemaHallDescription) {
        this.cinemaHallDescription = cinemaHallDescription;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
}
