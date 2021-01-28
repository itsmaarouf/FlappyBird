package controllers;
import models.modelsImpl.Game;
import util.observer.Observable;


public class Controller extends Observable{
    Game game;


    public Controller(Game game) {
        this.game = game;
    }

    public String getGame() {
        return this.game.toString();
    }

    public void changeBirdPosition(){
        this.game.getBird().setPositionY(this.game.getBird().getPositionY()+1);
    }

    public void goUp() {
        this.game.getBird().setPositionY(this.game.getBird().getPositionY()-4);
    }

    public void changeTubesPositions() {
        for (int i = 0; i < this.game.getTubes().length; i++) {
            this.game.getTubes()[i].setPositionX(this.game.getTubes()[i].getPositionX() - 1);
        }
        //this.game.updateTubes();
    }
}

