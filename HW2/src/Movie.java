public class Movie {
    private static String[] movies_stored = new String[30];
    private String name;
    private Genre movieGenre;
    private int yearOfRelease;
    private Director movieDirector;


    public Movie(Director movieDirector, int yearOfRelease, Genre movieGenre, String name) {
        if (check_if_can_add_movie(name)) {
            this.movieDirector = movieDirector;
            this.yearOfRelease = yearOfRelease;
            this.movieGenre = movieGenre;
            this.name = name;
        }
    }


    private static boolean check_if_can_add_movie(String name1) {
        if (movies_stored.length <= 30) {
            for (int i = 0; i < 30; i++) {
                if (name1 == Movie.movies_stored[i]) {
                    return false;
                }
            }
        }
        return true;
    }


    public Genre getMovieGenre() {
        return movieGenre;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public Director getMovieDirector() {
        return movieDirector;
    }

    public String getName() {
        return name;
    }
}
