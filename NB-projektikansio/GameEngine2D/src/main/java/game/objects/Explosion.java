/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

import game.Main;
import game.map.TileMap;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author esa
 */
public class Explosion extends MapObject{

    public Explosion(Point p,TileMap map) {
        super(map);
        width=Main.TILE_SIZE;
        height=Main.TILE_SIZE;
        point=p;
    }

    @Override
    public void draw(Graphics2D g) {
        
        if(ticker>8)destroyed=true;
        Color red=new Color(255,0,0,255);
        Color yellow=new Color(255,255,0,255);
                boolean isYellow;

                for(int i=10; i<=360; i+=10){
                    
                    //tee sektoreista vuorotellen punaisia ja keltaisia
                    if((i/10)%2==0){
                        g.setColor(red);
                        isYellow=false;
                    }else{
                        g.setColor(yellow);
                        isYellow=true;
                    }
                    
                    //piirtää ympurän sektorit, kuvio kasvaa ajan myötä
                    int width2=width*3/10*getTicker();
                    g.fillArc(
                        getX()-(int)(0.5*width2)+map.getX(), 
                        getY()-(int)(0.5*width2)+map.getY(),
                        width2, width2, (isYellow?10:20)*(i/10), (isYellow?10:20)
                    );
                }
    }
    
}
