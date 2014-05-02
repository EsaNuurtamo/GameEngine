
package game.gui;

import game.gui.MenuNode;
import game.Main;
import game.gui.Keys;
import game.gameLogic.GameHandler;
import game.gameLogic.GameHandler;
import game.gameLogic.GameHandler;
import game.gameLogic.GameState;
import game.gameLogic.GameState;
import game.gameLogic.GameState;
import game.map.TileMap;
import game.objects.guns.Bullet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class MenuState extends GameState{
    
    private int ticker=0;
    //lista taustalla valikon taustalla liikkuvista tähdistä
    //tähdet ovat Bullet luokan ilmentymiä
    private List<Bullet> stars=new ArrayList<Bullet>();
    private MenuNode currentNode;
    private int selected=0;
    public static final Font TITLE_FONT=new Font("Constantia", Font.BOLD, 60);
    
    public static final Font FONT=new Font("Constantia", Font.BOLD, 40);
    
    public MenuState(GameHandler sh) {
        super(sh);
        //luodaan puu
        MenuNode main=
        new MenuNode("GameEngine2D",new MenuNode("Play"),
                                    new MenuNode("Tutorial"),
                                    new MenuNode("Options", new MenuNode("Game Options"),
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
        //päivittää päävalikon taustan
        if(currentNode.getName().equals("GameEngine2D")) {
            updateBackGround();
        }
        if(currentNode.getName().equals("Exit")){
            System.exit(0);
        }else if(currentNode.getName().equals("Play")){
            gameHandler.setState(GameHandler.PLAY_STATE);
        }
        
        handleInput();
        ticker++;
    }
    public void updateBackGround(){
        if(ticker%5==0){     

                TileMap map=new TileMap();
                map.setLocation(new Point(0,0)); 
                for(int i=0; i<5;i++){
                    int x=(int)(Math.random()*Main.WIDTH);
                    int y=(int)(Math.random()*Main.HEIGHT);
                    Bullet b=new Bullet(new Point(Main.WIDTH/2,Main.HEIGHT/2),map );
                    b.setSpeed((int)(5+Math.random()*3));
                    b.calculateVector(new Point(x,y));

                    int r=1;
                    b.setWidth(r);
                    b.setHeight(r);
                    b.setColor(Color.YELLOW);
                    stars.add(b);
                }
            }


            if(stars.size()>400){
                for(int i=0;i<100;i++){
                    stars.remove(i);
                }
            }


            for(Bullet bul:stars){
                if(ticker%8==0){
                    int d=(int)(bul.getWidth()+bul.getSpeed()/3);
                    bul.setWidth(d);
                    bul.setHeight(d);
                    
                }

                bul.getPoint().translate(bul.getdX(), bul.getdY());
            }
    }
    @Override
    public void draw(Graphics2D g) {
        //piirtää taustan
        if(currentNode==null)return;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
        
        //piirtää tähdet
        if(currentNode.getName().equals("GameEngine2D")){
            for(Bullet b:stars){
            b.draw(g);
            }
            
        }
        
        
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
        if(currentNode.getName().equals("Tutorial")){
            String[] tutorial=
            {"Move around with keys W,A,S,D",
             "Use mouse to shoot and aim",
             "--------------------------",
             "STAYING ALIVE:",
             "-Avoid hitting enemies-",
             "-Watch out for bullets, they do the most damage",
             "-----------------------------------------------",
             "ENEMIES:",
            "-Try to kill as many enemies as you can-",
            "-Enemies spawn from flashing spawning points-",
            "-Enemies start spawn faster when time goes by!-"};
            for(int a=0;a<tutorial.length;a++){
                
                
                g.setFont(new Font("Constantia",Font.PLAIN,25));
                int mid=g.getFontMetrics().stringWidth(tutorial[a])/2;
                g.drawString(tutorial[a], Main.WIDTH/2-mid, 250+a*40);    
            }
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
