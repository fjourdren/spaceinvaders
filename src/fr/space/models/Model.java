package fr.space.models;

import fr.space.classes.Game;

import java.util.Observable;

public abstract class Model extends Observable {
    private Game game;

    public Model(Game game) {
        this.game = game;
    }
}
