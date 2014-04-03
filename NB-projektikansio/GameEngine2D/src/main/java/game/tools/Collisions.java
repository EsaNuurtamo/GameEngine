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

public class Collisions {

    public static boolean CircleLineCollision(MapObject e, Bullet bul){
        Point a=bul.getLastPoint();
        Point b=bul.getPoint();
        Point c=e.getPoint();
        double d=(double)e.getWidth()/2;
        
        // compute the euclidean distance between A and B
        double LAB = a.distance(b);

        // compute the direction vector D from A to B
        double Dx = (b.x-a.x)/LAB;
        double Dy = (b.y-a.y)/LAB;

        // Now the line equation is x = Dx*t + Ax, y = Dy*t + Ay with 0 <= t <= 1.

        // compute the value t of the closest point to the circle center (Cx, Cy)
        double t = Dx*(c.x-a.x) + Dy*(c.y-a.y);   

        // This is the projection of C on the line from A to B.

        // compute the coordinates of the point E on line and closest to C
        double Ex = t*Dx+a.x;
        double Ey = t*Dy+a.y;

        // compute the euclidean distance from E to C
        double LEC = Math.sqrt( Math.pow(Ex-c.x,2)+Math.pow(Ey-c.y,2) );

        // test if the line intersects the circle
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
