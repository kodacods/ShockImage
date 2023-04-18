package cosc202.andie;

import java.util.*;
import java.util.prefs.Preferences;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Colour menu.
 * </p>
 * 
 * <p>
 * The Colour menu contains actions that affect the colour of each pixel
 * directly
 * without reference to the rest of the image.
 * This includes conversion to greyscale in the sample code, but more operations
 * will need to be added.
 * </p>
 * 
 * Adding comment to test commmit
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class ColourActions {

    /** A list of actions for the Colour menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Colour menu actions.
     * </p>
     */
    public ColourActions() {
        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        ResourceBundle bundle = ResourceBundle.getBundle("TMessageBundle");
        
        actions = new ArrayList<Action>();
        actions.add(new ConvertToGreyAction(bundle.getString("Greyscale"), null, "Convert to greyscale", Integer.valueOf(KeyEvent.VK_G)));
        actions.add(new ContrastAction(bundle.getString("Contrast"), null, "Adjust contrast", Integer.valueOf(KeyEvent.VK_G)));
        actions.add(new BrightnessAction(bundle.getString("Brightness"), null, "Adjust brightness", Integer.valueOf(KeyEvent.VK_G)));
    }

    /**
     * <p>
     * Create a menu contianing the list of Colour actions.
     * </p>
     * 
     * @return The colour menu UI element.
     */
    public JMenu createMenu() {
        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        ResourceBundle bundle = ResourceBundle.getBundle("TMessageBundle");
        
        JMenu fileMenu = new JMenu(bundle.getString("Colour"));

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to convert an image to greyscale.
     * </p>
     * 
     * @see ConvertToGrey
     */
    public class ConvertToGreyAction extends ImageAction {

        /**
         * <p>
         * Create a new convert-to-grey action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ConvertToGreyAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ConvertToGreyAction is triggered.
         * It changes the image to greyscale.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ConvertToGrey());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to adjust the contrast of an image.
     * </p>
     * 
     * @see Contrast
     */
    public class ContrastAction extends ImageAction {

        /**
         * <p>
         * Create a new Contrast action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ContrastAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the ContrastrAction is triggered.
         * It prompts the user for a percentage of the contrast to be adjusted by
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the percentage to change contrast entered by the user.
            int percentage = 0;

            // Pop-up dialog box to ask for the percentage.
            SpinnerNumberModel percentModel = new SpinnerNumberModel(0, -90, 90, 10);
            JSpinner percentSpinner = new JSpinner(percentModel);
            int option = JOptionPane.showOptionDialog(null, percentSpinner, "Enter percentage to change contrast",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                percentage = percentModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new Contrast(percentage));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to adjust brightness of image.
     * </p>
     * 
     * @see Brightness
     */
    public class BrightnessAction extends ImageAction {

        /**
         * <p>
         * Create a new Brightness action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        BrightnessAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the BrightnessAction is triggered.
         * It prompts the user for a percentage to adjust the brightness.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the percentage to change contrast entered by the user.
            int percentage = 0;

            // Pop-up dialog box to ask for the percentage value.
            SpinnerNumberModel percentModel = new SpinnerNumberModel(0, -90, 90, 10);
            JSpinner percentSpinner = new JSpinner(percentModel);
            int option = JOptionPane.showOptionDialog(null, percentSpinner, "Enter percentage to change brightness",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                percentage = percentModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new Brightness(percentage));
            target.repaint();
            target.getParent().revalidate();
        }
    }

}
