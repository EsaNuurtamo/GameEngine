
package game.objects;

import game.objects.guns.Pistol;
import game.objects.guns.Gun;
import game.objects.guns.Bullet;
import game.Main;
import game.gui.Clicks;
import game.gui.Keys;
import game.gameLogic.GameHandler;
import game.gameLogic.GameState;
import game.gui.MouseMovement;
import game.gameLogic.PlayState;
import game.map.TileMap;
import game.objects.guns.MachineGun;
import game.objects.guns.Shotgun;
import game.tools.Geometry;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Luokka kuvaa pelaajan ominaisuuksia´
 * @author esa
 */
public class Player extends MapObject implements Updatable{
    private List<Gun> guns=new ArrayList<Gun>();
    private int curGun;
    
    public Player(TileMap map) {
        super(map);
        
        setWidth(Main.TILE_SIZE);
        setHeight(Main.TILE_SIZE);
        setColor(Color.CYAN);
        type=MapObject.PLAYER;
        setSpeed(6);
        setMaxHealth(1000);
        guns.add(new Pistol(map));
        guns.add(new MachineGun(map));
        guns.add(new Shotgun(map));
        curGun=0;
        
        
    }

    @Override
    public void setPoint(Point point) {
        
        super.setPoint(point);
        if(collides)return;
        int x=-point.x+Math.round(Main.WIDTH/2);
        int y=-point.y+Math.round(Main.HEIGHT/2);
        getMap().setLocation(new Point(x,y));
    }
    
    
    
    public Gun getGun(){
        return guns.get(curGun);
    }
    
    public void nextGun(){
        curGun++;
        if(curGun>=guns.size())curGun=0;
    }
    
    
    
    public int getXonScreen(){
        return Main.WIDTH/2; 
    }
    
    public int getYonScreen(){
        return Main.HEIGHT/2;
    }

    /*@Override
    public void draw(Graphics2D g) {
        super.draw(g);
        g.setColor(Color.BLUE);
        g.setFont(Main.DEBUG);
        guns.get(curGun).draw(g);
    }*/
    
    

    @Override
    public void update(PlayState state) {
        if(health<=0)destroyed=true;
        
        for(MapObject m:state.getObjectsOfType(MapObject.ENEMY)){
            if(Geometry.circleCircleCollision(m, this)){
                health--;
                m.setDestroyed(true);
            }
        }
        for(MapObject m:state.getObjectsOfType(MapObject.BULLET)){
            Bullet b=(Bullet)m;
            if(Geometry.CircleLineCollision(this,b)&&!b.isPlayers()){
                health--;
                b.setDestroyed(true);
            }
        }
        guns.get(curGun).update(state);
        /*if(Clicks.clicked){
            
            int x=Clicks.x-map.getX();
            int y=Clicks.y-map.getY();
            gun.pullTrigger(new Point(x,y),state);
             
            Point p=calcBulletPoint(new Point(Clicks.x-map.getX(),Clicks.y-map.getY()));
            
            Bullet b=new Bullet(p,map);
            b.setPlayers(true);
            
            int x=Clicks.x-map.getX();
            int y=Clicks.y-map.getY();
            b.calculateVector(new Point(x,y));
            state.getNewObjects().add(b);
        }*/
    
        
        
        
        
        move(dX,dY);
        
        
    }

    @Override
    public void tick() {
        super.tick();
        guns.get(curGun).tick();
    }
    
    
    
}
