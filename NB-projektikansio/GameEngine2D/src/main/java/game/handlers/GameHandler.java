
package game.handlers;

import java.awt.Color;
import java.awt.Graphics2D;

public class GameHandler {
    public static final int MENU_STATE=0;
    public static final int PLAY_STATE=1;
    public static final int PAUSE_STATE=2;
    
    private GameState curState;

    public GameHandler() {
        
    }

    public void setState(int state) {
        curState=null;
        if(state==MENU_STATE){
            curState=new MenuState(this);
        }else if(state==PLAY_STATE){
            curState=new PlayState(this);
        }
    }
    
    
    
    //pelilogiikka
    public void updateGame(){
        curState.update();
        
    }
    //pelin piirto
    public void drawGame(Graphics2D g){
        curState.draw(g);
        
    }
    
}
