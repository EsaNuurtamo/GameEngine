
package game.map;

import java.awt.Point;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class TileMapTest {
    private static TileMap map;
    
    
    @BeforeClass
    public static void setUpClass() {
        map=new TileMap();
        
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
    
    @Test
    public void ColAndRowWhenXandYNegative(){
        map.setLocation(new Point(-50,-50));
        assertEquals(50/map.tileSize, map.getStartCol());
        assertEquals(50/map.tileSize, map.getStartRow());
    }
    
    @Test
    public void ColAndRowWhenXandYPositive(){
        map.setLocation(new Point(50,50));
        assertEquals((int)-Math.ceil(50.0/map.tileSize), map.getStartCol());
        assertEquals((int)-Math.ceil(50.0/map.tileSize), map.getStartRow());
    }
    
    @Test
    public void ColAndRowWhenXpositiveAndYnegative(){
        map.setLocation(new Point(60,-70));
        assertEquals((int)-Math.ceil(60.0/map.tileSize), map.getStartCol());
        assertEquals(70/map.tileSize, map.getStartRow());
    }
    
    @Test
    public void mapLoadingFillsArrays(){
        map.load("/Map.txt");
        int i=0;
        for(int x=0;x<map.mapSize;x++){
            for(int y=0;y<map.mapSize;y++){
                if(map.getMap()[x][y]==1)i++;
            }
        }
        assertEquals(156,i);
    }
    
}
