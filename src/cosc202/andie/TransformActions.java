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
        actions.add(new RotateAntiClock90TransformAction("Rotate Anticlockwise 90", null, "Rotate image anticlockwise by 90 degrees", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new FlipVertTransformAction("Flip Vertical", null, "Flip image vertically", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new FlipHorzTransformAction("Flip Horizontal", null, "Flip image horizontally", Integer.valueOf(KeyEvent.VK_M)));
    }

    /**
     * <p>
     * Creates the menu containing said list of image transformation actions.
     * </p>
     */

     public JMenu createMenu(){
        JMenu transformMenu = new JMenu("Transform");

        for(Action action: actions){
            transformMenu.add(new JMenuItem(action));
        }
        return transformMenu;
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

     public class RotateAntiClock90TransformAction extends ImageAction{
        //baller .
        RotateAntiClock90TransformAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e){
            target.getImage().apply(new RotateAntiClock90Transform());
            target.repaint();
            target.getParent().revalidate();
        }

     }

     public class FlipVertTransformAction extends ImageAction{
        
        FlipVertTransformAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e){ 
            target.getImage().apply(new FlipVertTransform());
            target.repaint();
            target.getParent().revalidate(); 
        }
     }

     public class FlipHorzTransformAction extends ImageAction{
        
        FlipHorzTransformAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e){ 
            target.getImage().apply(new FlipHorzTransform());
            target.repaint();
            target.getParent().revalidate(); 
        }
     }
}
