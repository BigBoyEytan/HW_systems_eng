public class Movie {
    private static String[] movies_stored = new String[30];
    private String name;
    private Genre movieGenre;
    private int yearOfRelease;
    private Director movieDirector;


    public Movie(String name, Director movieDirector, int yearOfRelease, Genre movieGenre) {
            this.movieDirector = movieDirector;
            this.yearOfRelease = yearOfRelease;
            this.movieGenre = movieGenre;
            this.name = name;
    }


    public static boolean check_if_can_add_movie(String name1) {
        if (movies_stored.length <= 30) {
            for (int i = 0; i < 30; i++) {
                if (name1 == Movie.movies_stored[i]) {
                    System.out.println("Movie is already in the system.");
                    return false;
                }
            }
        }
        if(movies_stored.length > 30){
            System.out.println("System is full,cannot add more movies.");
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
