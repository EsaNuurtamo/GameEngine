
package game.objects.guns;

import game.Main;
import game.gameLogic.PlayState;
import game.gui.Clicks;
import game.gui.MouseMovement;
import game.map.TileMap;
import game.objects.MapObject;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public abstract class Gun extends MapObject{
    public static final int PISTOL=0;
    public static final int MG=1;
    public static final int RPG=2;
    public static final int SHOTGUN=3;
    
    protected boolean carried=false;
    
    protected int ammo;
    protected int clipSize;
    protected int inClip;
    protected boolean empty=false;
    protected boolean ready=false;
    
    protected int reloadtime;
    protected int fireRate;
    protected int reloadTimer=0;
    protected int fireRateTimer=0;
    

    public Gun(TileMap map) {
        super(map);
        
    }
    
    public void update(PlayState state){
        if(Clicks.isClicked()){
            int x=MouseMovement.x-map.getX();
            int y=MouseMovement.y-map.getY();
            pullTrigger(new Point(x,y),state);
            
        }
    }
    
    public void pullTrigger(Point p, PlayState state){
        //lipppaan lataaminen
        if(reloadTimer>=reloadtime){
            if(ammo<=0)return;
            reloadTimer=0;
            empty=false;
            if(ammo<clipSize){
                inClip=ammo;
                ammo=0;
            }else{
                inClip=clipSize;
                ammo-=clipSize;
            }
        }
        
        //luodin lataaminen piippuun
        if(fireRateTimer>=fireRate){
            fireRateTimer=0;
            ready=true;
        }
        
        //ampuminen
        if(ready){
            shoot(p,state);
            inClip--;
            if(inClip<=0){
                empty=true;
            }
            ready=false;
        }
        
    }
    
        
    
    public void draw(Graphics2D g){
        g.setFont(Main.DEBUG);
        g.setColor(Color.CYAN);
        g.drawString(inClip+"/"+ammo, 400, 50);
    }
    
        

    @Override
    public void tick() {
        super.tick();
        if(empty){
            reloadTimer++;
        }else if(!ready){
            fireRateTimer++;
        }
    }

    public int getAmmo() {
        return ammo;
    }

    public int getInClip() {
        return inClip;
    }
    
    public void shoot(Point p, PlayState state){
        Bullet b=new Bullet(state.getPlayer().getPoint(),map);
        b.setPlayers(true);
        b.calculateVector(p);
        state.getNewObjects().add(b);
    }
    
}
