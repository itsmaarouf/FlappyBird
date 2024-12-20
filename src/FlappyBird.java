// Importing necessary classes from the project
import controllers.Controller; // Handles the interaction between models and views
import models.modelsImpl.Game; // Represents the game logic
import views.Gui; // Graphical User Interface (GUI) for the game
import views.Tui; // Text-based User Interface (TUI) for the game

// Main class for the FlappyBird application
public class FlappyBird {
    public static void main(String[] args) {
        // sInitialize the game model
        Game game = new Game();
        // Create a controller, passing the game model
        Controller controller = new Controller(game);
        // Initialize the views (GUI and TUI), passing the controller
        Tui tui = new Tui(controller); // Text-based interface
        Gui gui = new Gui(controller); // Graphical interface

        // Register both views as observers of the controller
        controller.addObserver(gui);
        controller.addObserver(tui);

        // Start the game loop in the controller
        controller.run();
    }
}
