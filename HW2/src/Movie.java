public class Movie {
    private String title;
    private Genre genre;
    private int releaseYear;
    private Director director;

    public Movie(String title, Genre  genre, int releaseYear, Director director){
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.director = director;
    }

    public boolean isSameMovie(String title,int releaseYear,String directorName){
        return this.title.equals(title) && this.releaseYear == releaseYear &&
                this.director.isSameDirector(directorName);
    }

    public void printMovieInfo(){
        System.out.println("Title: " + this.title + ", Genre: " + this.genre
                + ", Year: " + this.releaseYear + ", director: " + this.director.getName());
    }
}
