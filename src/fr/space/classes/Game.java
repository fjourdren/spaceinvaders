package fr.space.classes;

import fr.space.views.BoardPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Game implements Runnable {
    private Keyboard keyboard;

    private List<Bullet> bullets = new ArrayList<Bullet>();
    private List<Alien> aliens = new ArrayList<Alien>();
    private Spaceship player;

    private boolean gameIsLoose = false;

    private int xSize;
    private int ySize;

    private boolean running = false;

    private BoardPanel boardPanel;

    public Game(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;

        Sprite sprite = new Sprite("/home/flavien/Dropbox/projet_space_invaders/projet_space_invaders/ship.gif");
        Spaceship playerShip = new Spaceship(this, new Position((this.xSize / 2) - sprite.getxDimension(), this.ySize - (sprite.getyDimension() + 10)), 1, sprite);

        this.setPlayer(playerShip);
    }

    public Game(BoardPanel boardPanel, int ySize) {
        this.boardPanel = boardPanel;
        this.ySize = ySize;
    }


    private void calculateIfLoose() {
        for (Alien e: this.getAliens()) {
            int yValueForLoose = this.ySize + e.getSprite().getyDimension();
            if(e.getPosition().getY() >= yValueForLoose) {
                this.gameIsLoose = true;
            }
        }
    }


    public void render() {
        this.boardPanel.repaint();
    }


    public void update(double delta) {

        this.getInput(delta);


        this.getPlayer().update(delta);


        Iterator<Bullet> iterBullets = this.getBullets().iterator();
        while(iterBullets.hasNext()) {
            Bullet b = iterBullets.next();

            b.update(delta);

            if(b.getLife() <= 0) {
                iterBullets.remove();
            }
        }



        Iterator<Alien> iterAliens = this.getAliens().iterator();
        while(iterAliens.hasNext()) {
            Alien a = iterAliens.next();

            a.update(delta);

            if(a.getLife() <= 0) {
                iterBullets.remove();
            }
        }


        this.calculateIfLoose();
    }


    public void getInput(double delta) {
        if(this.getKeyboard().getKey(32)) // shoot (espace)
            this.getPlayer().shoot();

        if(this.getKeyboard().getKey(37)) // left
            this.getPlayer().move(delta, -1);

        if(this.getKeyboard().getKey(39)) // right
            this.getPlayer().move(delta, 1);

        if(this.getKeyboard().getKey(80)) // pause (p)
            this.getPlayer().shoot();
    }


    public void run() {
        this.running = true;


        long initialTime = System.nanoTime();

        final double target_UPS = 128, target_FPS = 120;

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


    public void addBullet(Bullet bullet) {
        this.bullets.add(bullet);
    }

    public void removeBullet(Bullet bullet) {
        this.bullets.remove(bullet);
    }

    public void addAlien(Alien alien) {
        this.aliens.add(alien);
    }

    public void removeAlien(Alien alien) {
        this.aliens.remove(alien);
    }


    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    public List<Alien> getAliens() {
        return aliens;
    }

    public void setAliens(List<Alien> aliens) {
        this.aliens = aliens;
    }

    public Spaceship getPlayer() {
        return player;
    }

    public void setPlayer(Spaceship player) {
        this.player = player;
    }

    public int getxSize() {
        return xSize;
    }

    public void setxSize(int xSize) {
        this.xSize = xSize;
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