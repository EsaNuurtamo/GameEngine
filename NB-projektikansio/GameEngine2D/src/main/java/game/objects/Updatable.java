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
 * Tämän rajapinnan toteuttavat ne objektit
 * jotka sisältävät läpikäytävää logiikkaa
 */
public interface Updatable {
    
    /**
     * Sisältää logiikan jonka mukaan objektin arvot päivittyvät
     * @param state 
     */
    void update(PlayState state);
    
}
