
package game.objects;

import game.gui.Clicks;
import game.gameLogic.PlayState;
import game.map.TileMap;
import game.tools.Geometry;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author esa
 */
public class ShootingEnemy extends Enemy{

    public ShootingEnemy(Point p, TileMap map) {
        super(p, map);
        type=MapObject.SOOTING_ENEMY;
        color=Color.MAGENTA;
        setSpeed(3);
        health=10;
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        
    }

    @Override
    public void update(PlayState state) {
        if(health<=0){
            state.addKill();
            state.getNewObjects().add(new Explosion(point,map));
            destroyed=true;
        }
        if(ticker>30){
            ticker=0;
            Player p=state.getPlayer();
            Point poi=this.calcBulletPoint(p.getPoint());
            MapObject b=new Bullet(poi,map);
            
            
            
            Point ad=Geometry.calculateAdvancement(b, state.getPlayer());
            b.calculateVector(ad);
            
            
            state.getNewObjects().add(b);
        }
        if(getCollisions(state))return;
        calculateVector(Geometry.calculateAdvancement(this, state.getPlayer()));
        move(dX,dY);
        
    }

}
