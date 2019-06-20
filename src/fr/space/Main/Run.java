package fr.space.Main;

import fr.space.Models.GameModel;
import fr.space.Controllers.ControllerGame;
import fr.space.Views.ConfigView;
import fr.space.Views.GameView;

public class Run {

    public static GameView gameView;
    public static ConfigView configView;


    public static void main (String[] args) {

        final int height = 600;

        final int widthBoard = 500;
        final int widthInfos = 210;


        // init du model principal
        GameModel game = new GameModel(widthBoard, height);


        // controlleur associé
        ControllerGame controller = new ControllerGame(game);


        // on ouvre la fenêtre de jeu
        Run.openGameView(controller, widthBoard, widthInfos, height);


        // on démarre le thread dans le controlleur qui est charger de mettre à jour les entités (update & render)
        Thread gameThread = new Thread(controller);
        gameThread.start();
    }



    /*
     * Methods
     * */
    // fonction qui permet d'ouvrir la fenêtre de config
    public static void openConfigView(ControllerGame controller) {
        Run.setConfigView(new ConfigView(controller, "Config - Space Invaders", 400, 300));
    }


    // fonction qui permet d'ouvrir la fenêtre de jeu
    public static void openGameView(ControllerGame controller, int widthBoard, int widthInfos, int height) {
        Run.setGameView(new GameView(controller, "Space Invaders", widthBoard, widthInfos, height));
    }



    /*
     * GETTER & SETTER
     * */
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
