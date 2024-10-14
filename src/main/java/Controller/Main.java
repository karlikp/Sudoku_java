package Controller;

import Model.*;
import View.*;


public class Main {
    public static void main(String[] args) {
        // Creating model (Player), view (GameView) and controller (GameController)
        Player model = new Player("", "");  // Empty model, when will be initialize
        GameView view = new GameView();     // View to interaction with user
        GameController controller = new GameController(model, view);  // Creating controller with model and view

        // Starting game
        controller.GameStart(args);  // Controller manage interaction between model and view
    }
}
