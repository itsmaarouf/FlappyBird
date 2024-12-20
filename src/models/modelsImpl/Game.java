package models.modelsImpl;

// Game class contains the core game logic and state
public class Game {
    private Bird bird;    // Represents the player's bird
    private Tubes[] tubes;    // Array of tubes representing obstacles
    private int height = 20,    // Height of the game grid
                width = 15;   // Width of the game grid
            
    // ANSI escape codes for color (used for TUI representation)        
    public static final String ANSI_RESET = "\u001B[0m",
                                ANSI_RED = "\u001B[31m",
                                ANSI_YELLOW = "\u001B[33m";

    // Constructor: Initializes the bird and the array of tubes
    public Game() {
        this.tubes = new Tubes[10]; // Creates 10 tubes

        // Initialize each tube with specific position and size
        this.tubes[0] = new Tubes(10, 2, 10, 10, false);
        this.tubes[1] = new Tubes(6, 2, 10, 0, true);
        this.tubes[2] = new Tubes(8, 2, 16, 12, false);
        this.tubes[3] = new Tubes(8, 2, 16, 0, true);
        this.tubes[4] = new Tubes(12, 2, 22, 8, false);
        this.tubes[5] = new Tubes(4, 2, 22, 0, true);
        this.tubes[6] = new Tubes(10, 2, 28, 12, false);
        this.tubes[7] = new Tubes(6, 2, 28, 0, true);
        this.tubes[8] = new Tubes(8, 2, 34, 12, false);
        this.tubes[9] = new Tubes(8, 2, 34, 0, true);

        // Initialize the bird at position (2, 3)
        this.bird = new Bird(2, 3);
    }

    // Getters for the bird, tubes, game height, and width
    public Bird getBird() {
        return bird;
    }

    public Tubes[] getTubes() {
        return tubes;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    // Checks if a specific position (x, y) intersects with any tube
    public int getTubeIndex(int x, int y) {
        for (int i = 0; i < this.tubes.length; i++) {

            // Check if the position is within the tube's boundaries
            if (x == this.tubes[i].getPositionX()
                    && y >= this.tubes[i].getPositionY()
                    && y < this.tubes[i].getPositionY() + this.tubes[i].getHeight()
                    || x == (this.tubes[i].getPositionX() + this.tubes[i].getWidth())
                            && y < this.tubes[i].getPositionY() + this.tubes[i].getHeight()
                            && y >= this.tubes[i].getPositionY()) {
                return i; // Return the index of the tube
            }

        }
        return -1; // Return -1 if no tube is found at the position
    }

    // Converts the game state to a string (used for TUI display)
    public String toString() {
        return printGame();
    }

    // Builds and returns a string representation of the game grid
    public String printGame() {
        String result = "", oneBorder = "|", onePixelX = " ", onePixelY = "\n";

        result += "_________________" + onePixelY; // Top border
        for (int y = 0; y < height; y++) {
            result += oneBorder; // Left border
            for (int x = 0; x < width; x++) {
                //Building Tubes Borders
                int tubeIndex = getTubeIndex(x, y); // Check if there's a tube at (x, y)
                if (tubeIndex > -1) {
                    result += ANSI_RED + oneBorder + ANSI_RESET; // Tube boundary
                } else if (x == this.bird.getPositionX() && y == this.bird.getPositionY()) {
                    result += ANSI_YELLOW + "O" + ANSI_RESET; // Bird
                } else {
                    result += onePixelX; // Empty space
                }
            }
            result += oneBorder + onePixelY; // Right border
        }
        result += "|_______________|"; // Bottom border
        return result;
    }

    // Updates tube positions; tubes that move off-screen are repositioned
    public void updateTubes() {
        int lastPositionX = getLastPositionX(); // Get the furthest tube's X position
        for (int i = 0; i < this.tubes.length; i++) {
            if (this.tubes[i].getPositionX() < -4) { // Check if a tube is off-screen
                this.tubes[i].setPositionX(lastPositionX + 7); // Reposition the tube
            }
        }
    }

    // Finds the furthest X position among all tubes
    private int getLastPositionX() {
        int X = 0;
        for (int i = 0; i < this.tubes.length; i++) {

            if (this.tubes[i].getPositionX() >= X) {
                X = this.tubes[i].getPositionX();  // Update X if the tube's position is further
            }
        }
        return X;
    }
}
