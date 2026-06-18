/**
 * Represents a movie director with a unique name and biography.
 */
public class Director {
    private String name;
    private String biography;

    /**
     * Constructs a new Director instance.
     *
     * @param name
     * @param Biography
     */
    public Director(String name, String Biography) {
        this.name = name;
        this.biography = Biography;
        //System.out.println("added successfully!");
    }

    /**
     * Gets the name of the director.
     * @return the name of This director
     */
    public String getName(){
        return this.name;
    }

    /**
     * Checks if this director matches the given name.
     *
     * @param name name of the director to check
     * @return true if the names are the same , false otherwise
     */
    public boolean isSameDirector(String name){
        return this.name.equals(name);
    }

    /**
     * Checks if this director has the same name as another director instance.
     *
     * @param other the other director to compare with
     * @return true if the names are the same , false otherwise
     */
    public boolean isSameDirector(Director other){
        return this.name.equals(other.name);
    }
}
