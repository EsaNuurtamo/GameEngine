/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

import game.Main;
import game.handlers.PlayState;
import game.map.TileMap;
import game.tools.Geometry;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.PathIterator;

/**
 * Enmy on MapObjekti jota pelaajan on tarkotus tuhota
 * Enemylla on myös läpikäytävää logiikkaa
 */
public class Enemy extends MapObject implements Updatable{
    private Point advancePoint;
    public Enemy(Point p, TileMap map) {
        super(map);
        
        setPoint(p);
        setSpeed(6);
        super.point=p;
        setWidth(Main.TILE_SIZE);
        setHeight(Main.TILE_SIZE);
        setColor(Color.YELLOW);
        type=MapObject.ENEMY;
        health=1;
        
    }
    /**
     * Tarkistetaan osuuko Enemy luoteihin ja asetetaan 
     * @param state 
     */
    @Override
    public void update(PlayState state) {
        if(health<=0){
            state.addKill();
            destroyed=true;
        }
        
        if(getCollisions(state))return;
        
        int x=state.getPlayer().getdX();
        int y=state.getPlayer().getdY();
        advancePoint=Geometry.calculateAdvancement(this, state.getPlayer());
        calculateVector(advancePoint);
        move(dX,dY);
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        if(advancePoint==null)return;
        //g.setColor(Color.RED);
        //g.fill3DRect(advancePoint.x+map.getX(), advancePoint.y+map.getY(), 10, 10, false);
        
    }
    
    public boolean getCollisions(PlayState state){
        for(MapObject m:state.getObjectsOfType(MapObject.BULLET)){
            Bullet b=(Bullet)m;
            if(Geometry.CircleLineCollision(this,b)){
                health--;
                
            }
        }
        for(MapObject m:state.getObjectsOfType(MapObject.ENEMY)){
            if(m.equals(this))continue;
            if(Geometry.circleCircleCollision(m, this)){
                if(point.distance(state.getPlayer().getPoint())>m.getPoint().distance(state.getPlayer().getPoint())){
                    move((int)(Math.random()*m.dX),(int)(m.dY*Math.random()));
                    return true;
                }
            }
        }
        return false;
    }

    
    
    
    
}
