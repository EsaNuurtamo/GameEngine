
package game.handlers;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * GameHandler huolehtii siitä mikä osa pelistä on kulloinkin 
 * aktiivisena. Esim. jos ollaan pelissä (curState=PlayState)
 * ja painetaan esc niin siirrytään valikkoon (GameHandler 
 * vaihtaa curState=menuState)
 */
public class GameHandler {
    public static final int MENU_STATE=0;
    public static final int PLAY_STATE=1;
    public static final int PAUSE_STATE=2;
    
    private GameState curState;

    public GameHandler() {
        
    }
    
    /**
     * Asettaa lukua vastaavan pelitilan aktiiviseksi
     * @param state saadaan toiselta luokalta
     */
    public void setState(int state) {
        curState=null;
        if(state==MENU_STATE){
            curState=new MenuState(this);
        }else if(state==PLAY_STATE){
            curState=new PlayState(this);
        }
    }
    
    /**
     * Päivittää aktiivisen pelitilan
     */
    public void updateGame(){
        curState.update();
        
    }
    
    /**
     * Piirtää aktiivisen pelitilan
     * @param g on GamePanelissa olevan BufferedImagen 
     * krafiikkaolio
     */
    public void drawGame(Graphics2D g){
        
        curState.draw(g);
        
    }
    
}
