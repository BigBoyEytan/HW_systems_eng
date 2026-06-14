import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner;
    static int MAX_MOVIES = 100;
    static int MOVIES_COUNTER = 0;
    static String[] MOVIES = new String[MAX_MOVIES];
    static String[] DIRECTORS = new String[MAX_MOVIES];
    static double[] RATINGS = new double[MAX_MOVIES];
    static String[] ALL_NUM_OPTIONS = {"1", "2", "3", "4", "5"};
    static String OPENING_MESSAGE = "Welcome to the Movies Management System!.";
    static String ALL_USER_OPTIONS = """
            1. Add a new movie
            2. Display all movies
            3. Display movie rating
            4. Find the best director
            5. Exit
            Please enter your choice: """;
    static String ENDING_MESSAGE = "Exiting the program. Goodbye!";
    static String INVALID_INPUT = "Invalid choice. Please try again.";
    static String MOVIES_LIMIT_REACHED = "Movies limit reached";
    static String INVALID_RATING = "Invalid rating";
    static String NO_MOVIES = "No movies are available.";


    /**
     * Manages the main movie system and interactions with the user.
     */
    public static void manageMovies() {
        printOpeningMessage();
        String optionChosen = "start";

        while (!optionChosen.equals("5")) {
            printAllUserOptions();
            optionChosen = getOptionFromUser();
            switch (optionChosen) {
                case "1":
                    addNewMovie();
                    break;
                case "2":
                    displayAllMovies();
                    break;
                case "3":
                    displayMovieRating();
                    break;
                case "4":
                    findBestDirector();
                    break;
            }
        }
        printEndingMessage();

        MOVIES_COUNTER = 0;
        MOVIES = new String[MAX_MOVIES];
        DIRECTORS = new String[MAX_MOVIES];
        RATINGS = new double[MAX_MOVIES];
    }


    public static void printOpeningMessage() {
        System.out.println(OPENING_MESSAGE);
    }


    public static void printAllUserOptions() {
        System.out.println(ALL_USER_OPTIONS);
    }


    public static void printEndingMessage() {
        System.out.println(ENDING_MESSAGE);
    }

    /**
     * Reads and returns a valid menu option chosen by the user.
     *
     * @return the verified valid option string
    /Users/eytangorenshtein/CLionProjects/HW_1_shared/src */
    public static String getOptionFromUser() {
        String optionChosen = scanner.nextLine();
        while (!isOptionValid(optionChosen)) {
            System.out.println(INVALID_INPUT);
            printAllUserOptions();
            optionChosen = scanner.nextLine();
        }
        return optionChosen;
    }

    /**
     * Checks if the users input option matches any of the legal options.
     *
     * @param userInput the text entered by the user
     * @return true if the input is valid, false otherwise
     */
    public static boolean isOptionValid(String userInput) {
        for (int i = 0; i < ALL_NUM_OPTIONS.length; i++) {
            if (userInput.equals(ALL_NUM_OPTIONS[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new movie to the data or updates its rating if it already exists.
     */
    public static void addNewMovie() {

        System.out.println("Enter movie name:");
        String movieName = scanner.nextLine();

        System.out.println("Enter rating:");
        Double rating = scanner.nextDouble();
        scanner.nextLine();
        if (rating > 10 || rating < 0) {
            System.out.println(INVALID_RATING);
            return;
        }

        int indexOfMovie = getIndexOfMovie(movieName);

        if (!(indexOfMovie == MOVIES_COUNTER)) {
            RATINGS[indexOfMovie] = rating;
            return;
        }

        System.out.println("Enter director name:");
        String directorName = scanner.nextLine();


        if (MOVIES_COUNTER == MAX_MOVIES) {
            System.out.println(MOVIES_LIMIT_REACHED);
            return;
        }
        MOVIES[MOVIES_COUNTER] = movieName;
        RATINGS[MOVIES_COUNTER] = rating;
        DIRECTORS[MOVIES_COUNTER] = directorName;
        MOVIES_COUNTER++;
        System.out.println("Movie " + movieName + " added successfully!");
    }

    /**
     * Finds the index of a movie by its name.
     *
     * @param movieNmme the name of the movie to search for
     * @return the index of the movie if found, or the current movie count if not found
     */
    public static int getIndexOfMovie(String movieNmme) {
        for (int i = 0; i < MOVIES_COUNTER; i++) {
            if (MOVIES[i].equals(movieNmme)) {
                return i;
            }
        }
        return MOVIES_COUNTER;
    }


    /**
     * Displays all movies stored, grouped by their director's name.
     */
    public static void displayAllMovies() {
        if (MOVIES_COUNTER == 0) {
            System.out.println(NO_MOVIES);
            return;
        }
        String[] uniqueDirectors = getUniqueDirectors();

        for (int i = 0; i < uniqueDirectors.length; i++) {
            if (uniqueDirectors[i] == null) {
                break;
            }

            for (int diractorIndex = 0; diractorIndex < MOVIES_COUNTER; diractorIndex++) {
                if (uniqueDirectors[i].equals(DIRECTORS[diractorIndex])) {
                    System.out.println("Name: " + MOVIES[diractorIndex] + " rating: " + RATINGS[diractorIndex] + " director: " + DIRECTORS[diractorIndex]);
                }
            }
        }
    }

    /**
     * gets and returns an array of unique directors from the movies saved.
     *
     * @return an array containing only unique director names
     */
    public static String[] getUniqueDirectors() {
        String[] uniqueDirectorsMax = new String[MOVIES_COUNTER];
        int counter = 0;
        boolean found = false;

        for (int i = 0; i < MOVIES_COUNTER; i++) {
            for (int runner = 0; runner < MOVIES_COUNTER ; runner++) {
                if (uniqueDirectorsMax[runner] != null && DIRECTORS[i].equals(uniqueDirectorsMax[runner])) {
                    found = true;
                }
            }
            if (!found) {
                uniqueDirectorsMax[counter] = DIRECTORS[i];
                counter++;
            }
            found = false;
        }

        int len_to_return =0;
        for(int i=0;i<MOVIES_COUNTER;i++){
            if(uniqueDirectorsMax[i] != null){
                len_to_return ++;
            }
        }
        String[] unique_directors = new String[len_to_return];
        for(int i=0;i<len_to_return;i++){
            unique_directors[i] = uniqueDirectorsMax[i];
        }
        return unique_directors;
    }


    /**
     * Prompts for a movie name and prints its rating if exists.
     */
    public static void displayMovieRating() {
        System.out.println("Enter movie name:");
        String movieName = scanner.nextLine();

        for (int i = 0; i < MOVIES_COUNTER; i++) {
            if (movieName.equals(MOVIES[i])) {
                System.out.println("Rating for " + movieName + ": " + RATINGS[i]);
                return;
            }
        }
        System.out.println("No movie found with name " + movieName);
    }


    /**
     * configures and prints the director with the highest average movie rating.
     */
    public static void findBestDirector() {
        if (MOVIES_COUNTER == 0) {
            System.out.println(NO_MOVIES);
            return;
        }
        int index_best_director = 0;
        String[] unique_directors = getUniqueDirectors();
        double maxAvgRaring = -1, directorAvgRating;
        int lenUniqueDirectors = unique_directors.length;
        for (int i = 0; i < lenUniqueDirectors ; i++) {
            double sumOfRatings = 0;
            int numberOfMovies = 0;
            for (int j = 0; j <MOVIES_COUNTER; j++){
                if(unique_directors[i].equals(DIRECTORS[j])){
                    sumOfRatings += RATINGS[j];
                    numberOfMovies ++;
                }
            }
            directorAvgRating = sumOfRatings/ numberOfMovies;
            if(directorAvgRating > maxAvgRaring){
                maxAvgRaring = directorAvgRating;
                index_best_director =i;
            }
        }


        System.out.println("Best director: " + unique_directors[index_best_director] + " with an average rating of: "
                + String.format("%.2f", maxAvgRaring));
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
            } catch (Exception e) {
                System.out.println("Exception " + e);
            }
            System.out.println("Test number " + i + " ended.");
            System.out.println("-----------------------------------------------");
        }
        System.out.println("All tests have ended.");
    }


}