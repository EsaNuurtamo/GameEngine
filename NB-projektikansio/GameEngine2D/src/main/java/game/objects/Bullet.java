/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

import game.Main;
import game.handlers.Clicks;
import game.handlers.GameState;
import game.handlers.MouseMovement;
import game.handlers.PlayState;
import game.map.TileMap;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Point;
import java.util.Vector;

/**
 *
 * @author esa
 */
public class Bullet extends MapObject implements Updatable{
    private int speed=(int)(Main.TILE_SIZE*1.5);
    public Bullet(TileMap map) {
        this(null,map);
        
        
    }
    
    public Bullet(Point p, TileMap map){
        super(map);
        super.point=p;
        setWidth(Main.TILE_SIZE/4);
        setHeight(Main.TILE_SIZE/4);
        type=MapObject.BULLET;
    }
    
    public void calculateVector(){
        int x=Clicks.x-map.getX();
        int y=Clicks.y-map.getY();
        Point p=new Point(x,y);
        
        double divider=(point.distance(p)/speed);
        dX=(int)((x-getX())/divider);
        dY=(int)((y-getY())/divider);
    }

    @Override
    public void update(PlayState state) {
        
        if(state.getPlayer().point.distance(point)>1000||collides)destroyed=true;
        if(ticker%5==0){
            move(dX,dY);
        }
    }
    
    public void draw(Graphics2D g){
        g.setColor(Color.BLACK);
        g.fillOval(map.getX()+getX()-width/2, map.getY()+getY()-height/2, width, height);
        g.setColor(Color.GREEN);
        g.fillOval(map.getX()+getX()-width/2, map.getY()+getY()-height/2, width-3, height-3);
        
    }
    
}
