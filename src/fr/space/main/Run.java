package fr.space.main;

import fr.space.classes.Game;
import fr.space.models.ModelSpace;
import fr.space.controllers.ControllerSpace;
import fr.space.views.ViewSpace;
import fr.space.views.GameInterface;
import fr.space.views.ConfigInterface;

public class Run {

    public static void main (String[] args){
        Game game = new Game();

        game.run();


        // create interface
        ModelSpace conv = new ModelSpace(game);

        ControllerSpace controler = new ControllerSpace(conv);

        ViewSpace gameInterface = new GameInterface(controler, "SpaceInvaders - Hunt the Kalash");
        ViewSpace configInterface = new ConfigInterface(controler, "Config - SpaceInvaders");

    }

}
