/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.gui;
import java.util.ArrayList;
import java.util.List;

/**
 * Luokka kuvaa tksittäisen valikkosivun ominaisuuksia
 * 
 */
public class MenuNode {
    private MenuNode parent=null;
    private List<MenuNode> children=new ArrayList<MenuNode>();
    private String nodeName;

    public MenuNode(String nodeName,MenuNode... child) {
        this.nodeName=nodeName;
        for(int i=0;i<child.length;i++){
            MenuNode n=child[i];
            n.setParent(this);
            children.add(n);
        }
    }

    public String getName() {
        return nodeName;
    }
    
    public List<MenuNode> getChildren() {
        return children;
    }

    public MenuNode getParent() {
        return parent;
    }

    public void setParent(MenuNode parent) {
        this.parent = parent;
    }
    
    
}
