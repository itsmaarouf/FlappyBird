package controllers;
// Import necessary dependencies
import models.modelsImpl.Game;  // The main game logic
import models.modelsImpl.Messages;  // Contains messages like status or scores
import util.observer.Observable;  // Implements the Observer pattern for notifications

import java.util.Scanner;  // For user input
import java.util.concurrent.TimeUnit; // For pausing the game loop

// Controller class responsible for handling the game logic and user interactions
public class Controller extends Observable {
    // Fields
    Game game;  // The game model
    String statusMessage;  // Holds the current game status (e.g., welcome message, game over)
    boolean gameOver=false;  // Indicates whether the game has ended
    Scanner myObj = new Scanner(System.in); // Used to capture user input

    // Constructor: Initializes the controller with a game object and a welcome message
    public Controller(Game game) {
        this.game = game;
        this.statusMessage = Messages.WELCOME_MESSAGE;
    }

    // Getter for the current status message
    public String getStatusMessage() {
        return statusMessage;
    }

    // Getter for the game model
    public Game getGame() {
        return game;
    }


    // Converts the game model's current state to a string representation
    public String getGameSting() {
        return this.game.toString();
    }

    // Moves the bird down by 1 unit (gravity effect)
    public void changeBirdPosition() {
        this.game.getBird().setPositionY(this.game.getBird().getPositionY() + 1);
        notifyObservers(); // Notify observers (e.g., GUI or TUI) of the state change
    }

    // Moves the bird up by 2 units (user action)
    public void goUp() {
        this.game.getBird().setPositionY(this.game.getBird().getPositionY() - 2);
        notifyObservers();
    }

    // Moves all the tubes 1 unit to the left and updates their positions
    public void changeTubesPositions() {
        for (int i = 0; i < this.game.getTubes().length; i++) {
            this.game.getTubes()[i].setPositionX(this.game.getTubes()[i].getPositionX() - 1);
        }
        this.game.updateTubes(); // Handle tubes that move off-screen
        notifyObservers();
    }
    
    // Checks if a given position overlaps with any tube's position, returning the tube's index or -1 if none
    public int getTubeIndex(int x, int y) {
        for (int i = 0; i < this.game.getTubes().length; i++) {

            if (x >= this.game.getTubes()[i].getPositionX()
                    && x < this.game.getTubes()[i].getPositionX() + this.game.getTubes()[i].getWidth()
                    && y >= this.game.getTubes()[i].getPositionY()
                    && y < this.game.getTubes()[i].getPositionY() + this.game.getTubes()[i].getHeight()

            ) {
                return i;
            }
        }
        return -1;
    }

    // Checks if the game is over by detecting collisions between the bird and the tubes
    public boolean gameOver() {
        for (int i = 0; i < this.game.getTubes().length; i++) {
            if (this.game.getBird().getPositionX() == this.game.getTubes()[i].getPositionX()
                    && this.game.getBird().getPositionY() == this.game.getTubes()[i].getPositionY()) {
                return true; // Collision detected
            }
            notifyObservers();
        }
        return false;
    }

    // Displays the game-over message
    public void gameOverMessage() {
        this.statusMessage = Messages.GameOver;
        notifyObservers();
    }

    // Tracks and updates the game score
    int Score = 0;

    public int gameScore() {
        for (int i = 0; i < this.game.getTubes().length; i++) {
            if (this.game.getBird().getPositionX() == this.game.getTubes()[i].getPositionX()
                    + this.game.getTubes()[i].getWidth()) {
                Score++; // Increase score if the bird passes a tube
                this.statusMessage = Messages.GameScore(Score);
            }
            notifyObservers();
        }
        return Score;
    }

    // Captures user input and handles the "jump" action
    public void scanInput() {
        String a = myObj.next(); // Get the user's input
        System.out.println(a); // Debugging: Print the input
        if (a.equals("a")) {
            goUp(); // Move the bird up if "a" is pressed
        } else {
            getGameSting(); // Display the current game state
            getStatusMessage(); // Display the current status message
        }
    }

    // Prints the game state and status message
    private void printGame() {
        System.out.println(getGameSting());
        System.out.println(getStatusMessage());
    }

    // Main game loop
    public void run() {
            while(!gameOver) {
                printGame();  // Display the game state
                scanInput();  // Wait for user input
                try {
                    TimeUnit.MILLISECONDS.sleep(600);  // Pause for a short duration
                } catch (InterruptedException e) {
                    e.printStackTrace();  // Handle interruptions in the sleep
                }
                changeBirdPosition();  // Apply gravity
                changeTubesPositions();  // Move the tubes
                gameScore();  // Update the score
                gameOver = gameOver();  // Check if the game has ended
            }
            gameOverMessage(); // Display the game-over message
        notifyObservers();  // Notify all observers of the final state

    }
}

