package game;

import game.gui.GamePanel;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Main {
    
    public static final int BLOCK_SIZE=40;
    
    public static final double ASPECT_RATIO=16.0/9;
    public static final int SCREEN_HEIGHT=400;
    public static final int SCREEN_WIDTH=(int)(SCREEN_HEIGHT*ASPECT_RATIO);
    public static final int SCALE=2;
    
    public static Thread game;
    
    public static void main( String[] args ){
        JFrame frame = new JFrame();
        GamePanel gamePanel=new GamePanel();  
        
        frame.setPreferredSize(new Dimension(SCREEN_WIDTH*SCALE, SCREEN_HEIGHT*SCALE));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(gamePanel);  
        frame.pack();
        frame.setVisible(true);
        
        game=new Thread((Runnable)gamePanel);
        game.run();
        
    }
}
