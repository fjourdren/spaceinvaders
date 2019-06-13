package fr.space.views;

import fr.space.classes.Keyboard;
import fr.space.controllers.ControllerSpace;

import javax.swing.*;
import java.util.Observable;

public class GameInterface extends View {

    private BoardPanel boardPanel;

    public GameInterface(ControllerSpace controller, String title, int width, int height) {
        super(controller, title, width, height);


        this.boardPanel = new BoardPanel(controller);

        this.getController().setBoard(this.boardPanel);

        this.initWindow();
    }


    public void initWindow() {
        this.setSize(this.getWidth(), this.getHeight());
        this.setTitle(this.getTitle());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.getContentPane().add(this.boardPanel);

        this.setLocationByPlatform(true);

        // enregistrement du détecteur d'entrée clavier
        Keyboard keyboard = new Keyboard();
        this.addKeyListener(keyboard);
        this.getController().setKeyboard(keyboard);

        this.setVisible(true);

    }


    public void update(Observable observable, Object o) {

    }


}
