package models.modelsImpl;

public class Tubes {
    private int height;
    private int width;
    private int positionX;
    private int positionY;
    private boolean reverse;


    public Tubes(int height, int width, int positionX, int positionY, boolean reverse) {
        this.height = height;
        this.width = width;
        this.positionX = positionX;
        this.positionY = positionY;
        this.reverse = reverse;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public boolean isReverse() {
        return reverse;
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }
}