package game;

import game.gui.GamePanel;
import game.map.TileMap;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Main {
    
    public static final Font DEBUG=new Font("Sarif", Font.PLAIN,20);
    //tällä hetkellä käytä vain parillisia numeroita
    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static final double SCREEN_WIDTH = SCREEN_SIZE.getWidth();
    public static final double SCREEN_HEIGHT = SCREEN_SIZE.getHeight();
    public static final int TILE_SIZE=40;
    
    public static final double ASPECT_RATIO=SCREEN_WIDTH/SCREEN_HEIGHT;
    public static final int HEIGHT=720;
    public static final int WIDTH=(int)(HEIGHT*ASPECT_RATIO);
    public static final double SCALE=SCREEN_HEIGHT/HEIGHT;
            //1;
    
    public static Thread game;
    
    public static void main( String[] args ){
        
        JFrame frame = new JFrame();
        GamePanel gamePanel=new GamePanel();  
        frame.setUndecorated(true);
        frame.setPreferredSize(new Dimension((int)(WIDTH*SCALE), (int)(HEIGHT*SCALE)));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        frame.getContentPane().add(gamePanel);  
        frame.pack();
        frame.setVisible(true);
        
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = 
            Toolkit.getDefaultToolkit().createCustomCursor(
            cursorImg, new Point(0, 0), "blank cursor");
        frame.getContentPane().setCursor(blankCursor);
        frame.setAlwaysOnTop(true);
        
        
        game=new Thread((Runnable)gamePanel);
        game.run();
        
    }
}
