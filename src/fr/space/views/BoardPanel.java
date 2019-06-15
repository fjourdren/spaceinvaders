package fr.space.views;

import fr.space.classes.Entity;
import fr.space.controllers.ControllerSpace;

import java.awt.*;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {

    private ControllerSpace controller;
    private GameOver gameover;
    private Pause pause;

    public BoardPanel(ControllerSpace controller, int width, int height) {
        this.controller = controller;
        this.gameover = new GameOver(this);
        this.pause = new Pause(this);

        this.setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        this.setBackground(Color.BLACK);

        for (Entity e: this.getController().getModel().getEntities()) {
            e.render(g);
        }

        if(this.getController().getModel().getGame().isGameIsLoose())
            this.gameover.render(g);
        else if(this.getController().getModel().getGame().isPause())
            this.pause.render(g);
    }




    public ControllerSpace getController() {
        return controller;
    }

    public void setController(ControllerSpace controller) {
        this.controller = controller;
    }
}
