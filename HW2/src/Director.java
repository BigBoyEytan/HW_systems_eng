public class Director {
    private String name;
    private String biography;

    /**
     * constructor
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
     *
     * @return the name of This director
     */
    public String getName(){
        return this.name;
    }

    /**
     * cheks if this director has the same name as the director's name given (its their id)
     *
     * @param name name of the director to check
     * @return true if they have the same name (id) , false otherwise
     */
    public boolean isSameDirector(String name){
        return this.name.equals(name);
    }

    /**
     * cheks if this director has the same name as the director given (its their id)
     *
     * @param other director to compare the name to
     * @return true if they have the same name (id) , false otherwise
     */
    public boolean isSameDirector(Director other){
        return this.name.equals(other.name);
    }
}
