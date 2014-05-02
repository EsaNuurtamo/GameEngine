
package game.objects.guns;

import game.gameLogic.PlayState;
import game.map.TileMap;
import java.awt.Point;
import java.util.Random;

public class Shotgun extends Gun{

    public Shotgun(TileMap map) {
        super(map);
        fireRate=30;
        reloadtime=120;
        clipSize=6;
        inClip=clipSize;
        ammo=36;
    }

    @Override
    public void shoot(Point p, PlayState state) {
        Point pl=state.getPlayer().getPoint();
        for(int i=0; i<20; i++){
            Bullet b=new Bullet(state.getPlayer().getPoint(),map);
            b.setWidth(6);
            b.setHeight(6);
            b.setPlayers(true);
            Random r=new Random();
            int dist=(int)p.distance(pl);
            int x=(p.x+(r.nextInt(dist/4)-dist/8));
            int y=(p.y+(r.nextInt(dist/4)-dist/8));
            b.calculateVector(new Point(x,y));
            
            
            state.getNewObjects().add(b);
        }
        
    }
}
