/**
 *
 */
public class RentalSystem {
    final static int NOT_EXIST = -1;
    final static int MAX_SIZE = 30;
    private int moviesCounter = 0;
    private int customersCounter = 0;
    private Movie[] movies;
    private Customer[] customers;


    /**
     * Constructs a new RentalSystem
     */
    public RentalSystem() {
        movies = new Movie[MAX_SIZE];
        customers = new Customer[MAX_SIZE];
    }


    /**
     *  Adds new movie to the RentalSystems's storage if it's legal and the storage isn't full
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
     *
     * @param title the title of the movie
     * @param releaseYear the year the movie was released
     * @param directorName the name of the movie's director
     * @return
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
     *
     * @param movie
     * @return
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
     *
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
     *
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
     *
     * @param id the customer's id
     * @return
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
     *
     * @param title the title of the movie
     * @param releaseYear the year the movie was released
     * @param directorName the name of the movie's director
     * @return
     */
    private int getMovieIndex(String title,int releaseYear,String directorName){
        for (int i = 0; i < moviesCounter; i++){
            if (movies[i].isSameMovie(title, releaseYear, directorName)){
                return i;
            }
        }
        return NOT_EXIST;
    }


    /**
     *
     * @param directorName the name of the movie's director
     * @return
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
     *
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
