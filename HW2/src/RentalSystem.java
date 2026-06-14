public class RentalSystem {

    public RentalSystem() {
    }
    public void addMovie(String name, Genre movieGenre,int dateOfRelease,String directorName,String biography) {
        Director d1 = new Director(name, biography);
        if (Movie.check_if_can_add_movie(name)) {
            Movie m1 = new Movie(name, d1, dateOfRelease,movieGenre);
        }


    }
}
