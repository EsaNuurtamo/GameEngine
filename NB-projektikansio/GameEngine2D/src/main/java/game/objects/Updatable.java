/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

import game.gui.GamePanel;
import game.gameLogic.GameHandler;
import game.gameLogic.GameState;
import game.gameLogic.PlayState;
import java.util.ArrayList;

/**
 * Tämän rajapinnan toteuttavat ne objektit
 * jotka sisältävät läpikäytävää logiikkaa
 * 
 * Tällä hetkellä kaikki objektit perivät tämän luokan
 * mutta tulevaisuudessa myös staattisia objekteja(esim. pelaajan asettama seinä)
 */
public interface Updatable {
    
    /**
     * Sisältää logiikan jonka mukaan objektin arvot päivittyvät
     * @param state 
     */
    void update(PlayState state);
    
}
