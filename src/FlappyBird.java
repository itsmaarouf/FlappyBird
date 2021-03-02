import controllers.Controller;
import models.modelsImpl.Game;
import views.Gui;
import views.Tui;

public class FlappyBird {
    public static void main(String[] args) {
       Game game = new Game();
        Controller controller = new Controller(game);
        Tui tui = new Tui(controller);
        Gui gui = new Gui(controller);

        controller.addObserver(gui);
        controller.addObserver(tui);

        tui.run();
        gui.run();
    }
}
