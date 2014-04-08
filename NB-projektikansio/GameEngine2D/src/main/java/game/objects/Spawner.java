/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

import game.gameLogic.PlayState;
import game.map.TileMap;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author esa
 */
public class Spawner extends MapObject implements Updatable{
    private int spawnType;
    private int interval;
    private int wait;
    private boolean waiting=true;
    public Spawner(Point p,TileMap map, int spawnType) {
        super(map);
        setPoint(p);
        setWidth(TileMap.tileSize);
        setHeight(TileMap.tileSize);
        
        setType(MapObject.SPAWNER);
        this.spawnType=spawnType;
        //3 sekuntia
        this.interval=300;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public void setSpawnType(int spawnType) {
        this.spawnType = spawnType;
    }

    public int getSpawnType() {
        return spawnType;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    @Override
    public void update(PlayState state) {
        if(ticker>wait&&waiting){
            ticker=0;
            waiting=false;
        }
            
        
    
        if(ticker>interval){
            ticker=0;
            MapObject added=null;
            if(spawnType==MapObject.ENEMY){
                added=new Enemy(point,map);
            }else if(spawnType==MapObject.SOOTING_ENEMY){
                added=new ShootingEnemy(point,map);
            }
            state.getNewObjects().add(added);
        }
    }

    @Override
    public void draw(Graphics2D g) {
        if(ticker%20==0){
            color=Color.YELLOW;
            if(spawnType==MapObject.SOOTING_ENEMY){
                color=Color.MAGENTA;
            }
        }else{
            color=new Color(233,250,12,50);
        }
     
    
        super.draw(g);
    }
    
}
