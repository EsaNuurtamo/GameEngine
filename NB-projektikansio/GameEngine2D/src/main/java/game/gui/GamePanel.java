
package game.gui;

import game.Main;
import game.handlers.PlayState;
import game.handlers.GameHandler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable{
    public static final int FPS=60;
    //yhden pelipäivityksen kesto millisekunneissa
    public static final long tickLength=1000/FPS;
    private BufferedImage image;
    private Graphics2D graphics;
    private GameHandler gameHandler;
    private Keys keys;
    private MouseMovement mm;
    private Clicks clicks;
    
    
    public GamePanel() {
        super();
        super.setBackground(Color.BLACK);
        setFocusable(true);
        requestFocusInWindow();
        image = new BufferedImage(Main.WIDTH, Main.HEIGHT, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) image.getGraphics();
        gameHandler=new GameHandler();
        gameHandler.setState(GameHandler.PLAY_STATE);
        keys= new Keys();
        mm=new MouseMovement();
        clicks=new Clicks();
    }
    
    public void init(){
        addKeyListener(keys);
        addMouseMotionListener(mm);
        addMouseListener(clicks);
        
    }
    
    public void run(){
        init();
        //nanosekunneissa
        long start;
        long elapsed;
        long wait;
        
        //pelin päälooppi
        while(true){
            start=System.nanoTime();
            
            //tähän pelin logiikan läpikäynti
             gameHandler.updateGame();
             gameHandler.drawGame(graphics);
             draw();
            //
           
            elapsed = System.nanoTime() - start;
            wait=tickLength-(elapsed/1000000);
            if(wait<0)wait=1;
            try {
                Thread.sleep(wait);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            //System.out.println(1000000000/(System.nanoTime()-start));
            
        }
        
    }
    public void draw(){
        Graphics g2 = getGraphics();
        if(getGraphics()==null)return;
            
        g2.drawImage(image, 0, 0,
                    (int)(Main.WIDTH*Main.SCALE),
                    (int)(Main.HEIGHT*Main.SCALE), null);
        g2.dispose();
        
                
    }
    
}
