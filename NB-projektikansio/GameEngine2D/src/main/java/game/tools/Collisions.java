/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tools;

import game.objects.Bullet;
import game.objects.Enemy;
import game.objects.MapObject;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.awt.Point;
import java.util.Vector;
/**
 * Luokka sisältää staattiset metodit eri tyyppisten törmäysten laskemiseen
 */
public class Collisions {

    public static boolean CircleLineCollision(MapObject e, Bullet bul){
        Point a=bul.getLastPoint();
        Point b=bul.getPoint();
        Point c=e.getPoint();
        double d=(double)e.getWidth()/2;
        double LAB = a.distance(b);
        double Dx = (b.x-a.x)/LAB;
        double Dy = (b.y-a.y)/LAB;

       
        double t = Dx*(c.x-a.x) + Dy*(c.y-a.y);   

        double Ex = t*Dx+a.x;
        double Ey = t*Dy+a.y;

        double LEC = Math.sqrt( Math.pow(Ex-c.x,2)+Math.pow(Ey-c.y,2) );

        if( LEC < d ){
            return true;
        }else{
            return false;
        }
    }

    public static boolean circleCircleCollision(MapObject mob, MapObject obj){
        if(mob.getPoint().distance(obj.getPoint())<mob.getWidth()/2.0+obj.getWidth()/2.0){
            return true;
        }
        return false;
    }


    
}
