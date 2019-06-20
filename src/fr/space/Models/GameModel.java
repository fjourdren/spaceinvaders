package fr.space.Models;

import fr.space.Utils.GameState;
import fr.space.Views.BoardPanel;

import java.util.ArrayList;
import java.util.List;

public class GameModel extends Model {
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

    private GameState gameState = GameState.RUNNING;

    private BoardPanel boardPanel;


    /*
     * Constructors
     * */
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



    /*
     * Methods
     * */
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

    public void render() {
        this.getBoardPanel().repaint();
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





    /*
     * GETTER & SETTER avec notify
     * */
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



    /*
     * GETTER & SETTER
     * */
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