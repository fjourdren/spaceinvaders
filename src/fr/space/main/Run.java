package fr.space.main;

import fr.space.classes.Game;
import fr.space.models.ModelSpace;
import fr.space.controllers.ControllerSpace;
import fr.space.views.ConfigView;
import fr.space.views.GameView;

public class Run {

    public static GameView gameView;
    public static ConfigView configView;

    public static void openConfigView(ControllerSpace controller) {
        Run.setConfigView(new ConfigView(controller, "Config - Space Invaders", 400, 300));
    }


    public static void openGameView(ControllerSpace controller, int widthBoard, int widthInfos, int height) {
        Run.setGameView(new GameView(controller, "Space Invaders", widthBoard, widthInfos, height));
    }


    public static void main (String[] args) {

        final int height = 600;

        final int widthBoard = 500;
        final int widthInfos = 210;

        Game game = new Game(widthBoard, height);


        // create interface
        ModelSpace conv = new ModelSpace(game);

        ControllerSpace controller = new ControllerSpace(conv);

        Run.openGameView(controller, widthBoard, widthInfos, height);

        // game loop start
        Thread gameThread = new Thread(game);
        gameThread.start();
    }


    public static GameView getGameView() {
        return gameView;
    }

    public static void setGameView(GameView gameView) {
        Run.gameView = gameView;
    }

    public static ConfigView getConfigView() {
        return configView;
    }

    public static void setConfigView(ConfigView configView) {
        Run.configView = configView;
    }
}
