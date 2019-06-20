package fr.space.Models;

import fr.space.Utils.GameState;
import fr.space.Views.BoardPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class GameModel extends Model implements Runnable{
    private Keyboard keyboard;

    private List<Bullet> bullets;
    private List<Explosion> explosions;
    private Spaceship player;

    private int nbAlien = 0;

    private Wave defaultWave;
    private Alien defaultAlien;

    private Wave wave;

    private Sprite background;

    private int level = 0;

    private int score = 0;

    private int xSize;
    private int ySize;

    private final int menuSize = 50;

    private boolean runningUpdate= false;
    private GameState gameState = GameState.RUNNING;

    private BoardPanel boardPanel;

    public GameModel(int xSize, int ySize) {
        super();

        this.setxSize(xSize);
        this.setySize(ySize);


        // default enemy's wave
        Alien alienDef = new Alien(null, 1, Sprite.getSpriteAlien());
        this.setDefaultAlien(alienDef);
        this.getDefaultAlien().setSpeed(1);

        // create default wave
        Wave waveDef = new Wave(this, 3, 5, this.getDefaultAlien());
        this.setDefaultWave(waveDef);

        // set background
        this.setBackground(Sprite.getBackground());

        this.reset();
    }

    public GameModel(BoardPanel boardPanel, int ySize) {
        this.setBoardPanel(boardPanel);
        this.setySize(ySize);
    }


    public void reset() {
        this.setBullets(new ArrayList<Bullet>());
        this.setExplosions(new ArrayList<Explosion>());

        this.setScore(0);
        this.setLevel(0);

        this.setWave(new Wave(this.getDefaultWave()));

        Position playerPosition = new Position((this.getxSize() / 2) - Sprite.getSpriteShip().getxDimension(), this.getySize() - (Sprite.getSpriteShip().getyDimension() + 10 + this.getMenuSize())); // margin + menu size
        Spaceship playerShip = new Spaceship(this, playerPosition, 1, Sprite.getSpriteShip());
        this.setPlayer(playerShip);

        this.setGameState(GameState.RUNNING);
    }


    public void run() {
        this.setRunningUpdate(true);


        long initialTime = System.nanoTime();

        final double target_UPS = 128, target_FPS = 500;

        final double timeU = 1000000000 / target_UPS;
        final double timeF = 1000000000 / target_FPS;

        double deltaU = 0, deltaF = 0;
        int frames = 0, ticks = 0;

        long timer = System.currentTimeMillis();

        while(this.isRunningUpdate()) {

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
        this.getBoardPanel().repaint();
    }


    public void getInput(double delta) {
        if(!this.isPause() && !this.isLose()) {
            if (this.getKeyboard().getKey(32)) // shoot (espace)
                this.getPlayer().shoot();

            if (this.getKeyboard().getKey(37)) // left
                this.getPlayer().move(delta, -1, 0);

            if (this.getKeyboard().getKey(39)) // right
                this.getPlayer().move(delta, 1, 0);
        }


        if (!this.isLose() && this.getKeyboard().getKey(80)) { // pause (P)
            this.turnPause();


            // fix mul
            boolean newKeysValues[] = this.getKeyboard().getKeys();
            newKeysValues[80] = false;
            this.getKeyboard().setKeys(newKeysValues);
        }


        if(this.isLose()) {
            if(this.getKeyboard().getKey(82)) { // start game after a game over (R)
                this.reset();
            }
        }
    }


    public void spawnEnemies() {
        if(this.getWave().getAliens().size() == 0) {
            // default enemy's wave
            Alien alien = new Alien(null, 1, Sprite.getSpriteAlien());
            alien.setSpeed(1);

            Wave waveToSet = new Wave(this.getDefaultWave());
            this.setWave(waveToSet);

            this.getWave().spawn();

            this.setLevel(this.getLevel() + 1);
        }
    }


    private void calculateIfLoose() {
        this.setGameState(GameState.RUNNING);

        // si le joueur est mort alors la game est perdu
        if(this.getPlayer().getLife() <= 0) {
            this.setGameState(GameState.LOSE);
        } else { // si un alien a atteint le bas de l'écran alors la game est perdu
            for (Alien e: this.getWave().getAliens()) {
                int yValueForLoose = this.getySize() - e.getSprite().getyDimension();
                if(e.getPosition().getY() - this.getMenuSize() >= yValueForLoose) {
                    this.setGameState(GameState.LOSE);
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

        if(!this.isPause() && !this.isLose()) {

            this.spawnEnemies();


            this.getPlayer().update(delta);


            // update explosions
            Iterator<Explosion> iterExplosions = this.getExplosions().iterator();
            while (iterExplosions.hasNext()) {
                Explosion e = iterExplosions.next();

                e.update(delta);

                if (e.getLife() <= 0) {
                    iterExplosions.remove();
                }
            }


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

            this.calculateIfLoose();
        }
    }


    public boolean isPause() {
        return this.getGameState() == GameState.PAUSE;
    }


    public boolean isLose() {
        return this.getGameState() == GameState.LOSE;
    }


    public void turnPause() {
        if(this.isPause()) {
            this.setGameState(GameState.RUNNING);
        } else {
            this.setGameState(GameState.PAUSE);
        }
    }


    public List<Entity> getEntities() {
        List<Entity> entities = new ArrayList<Entity>();

        entities.addAll(this.getExplosions());
        entities.addAll(this.getWave().getAliens());
        entities.addAll(this.getBullets());
        entities.add(this.getPlayer());

        return entities;
    }


    public void addBullet(Bullet bullet) {
        this.getBullets().add(bullet);
    }

    public void removeBullet(Bullet bullet) {
        this.getBullets().remove(bullet);
    }

    public void addExplosion(Explosion explosion) {
        this.getExplosions().add(explosion);
    }

    public void removeExplosion(Explosion explosion) {
        this.getExplosions().remove(explosion);
    }

    public void addScore(int scoreAdd) {
        this.setScore(this.getScore() + scoreAdd);
    }



    public void setScore(int score) {
        this.score = score;
        this.setChanged();
        this.notifyObservers();
    }

    public void setLevel(int level) {
        this.level = level;
        this.setChanged();
        this.notifyObservers();
    }

    public void setNbAlien(int nbAlien) {
        this.nbAlien = nbAlien;
        this.setChanged();
        this.notifyObservers();
    }



    public int getScore() {
        return score;
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

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public void setBoardPanel(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
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

    public int getLevel() {
        return level;
    }

    public Sprite getBackground() {
        return background;
    }

    public void setBackground(Sprite background) {
        this.background = background;
    }

    public int getMenuSize() {
        return menuSize;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public boolean isRunningUpdate() {
        return runningUpdate;
    }

    public void setRunningUpdate(boolean runningUpdate) {
        this.runningUpdate = runningUpdate;
    }

    public List<Explosion> getExplosions() {
        return explosions;
    }

    public void setExplosions(List<Explosion> explosions) {
        this.explosions = explosions;
    }

    public int getNbAlien() {
        return nbAlien;
    }
}