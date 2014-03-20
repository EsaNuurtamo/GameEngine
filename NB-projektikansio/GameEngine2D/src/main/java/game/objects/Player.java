
package game.objects;

import game.Main;
import game.map.TileMap;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Player extends MapObject{

    public Player(TileMap map) {
        super(map);
        setWidth(40);
        setHeight(40);
    }

    @Override
    public void setPoint(Point point) {
        if(point.x<Main.BLOCK_SIZE+width/2)return;
        if(point.x>Main.BLOCK_SIZE*(map.mapSize-1)-width/2)return;
        if(point.y<Main.BLOCK_SIZE+height/2)return;
        if(point.y>Main.BLOCK_SIZE*(map.mapSize-1)-height/2)return;
        
        super.setPoint(point);
        int x=-point.x+Main.SCREEN_WIDTH/2;
        int y=-point.y+Main.SCREEN_HEIGHT/2;
        getMap().setLocation(new Point(x,y));
    }
    
    public void move(int dX, int dY){
        setPoint(new Point(getPoint().x+dX, getPoint().y+dY));
    }
    
    public void draw(Graphics2D g){
        g.setColor(Color.CYAN);
        g.fillOval(Main.SCREEN_WIDTH/2-getWidth()/2, Main.SCREEN_HEIGHT/2-getHeight()/2, getWidth(), getHeight());
        g.setColor(Color.ORANGE);
        g.drawString(getX()+" "+getY(), Main.SCREEN_WIDTH/2-getWidth()/2, Main.SCREEN_HEIGHT/2-getHeight()/2);
    }
    
}
