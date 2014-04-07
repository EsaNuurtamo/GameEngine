
package game.objects;

import game.Main;
import game.gui.Clicks;
import game.handlers.GameState;
import game.handlers.PlayState;
import game.map.TileMap;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Tässä luokassa on kuvattu millainen on yleisen tason objekti pelissä
 * SIsältää lähinnä liikkumiseen tarvittavan toiminnallisuuden sekä piirtää itsensä
 */
public abstract class MapObject {
    public static final int PLAYER=0;
    public static final int BULLET=1;
    public static final int ENEMY=2;
    public static final int SPAWNER=3;
    public static final int SOOTING_ENEMY=4;
    
    //paikka ja vektori
    protected Point point;
    protected int targetX;
    protected int targetY;
    protected int dX;
    protected int dY;
    protected double speed;
    protected Point lastPoint;
    protected Shape hitBox;
    
    //kuva
    protected Color color;
    //BufferedImage image;
   
    protected int type;
    protected TileMap map;
    
    protected int width;
    protected int height;
    
    protected int ticker;
    protected int maxHealth;
    protected int health;
    protected boolean destroyed;
    protected boolean collides=false;

    public MapObject(TileMap map) {
        this.map=map;
        ticker=0;
        destroyed=false;
    }

    
    /**
     * Asettaa objektin tiettyyn paikkaan
     * jos paikka on kartan ulkopuolella ei liiku
     * @param point 
     */
    public void setPoint(Point point) {
        if(point.x<Main.TILE_SIZE+Math.round(width/2)||
           point.x>Main.TILE_SIZE*(map.mapSize-1)-width/2||
           point.y<Main.TILE_SIZE+Math.round(height/2)||
           point.y>Main.TILE_SIZE*(map.mapSize-1)-height/2){
            collides=true;
            return;
        }
        collides=false;
        
        
        lastPoint=this.point;
        this.point = point;
    }

    public int getTargetY() {
        return targetY;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        this.health=maxHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
    
    public int getTargetX() {
        return targetX;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public Shape getHitBox() {
        return hitBox;
    }

    public void setHitBox(Shape hitBox) {
        this.hitBox = hitBox;
    }

    public int getTicker() {
        return ticker;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getSpeed() {
        return speed;
    }
    
    /**
     * Liikuttaa objektia tietyn suuntavektorin verran
     * @param dX
     * @param dY 
     */
    public void move(int dX, int dY){
        
        setPoint(new Point(getPoint().x+dX, getPoint().y+dY));
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    /**
     * Laskee  oikean pituisen suuntavektrorin jos tiedetään suunta p
     * @param p piste jota kohti objekti on menossa
     */
    public void calculateVector(Point p){
        
        int x=p.x;
        int y=p.y;
        
        double divider=(point.distance(p)/speed);
        if(divider<=0){
            divider=1;
        }
        
        
        dX=((int)((x-getX())/divider));
        dY=((int)((y-getY())/divider));
        
        
        
        
        
    }
    public Point calcBulletPoint(Point p){
        int x=p.x;
        int y=p.y;
        
        double divider=(point.distance(p)/(width/2+10));
        if(divider<=0){
            divider=1;
        }
        
        
        int x2=((int)((x-getX())/divider));
        int y2=((int)((y-getY())/divider));
        return new Point(getX()+x2,getY()+y2);
    }
    
    /**
     * Piirtää objektin oikeaan kohtaan
     * @param g GamePanelista saatu
     */
    public void draw(Graphics2D g){
        g.setColor(color);
        g.fillOval(map.getX()+getX()-width/2, map.getY()+getY()-height/2, width, height);
        
        //piirtää hitboxit
        /*if(hitBox==null)return;
        g.setColor(Color.BLACK);
        
        g.draw(hitBox);*/
        
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
