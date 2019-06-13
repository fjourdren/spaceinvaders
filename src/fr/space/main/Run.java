package fr.space.main;

import fr.space.classes.Game;
import fr.space.models.ModelSpace;
import fr.space.controllers.ControllerSpace;
import fr.space.views.GameInterface;

public class Run {

    public static void main (String[] args) {

        final int width = 500;
        final int height = 600;

        Game game = new Game(width, height - 33); // remove 33 has OS window


        // create interface
        ModelSpace conv = new ModelSpace(game);

        ControllerSpace controler = new ControllerSpace(conv);

        GameInterface gameInterface = new GameInterface(controler, "SpaceInvaders - Hunt the Kalash", width, height);
        //ViewSpace configInterface = new ConfigInterface(controler, "Config - SpaceInvaders");

        // game loop start
        Thread gameThread = new Thread(game);
        gameThread.start();
    }

}
