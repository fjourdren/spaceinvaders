package fr.space.Models;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    private boolean keys[];


    /*
     * Constructors
     * */
    public Keyboard() {
        this.setKeys(new boolean[65535]);
    }



    /*
     * Methods
     * */
    // event qui récupère quand une touche est appuyé
    public void keyTyped(KeyEvent e) {
    }

    // event qui va nous mettre la valeur de la touche appuyé à vrai dans un tableau
    public void keyPressed(KeyEvent e) {
        this.keys[e.getKeyCode()] = true;
    }

    // event qui va nous mettre la valeur de la touche laché à faux dans un tableau
    public void keyReleased(KeyEvent e) {
        this.keys[e.getKeyCode()] = false;
    }



    /*
     * GETTER & SETTER
     * */
    public boolean getKey(int key) {
        return keys[key];
    }

    public void setKey(int key, boolean value) {
        keys[key] = value;
    }

    public boolean[] getKeys() {
        return keys;
    }

    public void setKeys(boolean[] keys) {
        this.keys = keys;
    }
}
