package game;

import game.gui.GamePanel;
import game.map.TileMap;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Main {
    
    public static final int BLOCK_SIZE=40;
    
    public static final double ASPECT_RATIO=16.0/9;
    public static final int SCREEN_HEIGHT=500;
    public static final int SCREEN_WIDTH=(int)(SCREEN_HEIGHT*ASPECT_RATIO);
    public static final double SCALE=1.8;
    
    public static Thread game;
    
    public static void main( String[] args ){
        
        JFrame frame = new JFrame();
        GamePanel gamePanel=new GamePanel();  
        
        frame.setPreferredSize(new Dimension((int)(SCREEN_WIDTH*SCALE)+18, (int)(SCREEN_HEIGHT*SCALE)+47));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(gamePanel);  
        frame.pack();
        frame.setVisible(true);
        
        game=new Thread((Runnable)gamePanel);
        game.run();
        
    }
}
