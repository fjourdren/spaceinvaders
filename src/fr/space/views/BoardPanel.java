package fr.space.views;

import fr.space.classes.Entity;
import fr.space.controllers.ControllerSpace;

import java.awt.*;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {

    private ControllerSpace controller;
    private Score score;
    private Pause pause;

    public BoardPanel(ControllerSpace controller) {
        this.controller = controller;
        this.score = new Score(this);
        this.pause = new Pause(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(Color.BLACK);

        for (Entity e: this.getController().getModel().getEntities()) {
            e.render(g);
        }

        this.score.render(g);

        if(this.getController().getModel().getGame().isPause()) {
            this.pause.render(g);
        }
    }




    public ControllerSpace getController() {
        return controller;
    }

    public void setController(ControllerSpace controller) {
        this.controller = controller;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
