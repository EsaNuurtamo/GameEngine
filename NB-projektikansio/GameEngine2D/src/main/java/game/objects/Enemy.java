/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

import game.Main;
import game.handlers.PlayState;
import game.map.TileMap;
import game.tools.Collisions;
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
        setSpeed(5);
        super.point=p;
        setWidth(Main.TILE_SIZE);
        setHeight(Main.TILE_SIZE);
        setColor(Color.YELLOW);
        type=MapObject.ENEMY;
        
        
    }
    /**
     * Tarkistetaan osuuko Enemy luoteihin ja asetetaan 
     * @param state 
     */
    @Override
    public void update(PlayState state) {
        for(MapObject m:state.getObjectsOfType(MapObject.BULLET)){
            Bullet b=(Bullet)m;
            if(Collisions.CircleLineCollision(this,b)){
                destroyed=true;
            }
        }
        
        calculateVector(state.getPlayer().getPoint());
        move(dX,dY);
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        
    }

    
    
    
    
}
