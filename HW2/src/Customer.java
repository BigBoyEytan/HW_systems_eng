/**
 * Represents a customer in the movies rental system.
 */
public class Customer {
    private final static int RENT_LIMIT = 5;
    private String name;
    private String id;
    private Movie[] rentedMovies;
    private int rentedMoviesCounter = 0;

    /**
     * Constructs a new Customer instance with a given name and id.
     *
     * @param name the name of the customer
     * @param id the unique code of each customer
     */
    public Customer(String name, String id) {
        this.name = name;
        this.id = id;
        this.rentedMovies = new Movie[RENT_LIMIT];
    }

    /**
     * Checks if the provided id matches this customer's id.
     *
     * @param id the ID string to compare to
     * @return true if the ID matches, false otherwise.
     */
    public boolean isSameCustomer(String id){
        return this.id.equals(id);
    }

    /**
     * Determines if the customer has reached their maximum movie rental limit.
     *
     * @return true if the customer cannot rent any more movies, false otherwise
     */
    public boolean isCustomerReachedLimit(){
        return this.rentedMoviesCounter == RENT_LIMIT;
    }

    /**
     * Adds a movie to the customer's list of currently rented movies.
     *
     * @param movie the movie to be rented
     */
    public void rentMovie(Movie movie){
        rentedMovies[this.rentedMoviesCounter] = movie;
        this.rentedMoviesCounter++;
    }

    /**
     * Checks if the customer is currently renting a specific movie based on its details.
     *
     * @param title the title of the movie
     * @param releaseYear the year the movie was released
     * @param directorName the name of the movie's director
     * @return true if the customer is currently renting the movie, false otherwise
     */
    public boolean isMovieRented(String title,int releaseYear,String directorName){
        for (int i = 0; i < rentedMoviesCounter; i++){
            if (rentedMovies[i].isSameMovie(title, releaseYear, directorName)){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the customer is currently renting a specific movie object.
     *
     * @param movie the movie object to check
     * @return true if the customer is currently renting the movie, false otherwise
     */
    public boolean isMovieRented(Movie movie){
        for (int i = 0; i < rentedMoviesCounter; i++){
            if (rentedMovies[i].isSameMovie(movie)){
                return true;
            }
        }
        return false;
    }

    /**
     * Processes the return of a movie by removing it from the customer's rented list.
     *
     * @param title the title of the movie being returned
     * @param releaseYear the year the movie was released
     * @param directorName the name of the movie's director
     * @return true if the customer still has other movies rented after this return, false otherwise
     */
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
