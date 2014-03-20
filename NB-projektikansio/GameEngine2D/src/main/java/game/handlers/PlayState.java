/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.handlers;

import game.Main;
import game.map.TileMap;
import game.objects.MapObject;
import game.objects.Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author esa
 */
public class PlayState extends GameState{
    private TileMap tileMap;
    private Player player;
    public PlayState(GameHandler sh) {
        super(sh);
        init();
        
    }
    

    @Override
    public void init() {
        tileMap=new TileMap();
        tileMap.load("/Map.txt");
        player=new Player(tileMap);
        player.setPoint(new Point(400,400));
        
    }

    @Override
    public void update() {
        handleInput();
        
    }

    @Override
    public void draw(Graphics2D g) {
        if(tileMap==null)return;
        tileMap.draw(g);
        player.draw(g);
        /*g.setColor(Color.BLUE);
        g.drawLine(Main.SCREEN_WIDTH/2, 0, Main.SCREEN_WIDTH/2, Main.SCREEN_HEIGHT);
        g.drawLine(0, Main.SCREEN_HEIGHT/2, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT/2);*/
    }

    @Override
    public void handleInput() {
        if(Keys.keyState[Keys.UP_KEY]){
            player.move(0, -5);
        }
        if(Keys.keyState[Keys.DOWN_KEY]){
            player.move(0, 5);
        }
        if(Keys.keyState[Keys.LEFT_KEY]){
            player.move(-5, 0);
        }
        if(Keys.keyState[Keys.RIGHT_KEY]){
            player.move(5, 0);
        }
        Keys.update();
    }

    
    
}
