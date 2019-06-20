package fr.space.Controllers;

import fr.space.Models.*;
import fr.space.Utils.GameState;
import fr.space.Views.BoardPanel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ControllerGame extends Controller  implements Runnable {

    private boolean runningUpdate = false;


    /*
    * Constructors
    * */
    public ControllerGame(GameModel gameModel) {
        super(gameModel);
    }



    /*
    * Methods
    * */
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
                this.getGameModel().render();

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

    public void update(double delta) {

        this.getInput(delta);

        if(!this.getGameModel().isPause() && !this.getGameModel().isLose()) {

            this.spawnEnemies();

            this.getGameModel().getPlayer().update(delta);

            // update explosions
            Iterator<Explosion> iterExplosions = this.getGameModel().getExplosions().iterator();
            while (iterExplosions.hasNext()) {
                Explosion e = iterExplosions.next();

                e.update(delta);

                if (e.getLife() <= 0) {
                    iterExplosions.remove();
                }
            }

            // update bullets
            Iterator<Bullet> iterBullets = this.getGameModel().getBullets().iterator();
            while (iterBullets.hasNext()) {
                Bullet b = iterBullets.next();

                b.update(delta);

                if (b.getLife() <= 0) {
                    iterBullets.remove();
                }
            }

            this.getGameModel().getWave().update(delta);

            this.collisionDetection();

            this.calculateIfLoose();
        }
    }

    public void getInput(double delta) {
        if(!this.getGameModel().isPause() && !this.getGameModel().isLose()) {
            if (this.getGameModel().getKeyboard().getKey(32)) // shoot (espace)
                this.getGameModel().getPlayer().shoot();

            if (this.getGameModel().getKeyboard().getKey(37)) // left
                this.getGameModel().getPlayer().move(delta, -1, 0);

            if (this.getGameModel().getKeyboard().getKey(39)) // right
                this.getGameModel().getPlayer().move(delta, 1, 0);
        }

        if (!this.getGameModel().isLose() && this.getGameModel().getKeyboard().getKey(80)) { // pause (P)
            this.getGameModel().turnPause();


            // fix mul
            this.getGameModel().getKeyboard().setKey(80, false);
        }

        if(this.getGameModel().isLose()) {
            if(this.getGameModel().getKeyboard().getKey(82)) { // start game after a game over (R)
                this.getGameModel().reset();
            }
        }
    }

    public void spawnEnemies() {
        if(this.getGameModel().getWave().getAliens().size() == 0) {
            // default enemy's wave
            Alien alien = new Alien(null, 1, Sprite.getSpriteAlien());
            alien.setSpeed(1);

            Wave waveToSet = new Wave(this.getGameModel().getDefaultWave());
            this.getGameModel().setWave(waveToSet);

            this.getGameModel().getWave().spawn();

            this.getGameModel().setLevel(this.getLevel() + 1);
        }
    }

    private void calculateIfLoose() {
        this.getGameModel().setGameState(GameState.RUNNING);

        // si le joueur est mort alors la game est perdu
        if(this.getGameModel().getPlayer().getLife() <= 0) {
            this.getGameModel().setGameState(GameState.LOSE);
        } else { // si un alien a atteint le bas de l'écran alors la game est perdu
            for (Alien e: this.getGameModel().getWave().getAliens()) {
                int yValueForLoose = this.getGameModel().getySize() - e.getSprite().getyDimension();
                if(e.getPosition().getY() - this.getGameModel().getMenuSize() >= yValueForLoose) {
                    this.getGameModel().setGameState(GameState.LOSE);
                }
            }
        }

    }

    public void collisionDetection() {
        for (Entity e1: this.getEntities()) {
            for (Entity e2: this.getEntities()) {
                if (e1.isHitbox() && e2.isHitbox() && e1.collisionWith(e2)) {
                    e1.removeLife(1);
                    e2.removeLife(1);
                }
            }
        }
    }

    public void applyConfigIntervalShoot(long interval) {
        Spaceship.setShootInterval(interval);
    }

    public void applyConfigNumberAliens(int row, int col) {
        Wave wave = this.getGameModel().getDefaultWave();

        wave.setRow(row);
        wave.setCol(col);
    }

    public void applyConfigSpeed(float speed) {
        this.getGameModel().getWave().setSpeedToAllAliens(speed);
    }

    public List<Entity> getEntities() {
        List<Entity> entities = new ArrayList<Entity>();

        entities.addAll(this.getGameModel().getExplosions());
        entities.addAll(this.getGameModel().getWave().getAliens());
        entities.addAll(this.getGameModel().getBullets());
        entities.add(this.getGameModel().getPlayer());

        return entities;
    }



    /*
     * GETTER & SETTER
     * */
    public void setBoard(BoardPanel boardPanel) {
        this.getGameModel().setBoardPanel(boardPanel);
    }


    public BoardPanel getBoardPanel() { return this.getGameModel().getBoardPanel(); }


    public void setKeyboard(Keyboard keyboard) {
        this.getGameModel().setKeyboard(keyboard);
    }


    public int getScore() {
        return this.getGameModel().getScore();
    }


    public int getLevel() {
        return this.getGameModel().getLevel();
    }


    public int getNbAlien() {
        return this.getGameModel().getNbAlien();
    }


    public long getShootInterval() {
        return this.getGameModel().getPlayer().getShootInterval();
    }


    public float getAliensSpeed() {
        return this.getGameModel().getWave().getAlien().getSpeed();
    }


    public int getAliensRow() {
        return this.getGameModel().getWave().getRow();
    }


    public int getAliensCol() {
        return this.getGameModel().getWave().getCol();
    }


    public boolean isRunningUpdate() {
        return runningUpdate;
    }

    public void setRunningUpdate(boolean runningUpdate) {
        this.runningUpdate = runningUpdate;
    }
}
