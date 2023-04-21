package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Point;

import java.util.prefs.Preferences;


public class SelectionActions {
    protected ArrayList<Action> actions;
    /**
     * <p>
     * Create a set of Selection menu actions.
     * </p>
     */

    protected static Point selFirst;
    protected static Point selSecond;
    protected static boolean isSelected = false;

    public SelectionActions() {

    Preferences prefs = Preferences.userNodeForPackage(Andie.class);
    Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
    ResourceBundle bundle = ResourceBundle.getBundle("TMessageBundle");

        actions = new ArrayList<Action>();
    }

    /**
     * <p>
     * Create a menu contianing the list of Selection actions.
     * </p>
     * 
     * @return The Selection menu UI element.
     */
    public JMenu createMenu() {

        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        ResourceBundle bundle = ResourceBundle.getBundle("TMessageBundle");

        JMenu selectMenu = new JMenu(bundle.getString("Selection"));

        for(Action action: actions) {
            selectMenu.add(new JMenuItem(action));
        }

        return selectMenu;
    }

    public class RectangularSelectionAction extends ImageAction implements MouseListener{

        private Point first;
        private Point second;
        private boolean firstCheck;

        RectangularSelectionAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            target.addMouseListener(this);
        }

        public void mouseEntered(MouseEvent e) { 
        }  
        public void mouseExited(MouseEvent e) {  
        }  
        public void mousePressed(MouseEvent e) {  //drag-click looks a lot easier now, just as easy as 2-clicking
        }  
        public void mouseReleased(MouseEvent e) {  
        }  
        public void mouseClicked(MouseEvent e) {  
            if(firstCheck == false){
                first = e.getPoint();
                firstCheck = true;
            } else {
                second = e.getPoint();
            }
        }  

        public void actionPerformed(ActionEvent e){
            
        }

        /* Steps:
         * 01: Make panel informing of selection state, how many times clicked, 
         */
    }
}
