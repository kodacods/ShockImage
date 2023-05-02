package cosc202.andie;

import java.util.*;
import java.util.prefs.Preferences;
import java.awt.event.*;
import javax.swing.*;


import java.awt.FlowLayout;
import java.awt.Point;


public class SelectionActions {
    protected ArrayList<Action> actions;
    /**
     * <p>
     * Create a set of Selection menu actions.
     * 
     * </p>
     */

    protected static Point selFirst, selSecond;
    protected static Point originPoint = new Point(0,0);
    protected static int selWidth, selHeight; 


    public SelectionActions() {

        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        ResourceBundle bundle = ResourceBundle.getBundle("TMessageBundle");
        
        

        actions = new ArrayList<Action>();
        actions.add(new RectangularSelectionAction(bundle.getString("SelectPortion"), null, "Select a rectangular portion of the image", Integer.valueOf(KeyEvent.VK_M)));
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
        }

        

        public void actionPerformed(ActionEvent e){
            //JLabel status = new JLabel("Select First Point");
            target.addMouseListener(this);
            System.out.println("ML Instantiated and Set");
            System.out.println("Should be primed for selecting");
            

            //int option = JOptionPane.showOptionDialog(null, "Select First Point", "Selection", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            //The problem is an optionpane won't let the user select anywhere on the initial image.
            //if(first != null){
                //status.setText("First Point Clicked");
            //}
            
            
        }

        public void mouseExited(MouseEvent e){}
        public void mouseReleased(MouseEvent e){
            second = e.getPoint();
            System.out.println("Second Point Clicked");
            System.out.println(second);
            firstCheck = false;
            selFirst = first;
            selSecond = second;
            target.removeMouseListener(this);
            System.out.println("Both Clicked. Opening menu of selection operations.");
            
            //realization: opening a menu :OOOOOOOOOOO
            openMenu();
        }
        public void mousePressed(MouseEvent e){
            first = e.getPoint();
            System.out.println("First Point Clicked");
            System.out.println(first);
            firstCheck = true;
        }
        public void mouseEntered(MouseEvent e){}
        public void mouseClicked(MouseEvent e) {  
            if(firstCheck == false){
               
            } else {
              

            }
        } 

        private void openMenu(){

            if(selFirst.x < selSecond.x){
                originPoint.x = selFirst.x;
                selWidth = selSecond.x - selFirst.x;
            } else {
                originPoint.x = selSecond.x;
                selWidth = selFirst.x - selSecond.x;
            }
    
            if(selFirst.y < selSecond.y){
                originPoint.y = selFirst.y;
                selHeight = selSecond.y - selFirst.y;
            } else {
                originPoint.y = selSecond.y;
                selHeight = selFirst.y - selSecond.y;
            }

            System.out.println("origin point: " + originPoint);

            target.getImage().apply(new RectangularShowSelection());
            target.repaint();
            target.getParent().revalidate();

            String[] options = {"Cancel", "Crop"};
            int option = JOptionPane.showOptionDialog(null, "Select functions go here.", "Selection Operations Menu", JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
            
            target.getImage().undo(); //so glad that it forces the user to finish interacting with the newlymade menu first before doing anything else
            target.repaint();
            target.getParent().revalidate();

            if (option == 1) {
                target.getImage().apply(new CropSelection());
                target.repaint();
                target.getParent().revalidate();
            } 
            selFirst = new Point(0,0);
            selSecond = new Point(0,0);
            originPoint = new Point(0,0);
            
        }

        //oh yeah by the way if you're drawing you're probably gonna need to make one below that doesn't open a menu
        //like above but it doesn't open an option pane

    }
}
