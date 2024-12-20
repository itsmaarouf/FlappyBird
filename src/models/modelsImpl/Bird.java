package models.modelsImpl;

// Represents the bird in the game
public class Bird {
    private int positionY;  // Vertical position of the bird on the grid
    private int positionX;  // Horizontal position of the bird on the grid

    // Constructor: Initializes the bird's starting position
    public Bird(int positionY, int positionX) {
        this.positionY = positionY; // Set initial Y position
        this.positionX = positionX; // Set initial X position
    }

    // Getter for the vertical position
    public int getPositionY() {
        return positionY;
    }

    // Setter for the vertical position (used to move the bird up or down)
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    // Getter for the horizontal position
    public int getPositionX() {
        return positionX;
    }

    // Setter for the horizontal position (not commonly used in this game)
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

}
