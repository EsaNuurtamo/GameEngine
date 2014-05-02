/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gameLogic;

import game.gameLogic.PlayState;
import game.gameLogic.GameHandler;
import game.gui.Clicks;
import game.objects.guns.Bullet;
import game.objects.Enemy;
import game.objects.MapObject;
import game.objects.Spawner;
import java.awt.Point;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author esa
 */
public class PlayStateTest {
    private GameHandler gh;
    private PlayState ps;
    
    public PlayStateTest() {
        gh=new GameHandler();
        ps=new PlayState(gh);
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testPlayStateConstructor() {
        assertFalse(ps.getPlayer()==null);
        assertFalse(ps.getTileMap()==null);
        assertFalse(ps.getObjects()==null);
        assertFalse(ps.getNewObjects()==null);
        assertFalse(ps.getObjects().isEmpty());
    }
    
    @Test
    public void addingNewObjectsWorks(){
        int first=ps.getObjects().size();
        addObjects();
        ps.updateObjects();
        
        assertEquals(first+2, ps.getObjects().size());
        assertEquals(0, ps.getNewObjects().size());
        
        //uusi
        
    }
    @Test
    public void updateAddingObjects(){
        int first=ps.getObjects().size()-1;
        addObjects();
        ps.updateObjects();
        MapObject obj1=ps.getObjects().get(first+1);
        MapObject obj2=ps.getObjects().get(first+2);
        Point p1=obj1.getPoint();
        Point p2=obj2.getPoint();
        
        ps.updateObjects();
        
        assertTrue(obj1.getTicker()>0);
        assertTrue(obj2.getTicker()>0);
        assertNotSame(p1, obj1.getPoint());
        assertNotSame(p2, obj2.getPoint());
    }
    public void addObjects(){
        MapObject one=new Enemy(new Point(150,150),ps.getTileMap());
        MapObject two=new Bullet(new Point(70,70), ps.getTileMap());
        ps.getNewObjects().add(one);
        ps.getNewObjects().add(two);
       
    }
    @Test
    public void updateSpawnerInteraction(){
        Spawner s=new Spawner(new Point(1400,1400),ps.getTileMap(),MapObject.ENEMY);
        ps.getObjects().add(s);
        
    }
}
