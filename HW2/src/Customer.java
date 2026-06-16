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
}
