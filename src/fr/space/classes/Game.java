package fr.space.classes;

import fr.space.views.BoardPanel;

import java.util.ArrayList;
import java.util.List;

public class Game implements Runnable {
    private List<Entity> entities = new ArrayList<Entity>();

    private boolean gameIsLoose = false;

    private int ySize;

    private boolean running = false;

    private BoardPanel boardPanel;

    public Game(int ySize) {
        this.ySize = ySize;
        this.entities.add(new Spaceship(new Position(24, 24), 1, "/home/flavien/Dropbox/projet_space_invaders/projet_space_invaders/ship.gif"));
    }

    public Game(BoardPanel boardPanel, int ySize) {
        this.boardPanel = boardPanel;
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
        this.boardPanel.repaint();
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


    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public void setBoardPanel(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    public boolean isGameIsLoose() {
        return gameIsLoose;
    }

    public void setGameIsLoose(boolean gameIsLoose) {
        this.gameIsLoose = gameIsLoose;
    }

    public int getySize() {
        return ySize;
    }

    public void setySize(int ySize) {
        this.ySize = ySize;
    }
}