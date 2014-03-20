/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.handlers;

import java.awt.Graphics2D;

/**
 *
 * @author esa
 */
public abstract class GameState {
    private GameHandler gameHandler;

    public GameState(GameHandler sh) {
        this.gameHandler=sh;
    }
    
    public abstract void init();
    public abstract void update();
    public abstract void draw(Graphics2D g);
    public abstract void handleInput();
    
    
}
