
package game.objects;



import game.Main;
import game.gui.Clicks;
import game.gameLogic.PlayState;
import game.map.TileMap;
import game.objects.guns.Bullet;
import game.objects.Enemy;
import game.objects.Explosion;
import game.objects.MapObject;
import game.objects.Player;
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
        
        //ampuu 30 tickin välein
        if(ticker%30==0){
            
            
            MapObject b=new Bullet(getPoint(),map);
            Point ad=Geometry.calculateAdvancement(b, state.getPlayer());
            b.calculateVector(ad);
            
            
            state.getNewObjects().add(b);
        }
        if(getCollisions(state))return;
        if(state.getPlayer().getPoint().distance(point)<200){
            if(ticker%30==0){
               
                
                int x=(int)(Math.random()*Main.WIDTH);
                int y=(int)(Math.random()*Main.HEIGHT);
                calculateVector(new Point(x,y));
            }
            
        }else{
            calculateVector(Geometry.calculateAdvancement(this, state.getPlayer()));
        }
        
        
        move(dX,dY);
        
    }

}
