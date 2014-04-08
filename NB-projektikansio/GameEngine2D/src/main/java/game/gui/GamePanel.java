
package game.gui;

import game.Main;
import game.gameLogic.PlayState;
import game.gameLogic.GameHandler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 * Luokka GamePanel suoritetaan omana säikeenä
 * Se sisältää main loopin jossa peli päivittyy
 * Piirtäminen tapahtuu kaksivaiheisesti: aluksi buffered imageen 
 * ja sitten vasta buffered image piirtyy GamePanelin perimään Jpaneliin
 */
public class GamePanel extends JPanel implements Runnable{
    public static final int FPS=60;
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
        gameHandler.setState(GameHandler.MENU_STATE);
        keys= new Keys();
        mm=new MouseMovement();
        clicks=new Clicks();
    }
    
    public void init(){
        addKeyListener(keys);
        addMouseMotionListener(mm);
        addMouseListener(clicks);
        
    }
    
    /**
     * Run metodia kutsutaan kun säie aloitetaan mainissa
     * Tämä sisältää pää peliloopin jossa peli päivitetään ja
     * päivitysten nopeus rajoitetaan 60 kertaan sekunnissa wait-komentojen avulla
     */
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
                TimeUnit.MILLISECONDS.sleep(wait);
                
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            //System.out.println(1000000000/(System.nanoTime()-start));
            
        }
    }
    
    /**
     * Draw metodi piirtää bufferedimagen(joka sis. pelin kaikki grafiikat)
     * JPanelin Graphics oliolla
     */
    public void draw(){
        Graphics g2 = getGraphics();
        if(getGraphics()==null)return;
            
        g2.drawImage(image, 0, 0,
                    (int)(Main.WIDTH*Main.SCALE),
                    (int)(Main.HEIGHT*Main.SCALE), null);
        g2.dispose();
        
                
    }
    
}
