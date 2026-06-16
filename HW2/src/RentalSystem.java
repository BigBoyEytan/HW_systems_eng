public class RentalSystem {
    final static int NOT_EXIST = -1;
    final static int MAX_SIZE = 30;
    private static int moviesCounter = 0;
    private int customersCounter = 0;
    private Movie[] movies;
    private Customer[] customers;

    public RentalSystem() {
        movies = new Movie[MAX_SIZE];
        customers = new Customer[MAX_SIZE];
    }
    public void addMovie(String title, Genre genre,int releaseYear,String directorName,String biography) {
        if (moviesCounter == MAX_SIZE){
            System.out.println("System is full. Cannot add more movies.");
            return;
        }
        if (isMovieExist(title, releaseYear, directorName)){
            System.out.println("Movie is already in the system.");
            return;
        }

        // check if director exist
        // maybe static array of directors in class Director



    }
    private boolean isMovieExist(String title, int releaseYear,String directorName){
        for (int i = 0; i < moviesCounter; i++){
            if (movies[i].isSameMovie(title, releaseYear, directorName)){
                return true;
            }
        }
        return false;
    }

    public void removeMovie(){

    }

    public void rentMovie(String customerName, String id, String title,int releaseYear,String directorName){
        int customerIndex = getCustomerIndex(id);
        int movieIndex = getMovieIndex(title, releaseYear, directorName);
        //
        if (movieIndex == NOT_EXIST) {
            System.out.println("No such movie exist.");
            return;
        }
        //
        if (customerIndex != NOT_EXIST){
            if (customers[customerIndex].isCustomerReachedLimit()){
                System.out.println("The customer has reached the limit.");
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
        }
    }

    private int getCustomerIndex(String id){
        for (int i = 0; i < customersCounter; i++){
            if (customers[i].isSameCustomer(id)){
                return i;
            }
        }
        return NOT_EXIST;
    }
    private int getMovieIndex(String title,int releaseYear,String directorName){
        for (int i = 0; i < moviesCounter; i++){
            if (movies[i].isSameMovie(title, releaseYear, directorName)){
                return i;
            }
        }
        return NOT_EXIST;
    }
}
