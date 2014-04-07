/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tools;

import game.objects.Bullet;
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
 */
public class Geometry {

    public static boolean CircleLineCollision(MapObject e, Bullet bul){
        if(e.getPoint().distance(bul.getPoint())<e.getWidth()/2||
           e.getPoint().distance(bul.getLastPoint())<e.getWidth()/2){
           return true; 
        }
        return false;
        /*Point a=bul.getLastPoint();
        Point b=bul.getPoint();
        if(a.equals(b))return false;
        System.out.println(a+" "+b);
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
            double dt = Math.sqrt( Math.pow(e.getWidth()/2,2) - Math.pow(LEC, 2));
            // compute first intersection point
            double Fx = (t-dt)*Dx + a.x;
            double Fy = (t-dt)*Dy + a.y;
            Point one=new Point((int)Fx,(int)Fy);
            
            double Gx = (t+dt)*Dx + a.x;
            double Gy = (t+dt)*Dy + a.y;
            Point two=new Point((int)Gx,(int)Gy);
            
            if(one.distance(e.getPoint())<e.getWidth()+2||
               two.distance(e.getPoint())<e.getWidth()+2){
                return true;
            }
            
        }
        return false;*/
    }

    public static boolean circleCircleCollision(MapObject mob, MapObject obj){
        if(mob.getPoint().distance(obj.getPoint())<mob.getWidth()/2.0+obj.getWidth()/2.0){
            return true;
        }
        return false;
    }
    public static Point calculateAdvancement(MapObject obj, MapObject p){
        //lasketaan kolmio
        double dist=p.getPoint().distance(obj.getPoint());
        double dot=(p.getdX()*(obj.getX()-p.getX())+p.getdY()*(obj.getY()-p.getY()));
        double cosX=dot/(p.getSpeed()*dist);
        double angle1=Math.toDegrees(Math.acos(cosX));
        double angle2=180-90-angle1;
        
        double magnitude=(dist/2)/Math.sin(Math.toRadians(angle2));
        double coefficent=Math.abs(magnitude/p.getSpeed());
        
        double x=p.getX()+(coefficent*p.getdX());
        double y=p.getY()+(coefficent*p.getdY());
        if(p.getdX()==0)x=p.getX();
        if(p.getdY()==0)y=p.getY();
        
        return new Point((int)x,(int)y);
        
        
    }

    
}
