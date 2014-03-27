/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.handlers;

import game.Main;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author esa
 */
public class Clicks implements MouseListener{
    public static int x;
    public static int y;
    public static boolean clicked=false;
    
    public static void resetClicks(){
        clicked=false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        clicked=true;
        x=(int)(e.getX()/Main.SCALE);
        y=(int)(e.getY()/Main.SCALE);
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        clicked=false;
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
