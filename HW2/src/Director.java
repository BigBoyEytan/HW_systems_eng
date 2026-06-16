public class Director {
    private String name;
    private String biography;

    public Director(String name, String Biography) {
        this.name = name;
        this.biography = Biography;
        //System.out.println("added successfully!");
    }
    public String getName(){
        return this.name;
    }

    public boolean isSameDirector(String name){
        return this.name.equals(name);
    }
    public boolean isSameDirector(Director other){
        return this.name.equals(other.name);
    }
}
