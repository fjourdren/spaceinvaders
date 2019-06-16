package fr.space.main;

import fr.space.classes.Game;
import fr.space.models.ModelSpace;
import fr.space.controllers.ControllerSpace;
import fr.space.views.ViewSpace;

public class Run {

    public static void main (String[] args) {

        final int height = 600;

        final int widthBoard = 500;
        final int widthInfos = 210;

        Game game = new Game(widthBoard, height);


        // create interface
        ModelSpace conv = new ModelSpace(game);

        ControllerSpace controller = new ControllerSpace(conv);

        //ViewSpace vs = new ViewSpace(controller, "SpaceInvaders - Hunt the ShalaK", widthBoard, widthInfos, height);
        ViewSpace v = new ViewSpace(controller, "test", widthBoard, widthInfos, height);
        //ViewSpace configInterface = new ConfigInterface(controller, "Config - SpaceInvaders");

        // game loop start
        Thread gameThread = new Thread(game);
        gameThread.start();

    }
}
