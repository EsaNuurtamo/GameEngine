
package game.gui;



import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * Säilyttää tietoa siitä mitkä näppäimet on painettuna
 */
public class Keys implements KeyListener{
    public static final int LEFT_KEY=0;
    public static final int RIGHT_KEY=1;
    public static final int UP_KEY=2;
    public static final int DOWN_KEY=3;
    public static final int ESC=4;
    public static final int E_KEY=5;
    public static final int KEY_COUNT=6;
    public static boolean[] keyState=new boolean[KEY_COUNT];
    
    public static boolean[] prevKeyState=new boolean[KEY_COUNT];

    
    
    
    /**
     * Asettaa totuusarvon oikean napin kohdalle listaan
     * 
     * @param i KeyEventistä painalluksen yhteydessä saatu eventID
     * @param b jos painetaan nappia niin true jos vapautetaan nappi niin false
     */
    public static void setKey(int i, boolean b) {
        if(i == KeyEvent.VK_W) keyState[UP_KEY] = b;
	else if(i == KeyEvent.VK_A) keyState[LEFT_KEY] = b;
	else if(i == KeyEvent.VK_S) keyState[DOWN_KEY] = b;
	else if(i == KeyEvent.VK_D) keyState[RIGHT_KEY] = b;
        else if(i == KeyEvent.VK_ESCAPE) keyState[ESC] = b;
        else if(i == KeyEvent.VK_E) keyState[E_KEY] = b;
    }
	
    /**
     * Update päivittää nykyiset painallukset edellisiksi
     * jos näppäin pohjassa niin sekä edellisen arvo ja nykyinen on true
     */
    public static void update() {
        for(int i = 0; i < KEY_COUNT; i++) {
            prevKeyState[i] = keyState[i];
	}
    }
	
    /**
     * jos aikaisemmin jo painettu nappi pohjaan,
     * niin ei enää uudestaan suorita napin aiheuttamaa reaktiota pelissä
     * eli ei esim liiku kuin kerran vaikka pidetään liikkumisnappia pohjassa
     * sillä prev==true ja nykyinen==true joten palauttaa false
     * @param i 
     */
    public static boolean isPressed(int i) {
	return keyState[i]&& !prevKeyState[i];
    }
	
    public static boolean anyKeyPress() {
	for(int i = 0; i < KEY_COUNT; i++) {
            if(keyState[i]) return true;
	}
	return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        setKey(e.getKeyCode(), true);
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        setKey(e.getKeyCode(), false);
    }
    
    
}

