package fr.space.classes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    private boolean keys[];
    private Spaceship player;

    public Keyboard(Spaceship player) {
        this.keys = new boolean[65535];
        this.player = player;
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
}
