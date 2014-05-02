/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects.guns;

import game.Main;
import game.gui.Clicks;
import game.gameLogic.GameState;
import game.gui.MouseMovement;
import game.gameLogic.PlayState;
import game.map.TileMap;
import game.objects.MapObject;
import game.objects.Updatable;
import game.tools.Geometry;
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
 * Luokka kuvaa yksittäistä luotia pelissä
 */
public class Bullet extends MapObject implements Updatable{
    
    private boolean players;
    
    
    public Bullet(Point p, TileMap map){
        super(map);
        super.point=p;
        lastPoint=p;
        
        setWidth(Main.TILE_SIZE/4);
        setHeight(Main.TILE_SIZE/4);
        setColor(Color.DARK_GRAY);
        type=MapObject.BULLET;
        speed=30;
        players=false;
        
    }

    public void setPlayers(boolean players) {
        this.players = players;
    }

    public boolean isPlayers() {
        return players;
    }

    public Point getLastPoint() {
        return lastPoint;
    }

    public void setLastPoint(Point lastPoint) {
        this.lastPoint = lastPoint;
    }
    
    @Override
    public void update(PlayState state) {
        if(collides||state.getPlayer().getPoint().distance(this.getPoint())>10000){
            destroyed=true;
            
        }
        for(MapObject m:state.getObjectsOfType(MapObject.ENEMY)){
            
            if(!isPlayers())break;
            if(Geometry.CircleLineCollision(m,this)){
                m.setHealth(m.getHealth()-1);
                
                
                
            }
        }
        update();
        
    }
   
    public void update(){
        setLastPoint(getPoint());
        move(dX,dY);
    }
    
    

   

    
    
    
    
}
