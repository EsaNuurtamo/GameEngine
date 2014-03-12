
package game.handlers;

import java.awt.Color;
import java.awt.Graphics2D;

public class GameHandler {

    public GameHandler() {
    }
    //pelilogiikka
    public void updateGame(){
        
    }
    //pelin piirto
    public void drawGame(Graphics2D g){
        g.setColor(Color.RED);
        g.draw3DRect(50, 50, 40, 40, true);
        
    }
    
}
