/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects.guns;

import game.objects.guns.Gun;
import game.objects.guns.Bullet;
import game.gameLogic.PlayState;
import game.map.TileMap;
import java.awt.Point;

/**
 *
 * @author esa
 */
public class Pistol extends Gun{

    public Pistol(TileMap map) {
        super(map);
        fireRate=20;
        reloadtime=60;
        clipSize=10;
        inClip=clipSize;
        ammo=50;
    }
    
}
