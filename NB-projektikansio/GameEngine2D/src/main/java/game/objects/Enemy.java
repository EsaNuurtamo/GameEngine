/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

import game.objects.guns.Bullet;
import game.Main;
import game.gameLogic.PlayState;
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
        //jos health loppu niin lisää tappo ja räjähdys ja aseta tuhottavaksi
        if(health<=0){
            state.addKill();
            state.getNewObjects().add(new Explosion(point,map));
            
            destroyed=true;
        }
        
        if(getCollisions(state))return;
        
        int x=state.getPlayer().getdX();
        int y=state.getPlayer().getdY();
        Point p=Geometry.calculateAdvancement(this, state.getPlayer());
        calculateVector(p);
        //calculateVector(state.getPlayer().getPoint());
        move(dX,dY);
    }

    @Override
    public void draw(Graphics2D g) {
        
        super.draw(g);
        
        
        
        //g.fill3DRect(advancePoint.x+map.getX(), advancePoint.y+map.getY(), 10, 10, false);
        
    }
    
    public boolean getCollisions(PlayState state){
        
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
