package fr.space.models;

import fr.space.classes.Entity;
import fr.space.classes.Game;

import java.util.ArrayList;
import java.util.List;

public class ModelSpace extends Model {

    private Game game;

    public ModelSpace(Game game) {
        this.setGame(game);
    }

    public Game getGame() {
        return this.game;
    }


    public void setGame(Game game) {
        this.game = game;
    }


    public List<Entity> getEntities() {
        List<Entity> entities = new ArrayList<Entity>();

        entities.addAll(this.getGame().getExplosions());
        entities.addAll(this.getGame().getWave().getAliens());
        entities.addAll(this.getGame().getBullets());
        entities.add(this.getGame().getPlayer());

        return entities;
    }
    
}
