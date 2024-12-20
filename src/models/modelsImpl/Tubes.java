package models.modelsImpl;

// Represents a single tube (obstacle) in the game
public class Tubes {
    private int height;  // Height of the tube
    private int width;  // Width of the tube
    private int positionX;  // Horizontal position of the tube (column on the grid)
    private int positionY;  // Vertical position of the top-left corner of the tube
    private boolean reverse; // Indicates if the tube is reversed (bottom-to-top)

    // Constructor: Initializes the properties of a tube
    public Tubes(int height, int width, int positionX, int positionY, boolean reverse) {
        this.height = height; // Set tube height
        this.width = width; // Set tube width
        this.positionX = positionX; // Set tube's starting X position
        this.positionY = positionY; // Set tube's starting Y position
        this.reverse = reverse; // Specify if the tube is reversed
    }

    // Getter for the height of the tube
    public int getHeight() {
        return height;
    }

    // Setter for the height of the tube
    public void setHeight(int height) {
        this.height = height;
    }

    // Getter for the width of the tube
    public int getWidth() {
        return width;
    }

    // Setter for the width of the tube
    public void setWidth(int width) {
        this.width = width;
    }

    // Getter for the horizontal position (X-coordinate)
    public int getPositionX() {
        return positionX;
    }

    // Setter for the horizontal position (X-coordinate)
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    // Getter for the vertical position (Y-coordinate)
    public int getPositionY() {
        return positionY;
    }

    // Setter for the vertical position (Y-coordinate
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    // Getter for the "reverse" flag
    // True means the tube is reversed (starts from the bottom and grows upward)
    public boolean isReverse() {
        return reverse;
    }

    // Setter for the "reverse" flag
    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }
}