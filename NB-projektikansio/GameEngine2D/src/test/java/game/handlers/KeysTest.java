/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.handlers;

import java.awt.event.KeyEvent;
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
public class KeysTest {
    private static Keys keys;
    
    public KeysTest() {
        keys=new Keys();
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
    /*@Test
    public void atFirstNothingPressed(){
        
        assertFalse(keys.anyKeyPress());
        
    }
    @Test
    public void afterPress(){
        keys.setKey(KeyEvent.VK_S, true);
        assertTrue(keys.isPressed(Keys.DOWN_KEY));
        
        
        
    }
    @Test
    public void afterUpdate(){
        keys.setKey(KeyEvent.VK_S, true);
        keys.update();
        
        assertFalse(keys.isPressed(Keys.DOWN_KEY));
        
    }
    @Test
    public void afterRelease(){
        keys.setKey(KeyEvent.VK_S, true);
        keys.update();
        keys.setKey(KeyEvent.VK_S, false);
        assertFalse(keys.isPressed(Keys.DOWN_KEY));
    }*/
    
}
