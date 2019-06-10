package fr.space.main;

import fr.space.classes.Game;
import fr.space.models.ModelSpace;
import fr.space.controllers.ControllerSpace;
import fr.space.views.ViewSpace;
import fr.space.views.GameInterface;
import fr.space.views.ConfigInterface;

public class Run {

    public static void main (String[] args) {

        final int width = 500;
        final int height = 600;

        Game game = new Game(height);


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
