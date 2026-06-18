/**
 * Represents a Movie Rental System that manages a collection of movies and customers.
 */
public class RentalSystem {
    final static int NOT_EXIST = -1;
    final static int MAX_SIZE = 30;
    private int moviesCounter = 0;
    private int customersCounter = 0;
    private Movie[] movies;
    private Customer[] customers;


    /**
     * Constructs a new RentalSystem instance.
     */
    public RentalSystem() {
        movies = new Movie[MAX_SIZE];
        customers = new Customer[MAX_SIZE];
    }


    /**
     *  Adds new movie to the RentalSystems's storage if the movie is valid and the storage isn't full.
     * @param title the title of the movie
     * @param genre the genre of the movie
     * @param releaseYear the year the movie was released
     * @param directorName the name of the movie's director
     * @param biography the biography of the director
     */
    public void addMovie(String title, Genre genre,int releaseYear,String directorName,String biography) {
        if (moviesCounter == MAX_SIZE){
            System.out.println("System is full. Cannot add more movies.");
            return;
        }
        if (getMovieIndex(title, releaseYear, directorName) != NOT_EXIST){
            System.out.println("Movie is already in the system.");
            return;
        }
        Director director = getDirectorIfExist(directorName);
        if (director == null){
            director = new Director(directorName, biography);
        }
        movies[moviesCounter] = new Movie(title, genre, releaseYear, director);
        moviesCounter++;
    }


    /**
     * Removes a movie for the RentalSystems's storage if exist there and updates all related fields
     * @param title the title of the movie
     * @param releaseYear the year the movie was released
     * @param directorName the name of the movie's director
     */
    public void removeMovie(String title,int releaseYear,String directorName){
        int movieIndex = getMovieIndex(title, releaseYear, directorName);

        if (movieIndex == NOT_EXIST) {
            System.out.println("No such movie exists.");
            return;
        }
        if (isMovieRented(title, releaseYear, directorName)){
            System.out.println("Cannot remove rented movie.");
            return;
        }

        movies[movieIndex] = movies[moviesCounter - 1];
        movies[moviesCounter - 1] = null;
        moviesCounter--;
    }


    /**
     *Checks if a specific movie is currently rented by any customer in the system.
     * @param title the title of the movie
     * @param releaseYear the year the movie was released
     * @param directorName the name of the movie's director
     * @return true if at least one customer has rented this movie, false otherwise
     */
    private boolean isMovieRented(String title,int releaseYear,String directorName){
        for (int i = 0; i < customersCounter; i++){
            if (customers[i].isMovieRented(title, releaseYear, directorName)){
                return true;
            }
        }
        return false;
    }


    /**
     * Checks if a given Movie object is currently rented by any customer.
     * @param movie the Movie object to check
     * @return true if the movie is currently rented, false otherwise
     */
    private boolean isMovieRented(Movie movie){
        for (int i = 0; i < customersCounter; i++){
            if (customers[i].isMovieRented(movie)){
                return true;
            }
        }
        return false;
    }


    /**
     * Prints details of all the rented movies in the systems,
     * and the prints the details of all the unrented movies.
     */
    public void printMovies(){
        if (moviesCounter == 0){
            System.out.println("Rented Movies: ");
            System.out.println("No Rented movies.");
            System.out.println("Unrented Movies: ");
            System.out.println("No Unrented movies.");
            //System.out.println("No Rented/Unrented movies.");
            return;
        }

        if (customersCounter == 0){
            System.out.println("Rented Movies: ");
            System.out.println("No Rented movies.");
            System.out.println("Unrented Movies: ");
            for (int i = 0; i < moviesCounter; i++){
                movies[i].printMovieInfo();
            }
            return;
        }
        else {
            System.out.println("Rented Movies: ");
            boolean noneUnrented = true;
            for (int i = 0; i < moviesCounter; i++){
                if (isMovieRented(movies[i])){
                    movies[i].printMovieInfo();
                } else{
                    noneUnrented = false;
                }
            }
            if (noneUnrented){
                System.out.println("No Unrented movies.");
                return;
            }
            else{
                System.out.println("Unrented Movies: ");
                for (int i = 0; i < moviesCounter; i++){
                    if (!isMovieRented(movies[i])){
                        movies[i].printMovieInfo();
                    }
                }
            }
        }
    }


    /**
     * Processes a movie rental request. If the customer does not exist, a new customer
     * account is created. Checks if the movie exists, if the customer
     * already has it, or if the customer reached their rental limit before completing the rental.
     * @param customerName the name of the customer
     * @param id the customer's id
     * @param title the title of the movie
     * @param releaseYear the year the movie was released
     * @param directorName the name of the movie's director
     */
    public void rentMovie(String customerName, String id, String title,int releaseYear,String directorName){
        int customerIndex = getCustomerIndex(id);
        int movieIndex = getMovieIndex(title, releaseYear, directorName);
        //
        if (movieIndex == NOT_EXIST) {
            System.out.println("No such movie exists.");
            return;
        }

        if (customerIndex != NOT_EXIST){
            if (customers[customerIndex].isMovieRented(title, releaseYear, directorName)){
                System.out.println("Customer already has this movie");
                return;
            }
            if (customers[customerIndex].isCustomerReachedLimit()){
                System.out.println("The customer has reached the limit");
                return;
            }
            customers[customerIndex].rentMovie(movies[movieIndex]);
        }
        else {
            if (customersCounter == MAX_SIZE){
                System.out.println("No room for new customers.");
                return;
            }
            customers[customersCounter] = new Customer(customerName, id);
            customers[customersCounter].rentMovie(movies[movieIndex]);
            customersCounter++;
        }
    }


    /**
     * Searches for a customer by their id.
     * @param id the customer's id
     * @return the index of the customer in the array, or NOT_EXIST if not found
     */
    private int getCustomerIndex(String id){
        for (int i = 0; i < customersCounter; i++){
            if (customers[i].isSameCustomer(id)){
                return i;
            }
        }
        return NOT_EXIST;
    }


    /**
     * Searches for a movie based on its title, release year, and director.
     * @param title the title of the movie
     * @param releaseYear the year the movie was released
     * @param directorName the name of the movie's director
     * @return the index of the movie in the array, or NOT_EXIST if not found
     * */
    private int getMovieIndex(String title,int releaseYear,String directorName){
        for (int i = 0; i < moviesCounter; i++){
            if (movies[i].isSameMovie(title, releaseYear, directorName)){
                return i;
            }
        }
        return NOT_EXIST;
    }


    /**
     * Searches existing movies to find a matching Director object by name.
     * @param directorName the name of the movie's director
     * @return the existing Director object if exists, or null otherwise
     */
    private Director getDirectorIfExist(String directorName){
        for (int i = 0; i < moviesCounter; i++){
            if (movies[i].isSameDirector(directorName)){
                return movies[i].getDirector();
            }
        }
        return null;
    }


    /**
     * Handles returning a rented movie and updates all necessary fields.
     * @param id the customer's id
     * @param title the title of the movie
     * @param releaseYear the year the movie was released
     * @param directorName the name of the movie's director
     */
    public void returnMovie(String id, String title,int releaseYear,String directorName){
        int customerIndex = getCustomerIndex(id);
        if (customerIndex == NOT_EXIST){
            System.out.println("Customer not found.");
            return;
        }

        int movieIndex = getMovieIndex(title, releaseYear, directorName);
        if (movieIndex == NOT_EXIST ||
                !customers[customerIndex].isMovieRented(title, releaseYear, directorName)) {
            System.out.println("Customer cannot return the movie.");
            return;
        }
        boolean isMoviesRemaining =  customers[customerIndex].returnMovie(title, releaseYear, directorName);
        if (!isMoviesRemaining){
            customers[customerIndex] = customers[customersCounter];
            customers[customersCounter] = null;
            customersCounter--;
        }


    }
}
