package fr.space.classes;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class Wave {

    private Game game;
    private Alien alien;

    private List<Alien> aliens = new ArrayList<Alien>();

    private int margeBeetweenAliens = 0;
    private int directionX, directionY;

    private int row, col;

    public Wave(Game game, int row, int col, Alien alien) {
        this.game = game;

        this.row = row;
        this.col = col;

        this.alien = alien;

        this.directionX = 1;
        this.directionY = 0;
    }


    public Wave(Wave wave) {
        this.game = wave.getGame();
        this.alien = wave.getAlien();
        this.margeBeetweenAliens = wave.getMargeBeetweenAliens();

        this.directionX = wave.getDirectionX();
        this.directionY = wave.getDirectionY();
    }


    public void spawn() {

        for(int i = 0; i < this.getCol(); i++) {
            for(int j = 0; j < this.getRow(); j++) {
                Position pos = new Position(i * (Sprite.getSpriteAlien().getxDimension() + margeBeetweenAliens), j * (Sprite.getSpriteAlien().getyDimension() + margeBeetweenAliens));

                Alien tmpAlien = new Alien(this.getAlien());
                tmpAlien.setPosition(pos);

                this.addAlien(tmpAlien);
            }
        }

    }


    public int minXAlien() {
        int output = 999999;

        Iterator<Alien> iterAliens = this.getAliens().iterator();
        while(iterAliens.hasNext()) {
            Alien a = iterAliens.next();

            if(a.getPosition().getX() < output) {
                output = a.getPosition().getX();
            }
        }

        return output;
    }


    public int maxXAlien() {
        int output = -999999;

        Iterator<Alien> iterAliens = this.getAliens().iterator();
        while(iterAliens.hasNext()) {
            Alien a = iterAliens.next();

            if(a.getPosition().getX() > output) {
                output = a.getPosition().getX() + a.getSprite().getxDimension();
            }
        }

        return output;
    }


    public void update(double delta) {

        this.setDirectionY(0);

        if(minXAlien() < 0 || maxXAlien() > this.getGame().getxSize()) {
            this.directionX = -directionX;
            this.directionY = 5;
        }


        Iterator<Alien> iterAliens = this.getAliens().iterator();
        while(iterAliens.hasNext()) {
            Alien a = iterAliens.next();

            a.update(delta);

            a.move(delta, this.getDirectionX(), this.getDirectionY());

            if(a.getLife() <= 0) {
                iterAliens.remove();
            }
        }
    }


    public void addAlien(Alien alien) {
        this.getAliens().add(alien);
    }

    public void removeAlien(Alien alien) {
        this.getAliens().remove(alien);
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
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
