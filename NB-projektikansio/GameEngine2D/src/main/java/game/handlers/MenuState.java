
package game.handlers;

import game.Main;
import game.gui.Keys;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class MenuState extends GameState{
    
    
    
    private MenuNode currentNode;
    private int selected=0;
    public static final Font TITLE_FONT=new Font("Constantia", Font.BOLD, 60);
    
    public static final Font FONT=new Font("Constantia", Font.BOLD, 40);
    
    public MenuState(GameHandler sh) {
        super(sh);
        MenuNode main=
        new MenuNode("GameEngine2D",new MenuNode("Play"),
                            new MenuNode("Tutorial"),
                            new MenuNode("Options",new MenuNode("Game Options"),
                                                   new MenuNode("Video Options"),
                                                   new MenuNode("Audio Options")),
                            new MenuNode("Controls",new MenuNode("View Controls"),
                                                    new MenuNode("Edit Controls")),
                            new MenuNode("Exit"));
            
            
        
        currentNode=main;
        
        
            
    }

    @Override
    public void init() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update() {
        
        if(currentNode.getName().equals("Exit")){
            System.exit(0);
        }else if(currentNode.getName().equals("Play")){
            gameHandler.setState(GameHandler.PLAY_STATE);
        }
        
        handleInput();
    }

    @Override
    public void draw(Graphics2D g) {
        //piirtää taustan
        if(currentNode==null)return;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
        
        //piirrä liikkumisohje
        g.setColor(Color.WHITE);
        g.setFont(Main.DEBUG);
        g.setFont(Font.getFont("Arial"));
        int start=50;
        g.drawString("ESC -previous", 20, start);
        g.drawString("W,A,S,D -move", 20, start+30);
        g.drawString("ENTER -go to", 20, start+60);
        
        //Otsikko
        g.setColor(Color.WHITE);
        g.setFont(TITLE_FONT);
        int titleMid=g.getFontMetrics().stringWidth(currentNode.getName())/2;
        g.drawString(currentNode.getName(), Main.WIDTH/2-titleMid, 100);
        
        g.setFont(FONT);
        
        int i=0;
        for(MenuNode m:currentNode.getChildren()){
            if(selected==i){
                g.setColor(Color.YELLOW);
            }else{
                g.setColor(Color.WHITE);
            }
            int mid=g.getFontMetrics().stringWidth(m.getName())/2;
            g.drawString(m.getName(), Main.WIDTH/2-mid, 250+i*70);
            i++;
        }
    }

    @Override
    public void handleInput() {
        if(Keys.isPressed(Keys.ESC)){
            if(currentNode.getParent()!=null){
                currentNode=currentNode.getParent();
            }
        }
        if(currentNode==null)return;
        if(Keys.isPressed(Keys.DOWN_KEY)){
           
            selected++;
        }
        if(Keys.isPressed(Keys.UP_KEY)){
            selected--;
        }
        if(selected>currentNode.getChildren().size()-1)selected=0;
        if(selected<0)selected=currentNode.getChildren().size()-1;
        if(Keys.isPressed(Keys.ENTER)){
            enter();
        }
        Keys.update();    
    }
    
    public void enter(){
        
        currentNode=currentNode.getChildren().get(selected);
        selected=0;
    }
    
}
