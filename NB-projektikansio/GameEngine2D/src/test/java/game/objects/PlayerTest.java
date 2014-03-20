
package game.objects;

import game.Main;
import game.map.TileMap;
import java.awt.Point;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class PlayerTest {
    private static TileMap map;
    public static Player player;
    public static int mapX;
    public static int mapY;
    
    @BeforeClass
    public static void setUpClass() {
        map=new TileMap();
        player=new Player(map);
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        player.setPoint(new Point(400,400));
        mapX=-400+Main.SCREEN_WIDTH/2;
        mapY=-400+Main.SCREEN_HEIGHT/2;
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void movesMapCorrectly(){
        player.setPoint(new Point(500, 500));
        int x=-500+Main.SCREEN_WIDTH/2;
        int y=-500+Main.SCREEN_HEIGHT/2;
        assertEquals(new Point(x,y),map.getLocation());
        
    }
    
    @Test
    public void doesntSetLocIfXtooSmall(){
        int x=Main.BLOCK_SIZE+player.width/2-1;
        player.setPoint(new Point(x,400));
        //testataan pysyykö mapin sijainti samana kuin alussa
        //jos yritetään liian isoa arvoa
        
        assertEquals(new Point(mapX,mapY),map.getLocation());
        
    }
    
    @Test
    public void doesntSetLocIfXtooBig(){
        int x=Main.BLOCK_SIZE*(map.mapSize-1)-player.width/2+1;
        player.setPoint(new Point(x,400));
        assertEquals(new Point(mapX,mapY),map.getLocation());
        
    }
    
    @Test
    public void doesntSetLocIfYtooSmall(){
        int y=Main.BLOCK_SIZE+player.height/2-1;
        player.setPoint(new Point(400,y));
        assertEquals(new Point(mapX,mapY),map.getLocation());
        
    }
    
    @Test
    public void doesntSetLocIfYtooBig(){
        int y=Main.BLOCK_SIZE*(map.mapSize-1)-player.height/2+1;
        player.setPoint(new Point(400, y));
        assertEquals(new Point(mapX,mapY),map.getLocation());
        
        
    }
}
