
package game.map;

import game.Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Pelin kartta joka 
 * @author esa
 */
public class TileMap {
    public static int mapSize=40;
    public static int tileSize=Main.TILE_SIZE;
    
    //ruudulla näkyvän mapin osuuden mitat
    private int colsToDraw=Main.WIDTH/tileSize+2;
    private int rowsToDraw=Main.HEIGHT/tileSize+2;
    
    //mapin ensimmäinen rivi ja sarake jotka näkyvissä
    //näistä piirtäminen aloitetaan
    private int startRow;
    private int startCol;
    
    //mapin vasemman yläkulman sijainti panelin yläkulamaan verrattuna
    private Point location;
    
    private int[][] map;
    public TileMap() {
        map=new int[mapSize][mapSize];
    }

    public int getStartCol() {
        return startCol;
    }

    public int getStartRow() {
        return startRow;
    }
    
    public void load(String s){
        InputStream in = getClass().getResourceAsStream(s);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int row=0;
        while(row<mapSize){
            String[] line=null;
            try {
                line = br.readLine().split(" ");
            }catch (IOException ex) {
                ex.printStackTrace();
            }
            for(int i=0; i<line.length;i++){
                map[i][row]=Integer.parseInt(line[i]);
            }
            row++;
        }
        System.out.println(map);
        
    }

    public int[][] getMap() {
        return map;
    }
    

    public void setLocation(Point location) {
        this.location = location;
        
        //ehdot jotta pyristetään eri lailla jos negatiivinen -0.3-->-1
        // 0.3-->0
        if(location.x>0){
            startCol=(int)-Math.ceil(location.x/(double)tileSize);
        }else{
            startCol=-location.x/tileSize;
        }
        
        if(location.y>0){
            startRow=(int)-Math.ceil(location.y/(double)tileSize);
        }else{
            startRow=-location.y/tileSize;
        }
    }

    public Point getLocation() {
        return location;
    }
    public int getX(){
        return location.x;
    }
    public int getY(){
        return location.y;
    }
    public void drawBackround(Graphics2D g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
    }
    
    public void draw(Graphics2D g){
        drawBackround(g);
        
        if(location==null)return;
        
        for(int curX = startCol;curX<=startCol+colsToDraw;curX++){
            for(int curY = startRow;curY<=startRow+rowsToDraw;curY++){
                if(curX<0||curX>=mapSize||curY<0||curY>=mapSize){
                    continue;
                }
                if(map[curX][curY]==1){
                    g.setColor(Color.RED);
                }else{
                    g.setColor(Color.LIGHT_GRAY);
                    
                }
                
                g.fill3DRect(location.x+curX*tileSize, 
                             location.y+curY*tileSize, tileSize, tileSize, true);
                
            }
        }
    }
}
