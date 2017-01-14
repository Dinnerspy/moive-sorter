
public class Movies {

    String MovieTitle, MovieYear;
    int intMovieYear, Seen, NotSeen;

    Movies(String SringsOfMovie, String StringOfYear) {

        MovieTitle = SringsOfMovie;
        MovieYear = StringOfYear;
        intMovieYear = Integer.parseInt(StringOfYear);
        Seen = 0;
        NotSeen = 0;

    }

    public String getMovieName() {

        return MovieTitle;

    }

    public String getMovieYear() {

        return MovieYear;

    }

    public int getIntMovieYear() {

        return intMovieYear;

    }

    public void setSeen() {

        Seen = 1;

    }

    public void setNotSeen() {
        NotSeen = 1;
    }

    public int getSeen() {

        return Seen;

    }

    public int getNotSeen() {
        return NotSeen;
    }

}
