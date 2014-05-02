
package game.objects;

import game.objects.guns.Bullet;
import game.Main;
import game.gameLogic.GameHandler;
import game.gameLogic.PlayState;
import game.gui.Clicks;
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
    public static PlayState state;
    public static int mapX;
    public static int mapY;
    
    @BeforeClass
    public static void setUpClass() {
        map=new TileMap();
        Clicks clicks=new Clicks();
        Clicks.clicked=true;
        player=new Player(map);
        state=new PlayState(new GameHandler());
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        player.setPoint(new Point(400,400));
        mapX=-400+Main.WIDTH/2;
        mapY=-400+Main.HEIGHT/2;
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void mapLocRightAtStart(){
        assertEquals(new Point(mapX, mapY), map.getLocation());
    }
    
    @Test
    public void movesMapCorrectly(){
        player.setPoint(new Point(500, 500));
        int x=-500+Main.WIDTH/2;
        int y=-500+Main.HEIGHT/2;
        assertEquals(new Point(x,y),map.getLocation());
        
    }
    
    @Test
    public void doesntSetLocIfXtooSmall(){
        int x=(Main.TILE_SIZE+player.width/2)-2;
        player.setPoint(new Point(x,400));
        //testataan pysyykö mapin sijainti samana kuin alussa
        //jos yritetään liian isoa arvoa
        
        assertEquals(new Point(mapX,mapY),map.getLocation());
        
    }
    
    @Test
    public void doesntSetLocIfXtooBig(){
        int x=(Main.TILE_SIZE*(map.mapSize-1)-player.width/2)+2;
        player.setPoint(new Point(x,400));
        assertEquals(new Point(mapX,mapY),map.getLocation());
        
    }
    
    @Test
    public void doesntSetLocIfYtooSmall(){
        int y=(Main.TILE_SIZE+player.height/2)-2;
        player.setPoint(new Point(400,y));
        assertEquals(new Point(mapX,mapY),map.getLocation());
        
    }
    
    @Test
    public void doesntSetLocIfYtooBig(){
        int y=(Main.TILE_SIZE*(map.mapSize-1)-player.height/2)+2;
        player.setPoint(new Point(400, y));
        assertEquals(new Point(mapX,mapY),map.getLocation());
        
        
    }
    @Test
    public void updateDestroys(){
        player.health=0;
        player.update(state);
        assertTrue(player.destroyed);
    }
    
    @Test
    public void updateCollideEnemy(){
        player.setHealth(1);
        state.getObjects().add(new Enemy(new Point(400,410),map));
        player.update(state);
        assertEquals(0,player.getHealth());
    }
    
    public void updateCollideBullet(){
        player.setHealth(1);
        Bullet b=new Bullet(new Point(400,401),map);
        state.getObjects().add(b);
        player.update(state);
        assertEquals(0,player.getHealth());
        assertTrue(b.destroyed);
    }
    
}
