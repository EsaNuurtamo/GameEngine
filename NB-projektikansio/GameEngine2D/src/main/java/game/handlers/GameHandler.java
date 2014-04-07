
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
    
    private boolean paused=false;
    private GameState curState;
    private GameState pauseState;

    public GameHandler() {
        pauseState=new PauseState(this);
    }
    
    /**
     * Asettaa lukua vastaavan pelitilan aktiiviseksi
     * @param state saadaan toiselta luokalta
     */
    public void setState(int state) {
        
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
        if(paused){
            pauseState.update();
            return;
        }
        curState.update();
        
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isPaused() {
        return paused;
    }
    
    /**
     * Piirtää aktiivisen pelitilan
     * @param g on GamePanelissa olevan BufferedImagen 
     * krafiikkaolio
     */
    public void drawGame(Graphics2D g){
        if(paused){
            pauseState.draw(g);
           
            
        }else{
            
            curState.draw(g);
        }
        
        
        
        
    }
    
}
