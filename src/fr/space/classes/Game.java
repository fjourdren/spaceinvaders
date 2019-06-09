package fr.space.classes;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Entity> entities = new ArrayList<Entity>();

    private boolean gameIsLoose = false;

    private int ySize;

    private boolean running = false;

    public Game(int ySize) {
        this.ySize = ySize;
    }


    private void calculateIfLoose() {
        for (Entity e: this.entities) {
            if(e instanceof Alien) {
                int yValueForLoose = this.ySize + e.getSprite().getyDimension();
                if(e.getPosition().getY() >= yValueForLoose) {
                    this.gameIsLoose = true;
                }
            }
        }
    }


    public void render() {
        for (Entity e: this.entities) {
            e.render();
        }
    }


    public void update(double delta) {
        for (Entity e: this.entities) {
            e.update(delta);
        }

        this.calculateIfLoose();
    }


    public void getInput() {

    }


    public void run() {
        this.running = true;

        /*long lastLoopTime = System.currentTimeMillis();

        while(this.running) {
            long delta = System.currentTimeMillis() - lastLoopTime;
            lastLoopTime = System.currentTimeMillis();

            this.update();
            this.render();

            try {
                Thread.sleep(10);
            } catch (Exception e) {
                JOptionPane optionPane = new JOptionPane("Erreur sur la gameloop", JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog("Erreur");
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);

                System.exit(0);
            }
        }*/


        long initialTime = System.nanoTime();

        final double target_UPS = 128, target_FPS = 9999;

        final double timeU = 1000000000 / target_UPS;
        final double timeF = 1000000000 / target_FPS;

        double deltaU = 0, deltaF = 0;
        int frames = 0, ticks = 0;

        long timer = System.currentTimeMillis();

        while(this.running) {

            long currentTime = System.nanoTime();
            deltaU += (currentTime - initialTime) / timeU;
            deltaF += (currentTime - initialTime) / timeF;
            initialTime = currentTime;

            if(deltaU >= 1) {
                this.getInput();
                this.update(deltaU);

                ticks++;
                deltaU--;
            }

            if(deltaF >= 1) {
                this.render();

                frames++;
                deltaF--;
            }


            // reset toutes les secondes du nombre de UPS et de FPS
            if(System.currentTimeMillis() - timer > 1000) {
                System.out.println(String.format("UPS: %s, FPS: %s", ticks, frames));

                frames = 0;
                ticks = 0;
                timer += 1000;
            }
        }
    }
}