
package game.objects;

import game.Main;
import game.gui.Clicks;
import game.handlers.GameHandler;
import game.handlers.GameState;
import game.gui.MouseMovement;
import game.handlers.PlayState;
import game.map.TileMap;
import game.tools.Collisions;
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
        setSpeed(5);
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
        g.drawString(getX()+" "+getY(), getX()+map.getX(), getY()+map.getY());
    }
    
    

    @Override
    public void update(PlayState state) {
        
        
        for(MapObject m:state.getObjectsOfType(MapObject.ENEMY)){
            if(Collisions.circleCircleCollision(m, this)){
                destroyed=true;
                m.setDestroyed(true);
            }
            
            
        }
        
        if(Clicks.clicked){
            Bullet b=new Bullet(point,map);
            b.calculateVector();
            state.getNewObjects().add(b);
        }
        
        
    }
    
    
    
}
