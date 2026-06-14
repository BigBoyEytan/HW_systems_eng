import org.w3c.dom.ls.LSOutput;

public class Director {
    private static String[] usedNamesOfDirectors = new String[30];
    private String name;
    private String biography;

    public Director(String name, String Biography) {
        if (Check_if_can_use_name(name)) {
            this.name = name;
            this.biography = Biography;
            //System.out.println("added successfully!");
        }
        //System.out.println("already exist a director with this name or no space!");

    }

    private static boolean Check_if_can_use_name(String name1) {
        if (Director.usedNamesOfDirectors.length <= 30) {
            for (int i = 0; i < 30; i++) {
                if (Director.usedNamesOfDirectors[i] == name1) {
                    return false;
                }
            }
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public String getBiography() {
        return biography;
    }

}
