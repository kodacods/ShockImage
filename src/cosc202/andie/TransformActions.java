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
     * Create list of Transform menu actions
     * </p>
     */

    public TransformActions(){
        actions = new ArrayList<Action>();
        actions.add(new ResizeTransformAction("Resize", null, "Resize image", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new RotateAntiClock90TransformAction("Rotate Anticlockwise 90", null, "Rotate image anticlockwise by 90 degrees", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new RotateClock90TransformAction("Rotate Clockwise 90", null, "Rotate image clockwise by 90 degrees", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new Rotate180TransformAction("Rotate 180", null, "Rotate image by 180 degrees", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new FlipVertTransformAction("Flip Vertical", null, "Flip image vertically", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new FlipHorzTransformAction("Flip Horizontal", null, "Flip image horizontally", Integer.valueOf(KeyEvent.VK_M)));
    }

    /**
     * <p>
     * Creates the menu containing said list of image transformation actions.
     * 
     * @return The transform menu UI content.
     * </p>
     */

     public JMenu createMenu(){
        JMenu transformMenu = new JMenu("Transform");

        for(Action action: actions){
            transformMenu.add(new JMenuItem(action));
        }
        return transformMenu;
     }

    /**
     * <p>
     * Action to resize an image.
     * </p>
     * 
     * @see ResizeTransform
     */

     public class ResizeTransformAction extends ImageAction{
        /**
         * <p>
         * Create a new resize action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */

        ResizeTransformAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the resize action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ResizeTransformAction is triggered.
         * It prompts the user for a resize factor, then applys an appropriately sized {@link ResizeTransform}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */

        public void actionPerformed(ActionEvent e){
            // Determine the scale factor - ask the user.
            double scaleFactor = 1.0;

            // Pop-up dialog box to ask for the factor value.
            SpinnerNumberModel scaleModel = new SpinnerNumberModel(1.0, 0.0, 10.0, 0.1);
            JSpinner scaleSpinner = new JSpinner(scaleModel);
            int option = JOptionPane.showOptionDialog(null, scaleSpinner, "Enter scale factor", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                scaleFactor = scaleModel.getNumber().doubleValue();
                System.out.println(scaleFactor);
            }

            // Create and apply the transform
            target.getImage().apply(new ResizeTransform(scaleFactor));
            target.repaint();
            target.getParent().revalidate();
        }

     }

     /**
     * <p>
     * Action to rotate an image 90 degrees anticlockwise.
     * </p>
     * 
     * @see RotateAntiClock90Transform
     */

     public class RotateAntiClock90TransformAction extends ImageAction{
        /**
         * <p>
         * Create a new rotate anti-clockwise 90 degrees action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        RotateAntiClock90TransformAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e){
            target.getImage().apply(new RotateAntiClock90Transform());
            target.repaint();
            target.getParent().revalidate();
        }

     }

     /**
     * <p>
     * Action to rotate an image 90 degrees clockwise.
     * </p>
     * 
     * @see RotateClock90Transform
     */

     public class RotateClock90TransformAction extends ImageAction{
        /**
         * <p>
         * Create a new rotate clockwise 90 degrees action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        RotateClock90TransformAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e){
            target.getImage().apply(new RotateClock90Transform());
            target.repaint();
            target.getParent().revalidate();
        }

     }

    /**
     * <p>
     * Action to rotate an image 180 degrees.
     * </p>
     * 
     * @see Rotate180Transform
     */

     public class Rotate180TransformAction extends ImageAction{
        //baller .
        Rotate180TransformAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e){
            target.getImage().apply(new Rotate180Transform());
            target.repaint();
            target.getParent().revalidate();
        }

     }

     /**
     * <p>
     * Action to flip an image vertically.
     * </p>
     * 
     * @see FlipVertTransform
     */

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

    /**
     * <p>
     * Action to flip an image horizontally.
     * </p>
     * 
     * @see FlipHorzTransform
     */

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
