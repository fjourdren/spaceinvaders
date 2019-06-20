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
    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        this.keys[e.getKeyCode()] = true;
    }

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
