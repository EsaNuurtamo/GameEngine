/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.handlers;

import game.gui.MouseMovement;
import game.gui.Keys;
import game.gui.Clicks;
import game.Main;
import game.map.TileMap;
import game.objects.Bullet;
import game.objects.MapObject;
import game.objects.Player;
import game.objects.Updatable;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author esa
 */
public class PlayState extends GameState{
    public static final int CURSOR_SIZE=20;
    private TileMap tileMap;
    private Player player;
    private ArrayList<MapObject> objects;
    private ArrayList<MapObject> newObjects;
    public PlayState(GameHandler sh) {
        super(sh);
        objects=new ArrayList<MapObject>();
        newObjects=new ArrayList<MapObject>();
        init();
        
    }

    public ArrayList<MapObject> getObjects() {
        return objects;
    }

    public Player getPlayer() {
        return player;
    }

    public TileMap getTileMap() {
        return tileMap;
    }
    
    
    //edistää objektien kelloa ja päivittää logiikan sisältävät
    public void updateObjects(){
        for(MapObject obj:objects){
            obj.tick();
            if(obj instanceof Updatable){
                
                Updatable u=(Updatable)obj;
                u.update(this);
            }
        }
        for(MapObject obj:newObjects){
            objects.add(obj);
        }
        newObjects.clear();
    }

    public ArrayList<MapObject> getNewObjects() {
        
        return newObjects;
        
    }
    
    public void deleteDestroyed(){
        Iterator i=objects.iterator();
        while(i.hasNext()){
            MapObject obj=(MapObject)i.next();
            if(obj.isDestroyed()){
                i.remove();
            }
        }
    }
    
    
    
    
    
    

    @Override
    public void init() {
        tileMap=new TileMap();
        tileMap.load("/Map.txt");
        player=new Player(tileMap);
        player.setPoint(new Point(400,400));
        objects.add(player);
        
    }

    @Override
    public void update() {
        handleInput();
        updateObjects();
        deleteDestroyed();
        Clicks.resetClicks();
    }
    
    public ArrayList<MapObject> getObjectsOfType(int type){
        ArrayList<MapObject> l=new ArrayList<MapObject>();
        for(MapObject obj:objects){
            if(obj.getType()==type){
                l.add(obj);
            }
        }
        return l;
    }

    @Override
    public void draw(Graphics2D g) {
        if(tileMap==null)return;
        tileMap.draw(g);
        for(MapObject obj:getObjectsOfType(MapObject.BULLET)){
            Bullet b=(Bullet)obj;
            b.draw(g);
        }
        player.draw(g);
        
        
        //tähtäin
        g.setColor(Color.BLUE);
        g.drawOval(MouseMovement.x-CURSOR_SIZE/2, MouseMovement.y-CURSOR_SIZE/2, CURSOR_SIZE, CURSOR_SIZE);
        g.drawLine(MouseMovement.x-CURSOR_SIZE/2, MouseMovement.y, MouseMovement.x+CURSOR_SIZE/2, MouseMovement.y);
        g.drawLine(MouseMovement.x, MouseMovement.y-CURSOR_SIZE/2, MouseMovement.x, MouseMovement.y+CURSOR_SIZE/2);
        
        //piirrä ohje
        g.setColor(Color.WHITE);
        g.fill3DRect(10, 10, 200, 50, true);
        g.setColor(Color.BLACK);
        g.drawString("Press ESC to quit", 30, 30);
        
    }

    @Override
    public void handleInput() {
        if(Keys.keyState[Keys.UP_KEY]){
            player.move(0, -5);
        }
        if(Keys.keyState[Keys.DOWN_KEY]){
            player.move(0, 5);
        }
        if(Keys.keyState[Keys.LEFT_KEY]){
            player.move(-5, 0);
        }
        if(Keys.keyState[Keys.RIGHT_KEY]){
            player.move(5, 0);
        }
        if(Keys.keyState[Keys.ESC]){
            System.exit(0);
        }
        Keys.update();
    }

    
    
}
