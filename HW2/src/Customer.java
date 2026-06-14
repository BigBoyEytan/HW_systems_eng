public class Customer {
    private Movie[] rented_movies = new Movie[5];
    private String name;
    private String id;

    public Customer(String name, String id, Movie[] rented_movies) {
        this.name = name;
        this.id = id;
        this.rented_movies = rented_movies;
    }

    public Movie[] getRented_movies() {
        return rented_movies;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
