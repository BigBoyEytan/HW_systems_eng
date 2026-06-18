public class Customer {
    private final static int RENT_LIMIT = 5;
    private String name;
    private String id;
    private Movie[] rentedMovies;
    private int rentedMoviesCounter = 0;

    public Customer(String name, String id) {
        this.name = name;
        this.id = id;
        this.rentedMovies = new Movie[RENT_LIMIT];
    }

    public boolean isSameCustomer(String id){
        return this.id.equals(id);
    }

    public boolean isCustomerReachedLimit(){
        return this.rentedMoviesCounter == RENT_LIMIT;
    }

    public void rentMovie(Movie movie){
        rentedMovies[this.rentedMoviesCounter] = movie;
        this.rentedMoviesCounter++;
    }

    public boolean isMovieRented(String title,int releaseYear,String directorName){
        for (int i = 0; i < rentedMoviesCounter; i++){
            if (rentedMovies[i].isSameMovie(title, releaseYear, directorName)){
                return true;
            }
        }
        return false;
    }
    public boolean isMovieRented(Movie movie){
        for (int i = 0; i < rentedMoviesCounter; i++){
            if (rentedMovies[i].isSameMovie(movie)){
                return true;
            }
        }
        return false;
    }

    public boolean returnMovie(String title,int releaseYear,String directorName){
        for (int i = 0; i < rentedMoviesCounter; i++){
            if (rentedMovies[i].isSameMovie(title, releaseYear, directorName)){
                rentedMovies[i] = rentedMovies[rentedMoviesCounter - 1];
                rentedMovies[rentedMoviesCounter - 1] = null;
                rentedMoviesCounter--;
                break;
            }
        }
        return rentedMoviesCounter > 0;
    }
}
