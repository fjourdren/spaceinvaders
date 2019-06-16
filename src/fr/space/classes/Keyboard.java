package fr.space.classes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    private boolean keys[];

    public Keyboard() {
        this.setKeys(new boolean[65535]);
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        this.keys[e.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent e) {
        this.keys[e.getKeyCode()] = false;
    }

    public boolean getKey(int key) {
        return keys[key];
    }


    public boolean[] getKeys() {
        return keys;
    }

    public void setKeys(boolean[] keys) {
        this.keys = keys;
    }
}
