/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.handlers;

import com.sun.org.apache.xml.internal.utils.ObjectStack;
import game.gui.MouseMovement;
import game.gui.Keys;
import game.gui.Clicks;
import game.Main;
import game.map.TileMap;
import game.objects.Bullet;
import game.objects.Enemy;
import game.objects.MapObject;
import game.objects.Player;
import game.objects.ShootingEnemy;
import game.objects.Spawner;
import game.objects.Updatable;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import java.util.List;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Luokka kuva tilaa jossa peliä pelataan
 */
public class PlayState extends GameState{
    public static final int CURSOR_SIZE=20;
    private TileMap tileMap;
    private Player player;
    private List<MapObject> objects;
    private List<MapObject> newObjects;
    private int ticker=0;
    //tulokest
    private int killCount=0;
    private int killTarget=20;
    private int countDown=10;
    public PlayState(GameHandler sh) {
        super(sh);
        
        objects=new ArrayList<MapObject>();
        
        newObjects=new ArrayList<MapObject>();
        init();
        
    }

    public List<MapObject> getObjects() {
        return objects;
    }
    public void addKill(){
        killCount++;
    }
    public int getKillCount() {
        return killCount;
    }

    public void setKillCount(int killCount) {
        this.killCount = killCount;
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

    public List<MapObject> getNewObjects() {
        
        return newObjects;
        
    }
    
    public void deleteDestroyed(){
        Iterator i=objects.iterator();
        while(i.hasNext()){
            MapObject obj=(MapObject)i.next();
            
            if(obj.isDestroyed()){
                if(obj instanceof Player){
                player.setDestroyed(true);
                }
                i.remove();
            }
        }
    }
    
    
    
    public void addSpawners(){
        Spawner spawner=new Spawner(new Point(1400,1400),tileMap,MapObject.ENEMY);
        objects.add(spawner);
        spawner=new Spawner(new Point(200,1400),tileMap,MapObject.ENEMY);
        spawner.setWait(40);
        objects.add(spawner);
        spawner=new Spawner(new Point(200,200),tileMap,MapObject.ENEMY);
        spawner.setWait(80);
        objects.add(spawner);
        spawner=new Spawner(new Point(1400,200),tileMap,MapObject.ENEMY);
        spawner.setWait(120);
        objects.add(spawner);
        spawner=new Spawner(new Point(1400,300),tileMap,MapObject.SOOTING_ENEMY);
        spawner.setInterval(600);
        objects.add(spawner);
        spawner=new Spawner(new Point(200,1300),tileMap,MapObject.SOOTING_ENEMY);
        spawner.setWait(250);
        spawner.setInterval(600);
        objects.add(spawner);
    }
    
    

    @Override
    public void init() {
        tileMap=new TileMap();
        tileMap.load("/Map.txt");
        player=new Player(tileMap);
        player.setPoint(new Point(400,400));
        objects.add(player);
        
        
        
    }
    /**
     * Päivittää kaiken pelissä:
     * kontrollit, objektit, clickaukset
     */
    @Override
    public void update() {
        if(countDown==0){
            countDown=-1;
            addSpawners();
        }
        if(ticker%60==0){
            countDown--;
        }
        if(ticker%30==0){
           for( MapObject m:getObjectsOfType(MapObject.SPAWNER)){
               Spawner s=(Spawner)m;
               if(s.getInterval()<=60)continue;
               s.setInterval(s.getInterval()-1);
           }
        }
        handleInput();
        
        updateObjects();
        deleteDestroyed();
        Clicks.resetClicks();
        ticker++;
    }

    public int getTicker() {
        return ticker;
    }
    
    /**
     * Metodi paluttaa pelissä olevat, tietyn tyyppiset oliot
     * esim. pelin kaikki luodit
     * @param type saadaan pelin lokiigalta
     * @return listan jossa kaikki tyyppiä vastaavat oliot
     */
    public List<MapObject> getObjectsOfType(int type){
        List<MapObject> l=new ArrayList<MapObject>();
        for(MapObject obj:objects){
            if(obj.getType()==type){
                l.add(obj);
            }
            
        }
        if(type==MapObject.ENEMY){
            type=MapObject.SOOTING_ENEMY;
            for(MapObject obj:objects){
                if(obj.getType()==type){
                     l.add(obj);
                }
            
        }
                
        }
        return l;
    }
    
    /**
     * Piirtää pelin:
     * pelin kartta, pelin objectit, pelaaja, tähtäin
     * 
     * @param g saadaan Gamepanelista
     * @see map.TileMap#draw(Graphics2D)
     * @see objects.Player#draw(Graphics2D)
     * @see objects.MapObject#draw(Graphics2D)
     */
    @Override
    public void draw(Graphics2D g) {
        tileMap.draw(g);
        for(MapObject obj:objects){
            obj.draw(g);
        }
        player.draw(g);
        
        //tähtäin
        g.setColor(Color.BLUE);
        g.drawOval(MouseMovement.x-CURSOR_SIZE/2, MouseMovement.y-CURSOR_SIZE/2, CURSOR_SIZE, CURSOR_SIZE);
        g.drawLine(MouseMovement.x-CURSOR_SIZE/2, MouseMovement.y, MouseMovement.x+CURSOR_SIZE/2, MouseMovement.y);
        g.drawLine(MouseMovement.x, MouseMovement.y-CURSOR_SIZE/2, MouseMovement.x, MouseMovement.y+CURSOR_SIZE/2);
        
        //piirrä ohje
        g.setColor(Color.WHITE);
        g.setFont(Main.DEBUG);
        g.fill3DRect(10, 10, 200, 50, true);
        g.setColor(Color.BLACK);
        g.drawString("Kills: "+killCount+"/"+killTarget, 30, 30);
        g.drawString("Life: "+player.getHealth()+"/"+player.getMaxHealth(), 30, 55);
        
        if(countDown<0)return;
        g.setFont(new Font("Serif", Font.BOLD,40));
        g.setColor(Color.WHITE);
        g.fillRect(Main.WIDTH/2-170, Main.HEIGHT/2-150, 370, 60);
        g.setColor(Color.BLACK);
        g.drawString("Match begins in: "+this.countDown,Main.WIDTH/2-150,Main.HEIGHT/2-110);
    }
    
    /**
     * Tässä käsitellään PlayStaten kontrollit
     */
    @Override
    public void handleInput() {
        if(Keys.keyState[Keys.ESC]){
            gameHandler.setPaused(true);
        }
        
        if(Keys.isPressed(Keys.E_KEY)){
            newObjects.add(new ShootingEnemy(new Point(700,200),tileMap));
        }
        if(player.isDestroyed())return;
        int x=0;
        int y=0;
        if(Keys.keyState[Keys.UP_KEY]){
            y=-20;
        }
        if(Keys.keyState[Keys.DOWN_KEY]){
            y=20;
        }
        if(Keys.keyState[Keys.LEFT_KEY]){
            x=-20;
        }
        if(Keys.keyState[Keys.RIGHT_KEY]){
            x=20;
        }
        Point p=new Point(player.getX()+x,player.getY()+y);
        player.calculateVector(p);
        
        Keys.update();
    }

    
    
}
