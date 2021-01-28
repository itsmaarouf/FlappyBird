import controllers.Controller;
import models.modelsImpl.Game;
import views.Tui;

public class FlappyBird {
    public static void main(String[] args) {
       Game game = new Game();
        Controller controller = new Controller(game);
        Tui tui = new Tui(controller);
        tui.run();


    }
}
