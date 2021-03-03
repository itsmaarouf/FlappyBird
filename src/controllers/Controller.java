package controllers;
import models.modelsImpl.Game;
import models.modelsImpl.Messages;
import util.observer.Observable;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Controller extends Observable{
    Game game;
    String statusMessage;
    boolean gameOver=false;
    Scanner myObj = new Scanner(System.in);

    public Controller(Game game) {
            this.game = game;
            this.statusMessage = Messages.WELCOME_MESSAGE;
        }

    public String getStatusMessage() {
        return statusMessage;
    }

    public Game getGame() {
        return game;
    }

    public String getGameSting() {
        return this.game.toString() ;
    }

    public void changeBirdPosition(){
        this.game.getBird().setPositionY(this.game.getBird().getPositionY()+1);
        notifyObservers();
    }

    public void goUp() {
        this.game.getBird().setPositionY(this.game.getBird().getPositionY()-2);
        notifyObservers();
    }

    public void changeTubesPositions() {
        for (int i = 0; i < this.game.getTubes().length; i++) {
            this.game.getTubes()[i].setPositionX(this.game.getTubes()[i].getPositionX() - 1);
        }
        this.game.updateTubes();
        notifyObservers();
    }

    public int getTubeIndex(int x, int y){
        for (int i = 0; i < this.game.getTubes().length; i++) {

            if (x>=this.game.getTubes()[i].getPositionX()
                    && x<this.game.getTubes()[i].getPositionX() +this.game.getTubes()[i].getWidth()
                    && y>=this.game.getTubes()[i].getPositionY()
                    && y<this.game.getTubes()[i].getPositionY()+this.game.getTubes()[i].getHeight()

            ) {
                return i;
            }
        }
        return -1;
    }

    public boolean gameOver() {
        for (int i = 0; i < this.game.getTubes().length; i++) {
            if (this.game.getBird().getPositionX() == this.game.getTubes()[i].getPositionX()
            && this.game.getBird().getPositionY()  == this.game.getTubes()[i].getPositionY())
            {
                return true;
            }
            notifyObservers();
        }
        return false;
    }

    public void gameOverMessage(){
        this.statusMessage = Messages.GameOver;
        notifyObservers();
    }

    int Score = 0;
    public int gameScore() {
        for (int i = 0; i < this.game.getTubes().length; i++) {
            if (this.game.getBird().getPositionX() == this.game.getTubes()[i].getPositionX()+this.game.getTubes()[i].getWidth())
            {
                Score++;
                this.statusMessage = Messages.GameScore(Score);
            }
            notifyObservers();
        }
        return Score;
    }

    public void scanInput(){
        String  a = myObj.next();
        System.out.println(a);
        if (a.equals("a")) {
            goUp();
        }else {
            getGameSting();
            getStatusMessage();
        }
    }
    private void printGame() {
        System.out.println(getGameSting());
        System.out.println(getStatusMessage());
    }

    public void run() {
            while(!gameOver) {
                printGame();
                scanInput();
                try {
                    TimeUnit.MILLISECONDS.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                changeBirdPosition();
                changeTubesPositions();
                gameScore();
                gameOver = gameOver();
            }
            gameOverMessage();
        notifyObservers();

    }
}

