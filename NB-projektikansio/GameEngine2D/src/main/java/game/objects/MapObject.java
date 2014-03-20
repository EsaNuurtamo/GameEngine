
package game.objects;

import game.map.TileMap;
import java.awt.Point;

public abstract class MapObject {
    
    
    protected Point point;
    protected Point dPoint;
   
    
    protected TileMap map;
    
    protected int width;
    protected int height;

    public MapObject(TileMap map) {
        this.map=map;
        
    }

    public Point getdPoint() {
        return dPoint;
    }

    public void setdPoint(Point dPoint) {
        this.dPoint = dPoint;
    }
    
    public void setPoint(Point point) {
        this.point = point;
    }
    
    public int getX(){return point.x;}
    public int getY(){return point.y;}
    public int getdX(){return dPoint.x;}
    public int getdY(){return dPoint.y;}
    
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
    
    
    
    
    
    
}
