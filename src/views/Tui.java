package views;
import controllers.Controller;
import util.observer.IObserver;
import java.util.Scanner;

public class Tui implements IObserver { 
    Controller controller;
    boolean gameOver=false;
    Scanner myObj = new Scanner(System.in);

    public Tui(Controller controller) {
        this.controller = controller;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public void update() {

    }
    public void run() {
        while(!gameOver) {
            printGame();
            scanInput();
            this.controller.changeBirdPosition();
            this.controller.changeTubesPositions();
            this.controller.gameScore();
            gameOver = this.controller.gameOverTest();

        }
    }
    public void scanInput(){
        String  a = myObj.next();
        System.out.println(a);
            if (a.equals("a")) {
                controller.goUp();
            }else {
                printGame();
            }
    }
    private void printGame() {
        System.out.println(this.controller.getGameSting());
        System.out.println(this.controller.getStatusMessage());
    }
}