/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.handlers;

import java.awt.Graphics2D;

/**
 * Luokka sisältää toiminnalisuuden joka yhditää kaikkia pelitiloja
 * 
 */
public abstract class GameState {
    protected GameHandler gameHandler;

    public GameState(GameHandler sh) {
        this.gameHandler=sh;
    }
    
    public abstract void init();
    public abstract void update();
    public abstract void draw(Graphics2D g);
    public abstract void handleInput();
    
    
}
