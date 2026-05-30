import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static Scanner scanner; // Note: Do not change this line.
    static int MAX_MOVIES = 100;


    static String OPENING_MESSAGE = "Welcome to the Movies Management System!.";
    static String ALL_ACTIONS = """
            1. Add a new movie
            2. Display all movies
            3. Display movie rating
            4. Find the best director
            5. Exit
            """;
    static String ENDING_MESSAGE = "Exiting the program. Goodbye!";
    static String INVALID_INPUT = "Invalid choice. Please try again.";


    public static void manageMovies() {
        printOpeningMessage();

    }

    public static void printOpeningMessage(){
        System.out.println(OPENING_MESSAGE);
    }

    public static void addNewMovie(){

    }

    public static void main(String[] args) throws IOException {
        String path = args[0];

        scanner = new Scanner(new File(path));
        int numberOfTests = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= numberOfTests; i++) {
            System.out.println("Test number " + i + " starts.");
            try {
                manageMovies();
            } catch(Exception e){
                System.out.println("Exception " + e);
            }
            System.out.println("Test number " + i + " ended.");
            System.out.println("-----------------------------------------------");
        }
        System.out.println("All tests have ended.");
    }


}