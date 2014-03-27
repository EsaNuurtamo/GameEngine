/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

import game.gui.GamePanel;
import game.handlers.GameHandler;
import game.handlers.GameState;
import game.handlers.PlayState;
import java.util.ArrayList;

/**
 *
 * @author esa
 */
public interface Updatable {
    
    void update(PlayState state);
    
}
