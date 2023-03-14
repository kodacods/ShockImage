package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Transform menu.
 * </p>
 * 
 * <p>
 * The Transform menu encompasses the following tasks regarding image modification:
 * Rotation, reflection / flip and dilation / resize.
 * </p>
 * 
 * @author Meg Albarico
 * @version 1.0
 */

public class TransformActions {
    /** List of actions for Transform menu */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create list of actions
     * </p>
     */

    public TransformActions(){
        actions = new ArrayList<Action>();
        actions.add(new ResizeTransformAction("Resize", null, "Resize image", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new ResizeTransformAction("Flip", null, "Flip image", Integer.valueOf(KeyEvent.VK_M)));
    }

    /**
     * <p>
     * Creates the menu containing said list of image transformation actions.
     * </p>
     */

     public JMenu createMenu(){
        JMenu fileMenu = new JMenu("Transform");

        for(Action action : actions){
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
     }

     public class ResizeTransformAction extends ImageAction{
        //baller .
        ResizeTransformAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e){
            target.getImage().apply(new ResizeTransform());
            target.repaint();
            target.getParent().revalidate();
        }

     }

     public class FlipTransformAction extends ImageAction{
        //hmm
        FlipTransformAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e){
            target.getImage().apply(new FlipTransform());
            target.repaint();
            target.getParent().revalidate();
        }

     }
}
