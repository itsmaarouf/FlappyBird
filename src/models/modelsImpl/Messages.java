package models.modelsImpl;

// A utility class to manage game messages
public class Messages {

    // A welcome message displayed when the game starts
    public static final String WELCOME_MESSAGE = "Hello and Welcome in Flappy Bird, the best game ever \n press A to go up and S to go down";

    // A method that returns a formatted score message
    public static String GameScore(int score) {
        return "your Score is: " + score; // Display the current score
    }

    // A constant message to indicate the game is over
    public static final String GameOver = "Game is Over!!!";

}
