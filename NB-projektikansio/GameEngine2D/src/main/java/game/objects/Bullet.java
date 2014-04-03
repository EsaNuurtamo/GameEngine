/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

import game.Main;
import game.gui.Clicks;
import game.handlers.GameState;
import game.gui.MouseMovement;
import game.handlers.PlayState;
import game.map.TileMap;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Random;


/**
 *
 * @author esa
 */
public class Bullet extends MapObject implements Updatable{
    private int speed=(int)(Main.TILE_SIZE*1.2);
    private Point lastPoint;
    public Bullet(TileMap map) {
        this(null,map);
        setHitBox(new Rectangle(0,0,0,0));
        
    }
    
    public Bullet(Point p, TileMap map){
        super(map);
        super.point=p;
        lastPoint=p;
        
        setWidth(Main.TILE_SIZE/4);
        setHeight(Main.TILE_SIZE/4);
        setColor(Color.DARK_GRAY);
        type=MapObject.BULLET;
        
    }

    public Point getLastPoint() {
        return lastPoint;
    }

    public void setLastPoint(Point lastPoint) {
        this.lastPoint = lastPoint;
    }
    
    public void calculateVector(PlayState state){
        
        int x=Clicks.x-map.getX();
        int y=Clicks.y-map.getY();
        Point p=new Point(x,y);
        
        double divider=(point.distance(p)/speed);
        dX=(int)((x-getX())/divider);
        dY=(int)((y-getY())/divider);
        
        
    }

    @Override
    public void update(PlayState state) {
        
        if(collides)destroyed=true;
        setLastPoint(getPoint());
        move(dX,dY);
        setHitBox(new Line2D.Float(map.getX()+getLastPoint().x,map.getY()+getLastPoint().y,map.getX()+getX(),map.getY()+getY()));
    }

   

    
    
    
    
}
