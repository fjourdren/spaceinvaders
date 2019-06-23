package fr.space.Models;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class Wave extends Model {

    private GameModel gameModel;
    private Alien alien;

    private List<Alien> aliens = new ArrayList<Alien>();

    private int margeBeetweenAliens = 5;
    private int directionX, directionY;

    private int row, col;


    /*
     * Constructors
     * */
    public Wave(GameModel gameModel, int row, int col, Alien alien) {
        this.setGameModel(gameModel);

        this.setRow(row);
        this.setCol(col);

        this.setAlien(alien);

        this.setDirectionX(1);
        this.setDirectionY(0);
    }

    // copie d'une autre wave pour avoir une nouvelle signature mémoire
    public Wave(Wave wave) {
        this.setGameModel(wave.getGameModel());

        this.setRow(wave.getRow());
        this.setCol(wave.getCol());

        this.setAlien(wave.getAlien());

        this.setMargeBeetweenAliens(wave.getMargeBeetweenAliens());

        this.setDirectionX(wave.getDirectionX());
        this.setDirectionY(wave.getDirectionY());
    }



    /*
     * Methods
     * */
    // spawn des aliens avec x lignes et y colonnes
    public void spawn() {

        for(int i = 0; i < this.getCol(); i++) {
            for(int j = 0; j < this.getRow(); j++) {
                // calcul de la position de l'alien à ajouter
                Position pos = new Position(i * (Sprite.getSpriteAlien().getxDimension() + this.getMargeBeetweenAliens()), j * (Sprite.getSpriteAlien().getyDimension() + this.getMargeBeetweenAliens()));

                Alien tmpAlien = new Alien(this.getAlien());
                tmpAlien.setPosition(pos);

                this.addAlien(tmpAlien);
            }
        }
    }

    // récupère l'alien avec la plus faible valeur X en position
    public int minXAlien() {
        int output = 999999;

        for (Alien a: this.getAliens()) {
            if(a.getPosition().getX() < output) {
                output = a.getPosition().getX();
            }
        }

        return output;
    }

    // récupère l'alien avec la plus grande valeur X en position
    public int maxXAlien() {
        int output = -999999;

        for (Alien a: this.getAliens()) {
            if(a.getPosition().getX() > output) {
                output = a.getPosition().getX() + a.getSprite().getxDimension();
            }
        }

        return output;
    }

    // lance l'update de tous les aliens de la wave et choisi la direction de la wave
    public void update(double delta) {

        this.setDirectionY(0);

        // détermine si la wave est arrivé en bout d'écran, si c'est le cas: changement de sens et déscente
        if(minXAlien() < 0 || maxXAlien() > this.getGameModel().getxSize()) {
            this.setDirectionX(-this.getDirectionX());
            this.setDirectionY(5);
        }


        // lancement de l'update de tous les aliens de la wave
        Iterator<Alien> iterAliens = this.getAliens().iterator();
        while(iterAliens.hasNext()) {
            Alien a = iterAliens.next();

            a.update(delta);

            a.move(delta, this.getDirectionX(), this.getDirectionY());

            // si l'alien est mort, alors on spawn une explosion
            if(a.getLife() <= 0) {
                Explosion explo = new Explosion(a.getPosition());
                this.getGameModel().addExplosion(explo);
                this.getGameModel().addScore(a.getScore());

                iterAliens.remove();

                this.getGameModel().setNbAlien(this.getAliens().size());
            }
        }
    }

    // modification de la vitesse de tous les aliens
    public void setSpeedToAllAliens(float newSpeed) {
        for (Alien a: this.getAliens()) {
            a.setSpeed(newSpeed);
        }
    }

    // ajout d'un ennemi de la wave
    public void addAlien(Alien alien) {
        this.getAliens().add(alien);
        this.getGameModel().setNbAlien(this.getAliens().size());
    }

    // suppression d'un ennemi de la wave
    public void removeAlien(Alien alien) {
        this.getAliens().remove(alien);
        this.getGameModel().setNbAlien(this.getAliens().size());
    }



    /*
     * GETTER & SETTER
     * */
    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public int getMargeBeetweenAliens() {
        return margeBeetweenAliens;
    }

    public void setMargeBeetweenAliens(int margeBeetweenAliens) {
        this.margeBeetweenAliens = margeBeetweenAliens;
    }

    public int getDirectionX() {
        return directionX;
    }

    public void setDirectionX(int directionX) {
        this.directionX = directionX;
    }

    public int getDirectionY() {
        return directionY;
    }

    public void setDirectionY(int directionY) {
        this.directionY = directionY;
    }

    public Alien getAlien() {
        return alien;
    }

    public void setAlien(Alien alien) {
        this.alien = alien;
    }

    public List<Alien> getAliens() {
        return aliens;
    }

    public void setAliens(List<Alien> aliens) {
        this.aliens = aliens;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
