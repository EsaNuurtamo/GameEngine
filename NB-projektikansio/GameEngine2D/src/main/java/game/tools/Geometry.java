/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tools;

import game.objects.guns.Bullet;
import game.objects.Enemy;
import game.objects.MapObject;
import game.objects.Player;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.awt.Point;
import java.util.Vector;
/**
 * Luokka sisältää staattiset metodit eri tyyppisten törmäysten laskemiseen
 * ja muihin
 */
public class Geometry {

    public static boolean CircleLineCollision(MapObject e, Bullet bul){
        //väliaikainen nyt testaa vain janan päät
        if(e.getPoint().distance(bul.getPoint())<e.getWidth()/2||
           e.getPoint().distance(bul.getLastPoint())<e.getWidth()/2){
           return true; 
        }
        return false;
        
    }

    public static boolean circleCircleCollision(MapObject mob, MapObject obj){
        if(mob.getPoint().distance(obj.getPoint())<mob.getWidth()/2.0+obj.getWidth()/2.0){
            return true;
        }
        return false;
    }
    /**
     * Laskee ennakon jos haltutaan että objekti yrittää osua toiseen objektiin
     * esim. kuinka paljon liikkuvan hahmon eteen pitää tähdätä jotta luoti osuu
     * 
    */
    public static Point calculateAdvancement(MapObject obj, MapObject p){
        //lasketaan kolmio
        double dist=p.getPoint().distance(obj.getPoint());
        double dot=(p.getdX()*(obj.getX()-p.getX())+p.getdY()*(obj.getY()-p.getY()));
        double cosX=dot/(p.getSpeed()*dist);
        double angle1=Math.toDegrees(Math.acos(cosX));
        double dot2=(obj.getdX()*(p.getX()-obj.getX())+obj.getdY()*(p.getY()-obj.getY()));
        double angle2=Math.toDegrees(Math.acos(dot2/(obj.getSpeed()*dist)));
        double angle3=Math.toRadians(180-angle1-angle2);
        
        double distRelative=
                Math.sqrt(Math.pow(p.getSpeed(), 2)+Math.pow(obj.getSpeed(),2)-2*p.getSpeed()*obj.getSpeed()*Math.cos(angle3));
        
        
        double coefficent=(dist/distRelative);
        
        double x=p.getX()+(coefficent*p.getdX());
        double y=p.getY()+(coefficent*p.getdY());
        if(p.getdX()==0)x=p.getX();
        if(p.getdY()==0)y=p.getY();
        
        return new Point((int)x,(int)y);
        
        
    }

    
}
