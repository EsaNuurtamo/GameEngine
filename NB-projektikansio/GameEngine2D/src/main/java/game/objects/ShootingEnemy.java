
package game.objects;

import game.gui.Clicks;
import game.handlers.PlayState;
import game.map.TileMap;
import game.tools.Geometry;
import java.awt.Color;
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
    public void update(PlayState state) {
        if(health<=0){
            state.addKill();
            destroyed=true;
        }
        if(ticker>50){
            ticker=0;
            Player p=state.getPlayer();
            Point poi=this.calcBulletPoint(p.getPoint());
            MapObject b=new Bullet(poi,map);
            
            
            int x2=p.getX()+p.getdX();
            int y2=p.getY()+p.getdY();
            b.calculateVector(new Point(x2,y2));
            
            
            state.getNewObjects().add(b);
        }
        if(getCollisions(state))return;
        calculateVector(Geometry.calculateAdvancement(this, state.getPlayer()));
        move(dX,dY);
        
    }

}
