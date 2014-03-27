
package game.objects;

import game.Main;
import game.handlers.GameState;
import game.handlers.PlayState;
import game.map.TileMap;
import java.awt.List;
import java.awt.Point;
import java.util.ArrayList;

public abstract class MapObject {
    public static final int PLAYER=0;
    public static final int BULLET=1;
    
    //paikka ja vektori
    protected Point point;
    protected int dX;
    protected int dY;
   
    protected int type;
    protected TileMap map;
    
    protected int width;
    protected int height;
    
    protected int ticker;
    
    protected boolean destroyed;
    protected boolean collides=false;

    public MapObject(TileMap map) {
        this.map=map;
        ticker=0;
        destroyed=false;
    }

    
    
    public void setPoint(Point point) {
        if(point.x<Main.TILE_SIZE+Math.round(width/2)||
           point.x>Main.TILE_SIZE*(map.mapSize-1)-width/2||
           point.y<Main.TILE_SIZE+Math.round(height/2)||
           point.y>Main.TILE_SIZE*(map.mapSize-1)-height/2){
            collides=true;
            return;
        }
        collides=false;
        
        
        
        this.point = point;
    }
    
    public void move(int dX, int dY){
        setPoint(new Point(getPoint().x+dX, getPoint().y+dY));
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    public int getX(){return point.x;}
    public int getY(){return point.y;}
    public int getdX(){return dX;}
    public int getdY(){return dY;}

    public void setdX(int dX) {
        this.dX = dX;
    }

    public void setdY(int dY) {
        this.dY = dY;
    }
    
    
    public Point getPoint() {
        return point;
    }

    public TileMap getMap() {
        return map;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
    
    public void tick(){
        ticker++;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MapObject){
            MapObject mob=(MapObject)obj;
            if(mob.point.equals(this.point)&&
               mob.type==this.type&&
               mob.dX==this.dX&&
               mob.dY==this.dY){
                return true;
            }
        }
        return false;
    }
    
    
    
    
    
    
    
    
}
