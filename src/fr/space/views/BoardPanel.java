package fr.space.views;

import fr.space.classes.Entity;
import fr.space.controllers.ControllerSpace;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {

    private ControllerSpace controller;
    private GameOver gameover;
    private Pause pause;

    public BoardPanel(ControllerSpace controller, int width, int height) {
        this.setController(controller);
        this.setGameover(new GameOver(this));
        this.setPause(new Pause(this));

        this.setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        this.setBackground(Color.BLACK);

        // set background
        BufferedImage backgroundImage = this.getController().getGameModel().getGame().getBackground().getImage();
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);

        for (Entity e: this.getController().getGameModel().getEntities()) {
            e.render(g);
        }

        if(this.getController().getGameModel().getGame().isLose())
            this.getGameover().render(g);
        else if(this.getController().getGameModel().getGame().isPause())
            this.getPause().render(g);

        // fix lags
        Toolkit.getDefaultToolkit().sync();
    }




    public ControllerSpace getController() {
        return controller;
    }

    public void setController(ControllerSpace controller) {
        this.controller = controller;
    }

    public GameOver getGameover() {
        return gameover;
    }

    public void setGameover(GameOver gameover) {
        this.gameover = gameover;
    }

    public Pause getPause() {
        return pause;
    }

    public void setPause(Pause pause) {
        this.pause = pause;
    }
}
