package fr.space.classes;

import fr.space.views.BoardPanel;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Game implements Runnable {
    private Keyboard keyboard;

    private List<Bullet> bullets = new ArrayList<Bullet>();
    //private List<Alien> aliens = new ArrayList<Alien>();
    private Spaceship player;

    private Wave defaultWave;
    private Alien defaultAlien;

    private Wave wave;

    private int level = 0;

    private boolean gameIsLoose = false;
    private int score = 0;

    private int xSize;
    private int ySize;

    private boolean running = false;
    private boolean pause = false;

    private BoardPanel boardPanel;

    public Game(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;


        // default enemy's wave
        this.defaultAlien = new Alien(null, 1, Sprite.getSpriteAlien());
        this.defaultAlien.setSpeed(0.0000000000001f);

        this.defaultWave = new Wave(this, 3, 5, this.defaultAlien);

        this.wave = new Wave(this.defaultWave);



        // spawn player
        Position playerPosition = new Position((this.xSize / 2) - Sprite.getSpriteShip().getxDimension(), this.ySize - (Sprite.getSpriteShip().getyDimension() + 10));
        Spaceship playerShip = new Spaceship(this, playerPosition, 1, Sprite.getSpriteShip());

        this.setPlayer(playerShip);
    }

    public Game(BoardPanel boardPanel, int ySize) {
        this.boardPanel = boardPanel;
        this.ySize = ySize;
    }


    public void run() {
        this.running = true;


        long initialTime = System.nanoTime();

        final double target_UPS = 128, target_FPS = 500;

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


    public void render() {
        this.boardPanel.repaint();
    }


    public void getInput(double delta) {
        if(!this.isPause()) {
            if (this.getKeyboard().getKey(32)) // shoot (espace)
                this.getPlayer().shoot();

            if (this.getKeyboard().getKey(37)) // left
                this.getPlayer().move(delta, -1, 0);

            if (this.getKeyboard().getKey(39)) // right
                this.getPlayer().move(delta, 1, 0);
        }


        if (this.getKeyboard().getKey(80)) { // pause (p)
            if(this.isPause()) {
                this.setPause(false);
            } else {
                this.setPause(true);
            }


            // fix mul
            boolean newKeysValues[] = this.getKeyboard().getKeys();
            newKeysValues[80] = false;
            this.getKeyboard().setKeys(newKeysValues);
        }


    }


    public void spawnEnemies() {
        if(this.wave.getAliens().size() == 0) {
            // default enemy's wave
            Alien alien = new Alien(null, 1, Sprite.getSpriteAlien());
            alien.setSpeed(0.0000000000001f);

            this.wave = new Wave(this, 3, 5, alien);

            this.wave.spawn();

            this.setLevel(this.getLevel() + 1);
        }
    }


    private void calculateIfLoose() {
        this.gameIsLoose = false;

        if(this.getPlayer().getLife() <= 0) {
            this.gameIsLoose = true;
        } else {
            for (Alien e: this.getWave().getAliens()) {
                int yValueForLoose = this.ySize - e.getSprite().getyDimension();
                if(e.getPosition().getY() >= yValueForLoose) {
                    this.gameIsLoose = true;
                }
            }
        }

    }


    public void collisionDetection() {
        Iterator<Alien> iterAliensCollisionPlayer = this.getWave().getAliens().iterator();
        while (iterAliensCollisionPlayer.hasNext()) {
            Alien a = iterAliensCollisionPlayer.next();

            if (a.collisionWith(this.getPlayer())) {
                a.removeLife(1);
                this.getPlayer().removeLife(1);
            }
        }


        // colission calculs
        Iterator<Bullet> iterBulletsCollision = this.getBullets().iterator();
        Iterator<Alien> iterAliensCollision = this.getWave().getAliens().iterator();

        while (iterBulletsCollision.hasNext()) {
            Bullet b = iterBulletsCollision.next();

            while (iterAliensCollision.hasNext()) {
                Alien a = iterAliensCollision.next();

                if (a.collisionWith(b)) {
                    a.removeLife(1);
                    b.removeLife(1);

                    if(a.getLife() <= 0) {
                        this.addScore(a.getScore());
                    }
                }
            }
        }
    }


    public void update(double delta) {

        this.getInput(delta);

        if(!this.isPause()) {

            this.spawnEnemies();


            this.getPlayer().update(delta);


            // update bullets
            Iterator<Bullet> iterBullets = this.getBullets().iterator();
            while (iterBullets.hasNext()) {
                Bullet b = iterBullets.next();

                b.update(delta);

                if (b.getLife() <= 0) {
                    iterBullets.remove();
                }
            }


            this.getWave().update(delta);

            this.collisionDetection();


            if(this.getPlayer().getLife() <= 0) {
                // "détruire le joueur"
            }

            this.calculateIfLoose();
            if (this.gameIsLoose) {
                System.out.println("loose");
            }
        }
    }


    public void addBullet(Bullet bullet) {
        this.bullets.add(bullet);
    }

    public void removeBullet(Bullet bullet) {
        this.bullets.remove(bullet);
    }

    public void addScore(int scoreAdd) {
        this.setScore(this.getScore() + scoreAdd);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public Wave getDefaultWave() {
        return defaultWave;
    }

    public void setDefaultWave(Wave defaultWave) {
        this.defaultWave = defaultWave;
    }

    public Alien getDefaultAlien() {
        return defaultAlien;
    }

    public void setDefaultAlien(Alien defaultAlien) {
        this.defaultAlien = defaultAlien;
    }

    public Wave getWave() {
        return wave;
    }

    public void setWave(Wave wave) {
        this.wave = wave;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}