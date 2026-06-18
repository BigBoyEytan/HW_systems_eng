/**
 * Represents a movie with its associated details such as title, genre, release year, and director.
 */
public class Movie {
    private String title;
    private Genre genre;
    private int releaseYear;
    private Director director;

    /**
     * Constructs a new Movie instance
     *
     * @param title the title of the movie
     * @param genre the genre of the movie
     * @param releaseYear the year the movie was released
     * @param director the director of the movie
     */
    public Movie(String title, Genre  genre, int releaseYear, Director director){
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.director = director;
    }

    /**
     * Checks if the provided details match this movie's title, release year, and director.
     *
     * @param title the title to compare
     * @param releaseYear the release year to compare
     * @param directorName the name of the director to compare
     * @return true if all provided details match this movie, false otherwise
     */
    public boolean isSameMovie(String title,int releaseYear,String directorName){
        return this.title.equals(title) && this.releaseYear == releaseYear &&
                this.director.isSameDirector(directorName);
    }

    /**
     * Checks if the two movies are the same
     *
     * @param other the other movie we want to compare to
     * @return true if they are equal, false otherwise
     */
    public boolean isSameMovie(Movie other){
        return  isSameMovie(other.title, other.releaseYear,other.director.getName());
    }

    /**
     * Prints the movie's details
     */
    public void printMovieInfo(){
        System.out.println("Title: " + this.title + ", Genre: " + this.genre
                + ", Year: " + this.releaseYear + ", director: " + this.director.getName());
    }

    /**
     * Checks if the provided name matches the name of this movie's director.
     *
     * @param name the director name to check
     * @return true if the name matches the director, false otherwise
     */
    public boolean isSameDirector(String name){
        return this.director.isSameDirector(name);
    }

    public Director getDirector() {
        return director;
    }
}
