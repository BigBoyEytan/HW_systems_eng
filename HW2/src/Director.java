public class Director {
    public String name;
    public String biography;
    private static String[] usedNamesOfDirectors = new String [30];

    public Director(String name, String Biography){
        if(Check_if_can_use_name(name)){
            this.name = name;
            this.biography = Biography;
        }
    }

    private static boolean Check_if_can_use_name(String name1){
        for(int i=0;i<30;i++){
            if(Director.usedNamesOfDirectors[i] == name1){
                return false;
            }
        }
        return true;
    }

}
