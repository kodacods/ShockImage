package cosc202.andie;

import java.util.*;
import java.util.prefs.Preferences;
import java.awt.Point;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Transform menu.
 * </p>
 * 
 * <p>
 * The Transform menu encompasses the following tasks regarding image
 * modification:
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

    public TransformActions() {

        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        ResourceBundle bundle = ResourceBundle.getBundle("TMessageBundle");

        actions = new ArrayList<Action>();
        actions.add(new ResizeTransformAction(bundle.getString("Resize"), null, "Resize image",
                Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new RotateAntiClock90TransformAction(bundle.getString("RotateAnticlockwise90"), null,
                "Rotate image anticlockwise by 90 degrees", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new RotateClock90TransformAction(bundle.getString("RotateClockwise90"), null,
                "Rotate image clockwise by 90 degrees", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new Rotate180TransformAction(bundle.getString("Rotate180"), null, "Rotate image by 180 degrees",
                Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new FlipVertTransformAction(bundle.getString("FlipVertical"), null, "Flip image vertically",
                Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new FlipHorzTransformAction(bundle.getString("FlipHorizontal"), null, "Flip image horizontally",
                Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new StampAction(bundle.getString("Stamp"), null, "Stamp image onto current image", Integer.valueOf(KeyEvent.VK_M)));
    }

    /**
     * <p>
     * Creates the menu containing said list of image transformation actions.
     * 
     * @return The transform menu UI content.
     *         </p>
     */

    public JMenu createMenu() {
        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        ResourceBundle bundle = ResourceBundle.getBundle("TMessageBundle");

        JMenu transformMenu = new JMenu(bundle.getString("Transform"));

        for (Action action : actions) {
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

    public class ResizeTransformAction extends ImageAction {
        /**
         * <p>
         * Create a new resize action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
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
         * It prompts the user for a resize factor, then applys an appropriately sized
         * {@link ResizeTransform}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */

        public void actionPerformed(ActionEvent e) {
            if (!target.getImage().hasImage()) {
                JOptionPane.showMessageDialog(null, "You need to open an image first!");
                return;
            }

            // Determine the scale factor - ask the user.
            double scaleFactor = 1.0;

            // Pop-up dialog box to ask for the factor value.
            SpinnerNumberModel scaleModel = new SpinnerNumberModel(1.0, 0.0, 10.0, 0.1);
            JSpinner scaleSpinner = new JSpinner(scaleModel);
            int option = JOptionPane.showOptionDialog(null, scaleSpinner, "Enter scale factor",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

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

    public class RotateAntiClock90TransformAction extends ImageAction {
        /**
         * <p>
         * Create a new rotate anti-clockwise 90 degrees action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        RotateAntiClock90TransformAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
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

    public class RotateClock90TransformAction extends ImageAction {
        /**
         * <p>
         * Create a new rotate clockwise 90 degrees action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        RotateClock90TransformAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
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

    public class Rotate180TransformAction extends ImageAction {
        // baller .
        Rotate180TransformAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
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

    public class FlipVertTransformAction extends ImageAction {

        FlipVertTransformAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
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

    public class FlipHorzTransformAction extends ImageAction {

        FlipHorzTransformAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new FlipHorzTransform());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to stamp another image onto the current image
     * </p>
     * 
     * @see Stamp
     */
    public class StampAction extends ImageAction implements MouseListener {

        private int x = -1, y = -1;
        private BufferedImage stamp;

        /**
         * <p>
         * Create a new stamp action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        StampAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * This param is called when the mouse is released. The
         * MouseListener is only added when an image is chosen and the
         * window is selected. It draws the image onto the current image.
         * 
         * @param e mouseevent
         */
        public void mouseReleased(MouseEvent e) {
            Point p = e.getPoint();
            double positionScale = target.getZoom() / 100.0;
            this.x = (int) Math.round(p.x / positionScale);
            this.y = (int) Math.round(p.y / positionScale);
            target.getImage().apply(new Stamp(stamp, x, y));
            target.repaint();
            target.getParent().revalidate();
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
        }

        public void disable() {
            target.removeMouseListener(this);
            target.addMouseListener(target);
        }

        /**
         * This method shows a file chooser to the user to pick which image
         * to use as the stamp. It them informs the user of how to use
         * the stamp, and applies the stamp action when the mouse is
         * clicked. When the escape key is pressed, it stops listening for
         * mouse clicks.
         * 
         * @param e action event
         */
        public void actionPerformed(ActionEvent e) {
            if (!target.getImage().hasImage()) {
                JOptionPane.showMessageDialog(null, "You need to open an image first!");
                return;
            }

            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    File imageFile = new File(imageFilepath);
                    stamp = ImageIO.read(imageFile);
                    JOptionPane.showMessageDialog(null, "Click to place stamps, press escape to cancel");
                    target.setFocusable(true);
                    KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);

                    ActionListener action = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            disable();
                        }
                    };
                    target.registerKeyboardAction(action, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
                    target.addMouseListener(this);
                    target.removeMouseListener(target);
                } catch (Exception ex) {
                    // If file is not valid, give the user an error message and restart the file
                    // opening process.
                    JOptionPane.showMessageDialog(null, "Please open a valid image. (GIF, PNG, JPEG, BMP, or WBMP)");
                    actionPerformed(e);
                    return;
                }
            }
        }
    }
}
