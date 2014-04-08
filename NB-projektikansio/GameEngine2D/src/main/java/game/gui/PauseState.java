/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui;

import game.gui.MenuState;
import game.Main;
import game.gui.Keys;
import game.gameLogic.GameHandler;
import game.gameLogic.GameState;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author esa
 */
public class PauseState extends GameState{
    private String[] options={"Resume", "Save","Load", "Main menu", "Quit game"};
    private int selected=0;
    public PauseState(GameHandler sh) {
        super(sh);
    }
    
    public void enter(){
        if(selected==0){
            gameHandler.setPaused(false);
        }else if(selected==1){
            //saving
        }else if(selected==2){
            //loading
        }else if(selected==3){
            gameHandler.setPaused(false);
            gameHandler.setState(GameHandler.MENU_STATE);
        }else if(selected==4){
            System.exit(0);
        }
    }

    @Override
    public void init() {
    
    }

    @Override
    public void update() {
        handleInput();
    }

    @Override
    public void draw(Graphics2D g) {
        
        //piirtää taustan
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
        
      
        
        
        
        //piirtää otsikon
        g.setColor(Color.WHITE);
        g.setFont(MenuState.TITLE_FONT);
        int titleMid=g.getFontMetrics().stringWidth("Paused")/2;
        g.drawString("Paused", Main.WIDTH/2-titleMid, 100);
        
        //piirtää valikon kohdat
        for(int i=0; i<options.length;i++){
            if(selected==i){
                g.setColor(Color.YELLOW);
            }else{
                g.setColor(Color.WHITE);
            }
            g.setFont(MenuState.FONT);
            int mid=g.getFontMetrics().stringWidth(options[i])/2;
            g.drawString(options[i], Main.WIDTH/2-mid, 250+i*70);
            
        }
    }

    @Override
    public void handleInput() {
        if(Keys.isPressed(Keys.DOWN_KEY)){
            
            selected++;
        }
        if(Keys.isPressed(Keys.UP_KEY)){
            selected--;
        }
        if(selected>options.length-1)selected=0;
        if(selected<0)selected=options.length-1;
        if(Keys.isPressed(Keys.ENTER)){
            enter();
        }
        Keys.update();
    }
        
    
            
    
}
