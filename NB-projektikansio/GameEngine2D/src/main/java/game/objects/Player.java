
package game.objects;

import game.Main;
import game.handlers.Clicks;
import game.handlers.GameHandler;
import game.handlers.GameState;
import game.handlers.MouseMovement;
import game.handlers.PlayState;
import game.map.TileMap;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Player extends MapObject implements Updatable{
    
    public Player(TileMap map) {
        super(map);
        setWidth(Main.TILE_SIZE);
        setHeight(Main.TILE_SIZE);
        type=MapObject.PLAYER;
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
    
    public void draw(Graphics2D g){
        g.setColor(Color.BLACK);
        g.fillOval(map.getX()+getX()-width/2, map.getY()+getY()-height/2, width, height);
        g.setColor(Color.CYAN);
        g.fillOval(Main.WIDTH/2-getWidth()/2, Main.HEIGHT/2-getHeight()/2, getWidth()-5, getHeight()-5);
        g.setColor(Color.BLUE);
        g.drawString(getX()+" "+getY(), map.getX()+getX()-width/2, map.getY()+getY()-height/2);
        
    }

    @Override
    public void update(PlayState state) {
        
        if(Clicks.clicked){
            Bullet b=new Bullet(point,map);
            b.calculateVector();
            state.getNewObjects().add(b);
        }
        
    }
    
    
    
}
