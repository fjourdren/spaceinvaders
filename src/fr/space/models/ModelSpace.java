package fr.space.models;

import fr.space.classes.Entity;
import fr.space.classes.Game;

import java.util.List;

public class ModelSpace extends Model {

    private Game game;

    public ModelSpace(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return this.game;
    }


    public void setGame(Game game) {
        this.game = game;
    }


    public void setEntities(List<Entity> entities) {
        this.getGame().setEntities(entities);
    }


    public List<Entity> getEntities() {
        return this.getGame().getEntities();
    }
    
}
