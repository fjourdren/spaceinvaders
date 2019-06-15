package fr.space.main;

import fr.space.classes.Game;
import fr.space.models.ModelSpace;
import fr.space.controllers.ControllerSpace;
import fr.space.views.ViewSpace;

public class Run {

    public static void main (String[] args) {

        final int height = 600;

        final int widthBoard = 500;
        final int widthInfos = 200;

        Game game = new Game(widthBoard, height);


        // create interface
        ModelSpace conv = new ModelSpace(game);

        ControllerSpace controler = new ControllerSpace(conv);

        //ViewSpace configInterface = new ConfigInterface(controler, "Config - SpaceInvaders");

        ViewSpace vs = new ViewSpace(controler, "SpaceInvaders - Hunt the ShalaK", widthBoard, widthInfos, height);


        // game loop start
        Thread gameThread = new Thread(game);
        gameThread.start();

    }

}
