
package game.objects;

import game.Main;
import game.gui.Clicks;
import game.gui.Keys;
import game.gameLogic.GameHandler;
import game.gameLogic.GameState;
import game.gui.MouseMovement;
import game.gameLogic.PlayState;
import game.map.TileMap;
import game.tools.Geometry;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Player extends MapObject implements Updatable{
    
    public Player(TileMap map) {
        super(map);
        setWidth(Main.TILE_SIZE);
        setHeight(Main.TILE_SIZE);
        setColor(Color.CYAN);
        type=MapObject.PLAYER;
        setSpeed(6);
        setMaxHealth(1000);
    }

    @Override
    public void setPoint(Point point) {
        
        super.setPoint(point);
        if(collides)return;
        int x=-point.x+Math.round(Main.WIDTH/2);
        int y=-point.y+Math.round(Main.HEIGHT/2);
        getMap().setLocation(new Point(x,y));
    }
    
    
    
    
    public int getXonScreen(){
        return Main.WIDTH/2; 
    }
    
    public int getYonScreen(){
        return Main.HEIGHT/2;
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        g.setColor(Color.BLUE);
        g.setFont(Main.DEBUG);
        //g.drawString(getX()+" "+getY(), getX()+map.getX(), getY()+map.getY());
    }
    
    

    @Override
    public void update(PlayState state) {
        if(health<=0)destroyed=true;
        
        for(MapObject m:state.getObjectsOfType(MapObject.ENEMY)){
            if(Geometry.circleCircleCollision(m, this)){
                health--;
                m.setHealth(0);
            }
        }
        for(MapObject m:state.getObjectsOfType(MapObject.BULLET)){
            Bullet b=(Bullet)m;
            if(Geometry.CircleLineCollision(this,b)){
                health--;
                b.setDestroyed(true);
            }
        }
        
        if(Clicks.clicked){
            Point p=calcBulletPoint(new Point(Clicks.x-map.getX(),Clicks.y-map.getY()));
            
            Bullet b=new Bullet(p,map);
            b.setPlayers(true);
            
            int x=Clicks.x-map.getX();
            int y=Clicks.y-map.getY();
            b.calculateVector(new Point(x,y));
            state.getNewObjects().add(b);
        }
        
        
        
        
        move(dX,dY);
        
        
    }
    
    
    
}
