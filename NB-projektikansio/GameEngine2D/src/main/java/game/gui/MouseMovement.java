/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui;

import game.Main;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMovement implements MouseMotionListener{
    public static int x;
    public static int y;
    public static int xOnMap;
    public static int yOnMap;
    
    public boolean dragged=false;

    @Override
    public void mouseDragged(MouseEvent e) {
        
        dragged=true;
        x=(int)(e.getX()/Main.SCALE);
        y=(int)(e.getY()/Main.SCALE);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
        dragged=false;
        x=(int)(e.getX()/Main.SCALE);
        y=(int)(e.getY()/Main.SCALE);
        
    }
    
    
}
