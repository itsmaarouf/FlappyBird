package models.modelsImpl;

public class Game {
    private Bird bird;
    private Tubes[] tubes;
    private int height = 10, width = 30;

    public Game() {
        this.tubes = new Tubes[10];
        this.tubes[0] = new Tubes(5, 3, 10, 5, false);
        this.tubes[1] = new Tubes(3, 3, 10, 0, true);
        this.tubes[2] = new Tubes(4, 3, 17, 6, false);
        this.tubes[3] = new Tubes(4, 3, 17, 0, true);
        this.tubes[4] = new Tubes(7, 3, 24, 3, false);
        this.tubes[5] = new Tubes(2, 3, 24, 0, true);
        this.tubes[6] = new Tubes(6, 3, 31, 4, false);
        this.tubes[7] = new Tubes(3, 3, 31, 0, true);
        this.tubes[8] = new Tubes(5, 3, 38, 5, false);
        this.tubes[9] = new Tubes(4, 3, 38, 0, true);

        this.bird = new Bird(2,5);
    }

    public Tubes[] getTubes() {
        return tubes;
    }

    public Bird getBird() {
        return bird;
    }


    public int getTubeIndex(int x, int y){
        for (int i = 0; i < this.tubes.length; i++) {

            if (x==this.tubes[i].getPositionX()
                    && y>=this.tubes[i].getPositionY()
                    && y<this.tubes[i].getPositionY()+this.tubes[i].getHeight()
                    || x==(this.tubes[i].getPositionX() +this.tubes[i].getWidth() )
                    && y<this.tubes[i].getPositionY()+this.tubes[i].getHeight()
                    && y>=this.tubes[i].getPositionY()
            ) {
                return i;
            }

        }
        return -1;
    }
    public String toString(){
        return printGame();
    }

    public String printGame(){
        String result = "", oneBorder = "|", onePixelX = " ", onePixelY = "\n";

        result += "|------------------------------|"+onePixelY;
        for (int y = 0; y < height; y++) {
            //Building Game Borders
            result += oneBorder;
            for (int x = 0; x < width; x++) {
                //Building Tubes Borders
                int tubeIndex = getTubeIndex(x, y);
                if (tubeIndex >-1) {
                    result += oneBorder;
                }else if (x == this.bird.getPositionX() && y == this.bird.getPositionY() ) {
                    // bird
                    result += "O";
                }else {
                    // space
                    result += onePixelX;
                }
            }
            result += oneBorder+onePixelY;
        }
        result += "|------------------------------|";
        return result;
    }

    public void updateTubes() {
        int lastPositionX = getLastPositionX();
        for (int i = 0; i <this.tubes.length ; i++) {
            System.out.print(this.tubes[i].getPositionX() + " ");
            if (this.tubes[i].getPositionX() < -4) {
                this.tubes[i].setPositionX(lastPositionX + 7);
            }
        }
    }

    private int getLastPositionX() {
        int X = 0;
        for (int i = 0; i <this.tubes.length ; i++) {
            if (this.tubes[i].getPositionX()>=X) {
                X = this.tubes[i].getPositionX();
            }
        }
        return X;
    }
}