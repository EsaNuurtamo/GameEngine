/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects.guns;

import game.gameLogic.PlayState;
import game.gui.Clicks;
import game.gui.MouseMovement;
import game.map.TileMap;
import java.awt.Point;

/**
 *
 * @author esa
 */
public class MachineGun extends Gun{

    public MachineGun(TileMap map) {
        super(map);
        fireRate=10;
        reloadtime=100;
        clipSize=30;
        inClip=clipSize;
        ammo=100;
    }

    @Override
    public void update(PlayState state) {
        if(Clicks.clicked){
            int x=MouseMovement.x-map.getX();
            int y=MouseMovement.y-map.getY();
            pullTrigger(new Point(x,y),state);
            
        }
    }

    
    
}
