package models.modelsImpl;

public class Bird {
    private int positionY;
    private int positionX;

    public Bird(int positionY, int positionX) {
        this.positionY = positionY;
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int fly() {
        return positionY+=1;
    }
}
