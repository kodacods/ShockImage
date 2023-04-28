package cosc202.andie;

import java.util.*;
import java.util.prefs.Preferences;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

/**
 * <p>
 * Actions provided by the Filter menu.
 * </p>
 * 
 * <p>
 * The Filter menu contains actions that update each pixel in an image based on
 * some small local neighbourhood.
 * This includes a Mean Filter, a Sharpen Filter, a Median filter, a Gaussian
 * Blur and an Emboss filter.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class FilterActions {

    /** A list of actions for the Filter menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Filter menu actions.
     * </p>
     */
    public FilterActions() {

        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        ResourceBundle bundle = ResourceBundle.getBundle("TMessageBundle");

        actions = new ArrayList<Action>();
        actions.add(new GaussianBlurAction(bundle.getString("GaussianBlur"), null, "Apply a Sharpen filter",
                Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new MeanFilterAction(bundle.getString("MeanFilter"), null, "Apply a mean filter",
                Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new MedianFilterAction(bundle.getString("MedianFilter"), null, "Apply a Median filter",
                Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new SharpenFilterAction(bundle.getString("SharpenFilter"), null, "Apply a Sharpen filter",
                Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new EmbossFilterAction(bundle.getString("EmbossFilter"), null, "Apply an Emboss filter",
                Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new SobelFilterAction(bundle.getString("SobelFilter"), null, "Apply an Emboss filter",
                Integer.valueOf(KeyEvent.VK_M)));
    }

    /**
     * <p>
     * Create a menu contianing the list of Filter actions.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {

        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        ResourceBundle bundle = ResourceBundle.getBundle("TMessageBundle");

        JMenu fileMenu = new JMenu(bundle.getString("Filter"));

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Ask the user for a radius for the filter
     * </p>
     * 
     * @return a radius value from 0 to 10.
     */
    public int getRadius() {
        // Pop-up dialog box to ask for the radius value.
        SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
        JSpinner radiusSpinner = new JSpinner(radiusModel);
        int option = JOptionPane.showOptionDialog(null, radiusSpinner, "Enter filter radius (1-10)",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        // Check the return value from the dialog box.
        if (option == JOptionPane.CANCEL_OPTION) {
            return 0;
        } else if (option == JOptionPane.OK_OPTION) {
            return radiusModel.getNumber().intValue();
        }

        return 0;
    }

    /**
     * <p>
     * Ask the user for a direction for the emboss filter,
     * and apply the filter to the image in real time.
     * </p>
     */
    public void getDirection() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));
        String[] directions = { "↖", "↑", "↗", "←", "", "→", "↙", "↓", "↘" };
        ImagePanel target = ImageAction.getTarget();
        BufferedImage og = target.getImage().getCurrentImage();

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (!target.getImage().getCurrentImage().equals(og)) {
                    target.getImage().undo();
                }

                // Create and apply the filter
                target.getImage().apply(
                        new EmbossFilter(java.util.Arrays.asList(directions).indexOf(actionEvent.getActionCommand())));
                target.repaint();
                target.getParent().revalidate();
            }
        };

        for (int i = 0; i < directions.length; i++) {
            String dir = directions[i];
            if (dir.equals("")) {
                JPanel p = new JPanel();
                buttonPanel.add(p);
            } else {
                JButton b = new JButton(dir);
                b.addActionListener(actionListener);
                buttonPanel.add(b);
            }
        }

        int option = JOptionPane.showOptionDialog(null, buttonPanel, "Choose Direction of Emboss Filter",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        // Check the return value from the dialog box.
        if (option == JOptionPane.CANCEL_OPTION) {
            target.getImage().undo();
            target.repaint();
            target.getParent().revalidate();
            return;
        } else if (option == JOptionPane.OK_OPTION) {
            return;
        }
    }

    /**
     * <p>
     * Action to blur an image with a mean filter.
     * </p>
     * 
     * @see MeanFilter
     */
    public class MeanFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new mean-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MeanFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * It prompts the user for a filter radius, then applys an appropriately sized
         * {@link MeanFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            int radius = getRadius();

            // Create and apply the filter
            target.getImage().apply(new MeanFilter(radius));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to blur an image with a median filter.
     * </p>
     * 
     * @see MeanFilter
     */
    public class MedianFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new median-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MedianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the MedianFilterAction is triggered.
         * It prompts the user for a filter radius, then applys an appropriately sized
         * {@link MeanFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            int radius = getRadius();

            // Create and apply the filter
            target.getImage().apply(new MedianFilter(radius));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to blur an image with a sharpen filter.
     * </p>
     * 
     * @see SharpenFilter
     */
    public class SharpenFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new sharpen-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SharpenFilterAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the SharpenFilterAction is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new SharpenFilter());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class GaussianBlurAction extends ImageAction {

        /**
         * <p>
         * Create a new gaussian-blur action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        GaussianBlurAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the GuassianBlurAction is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            int radius = getRadius();

            // Create and apply the filter
            target.getImage().apply(new GaussianBlur(radius));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to blur an image with a Emboss filter.
     * </p>
     * 
     * @see EmbossFilter
     */
    public class EmbossFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new Emboss-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        EmbossFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the EmbossFilterAction is triggered.
         * {@link EmbossFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            getDirection();
        }

    }

    /**
     * <p>
     * Action to blur an image with a Sobel filter.
     * </p>
     * 
     * @see SobelFilter
     */
    public class SobelFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new Sobel-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SobelFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the SobelFilterAction is triggered.
         * {@link SobelFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            sobelFilterPopup();
        }

        public void sobelFilterPopup() {
            JPanel buttonPanel = new JPanel();
            String[] directions = { "← →", "↑ ↓" };
            ImagePanel target = ImageAction.getTarget();
            BufferedImage og = target.getImage().getCurrentImage();

            ActionListener actionListener = new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    if (!target.getImage().getCurrentImage().equals(og)) {
                        target.getImage().undo();
                    }

                    // Create and apply the filter
                    target.getImage().apply(
                            new SobelFilter(
                                    java.util.Arrays.asList(directions).indexOf(actionEvent.getActionCommand()) == 1));
                    target.repaint();
                    target.getParent().revalidate();
                }
            };

            for (int i = 0; i < directions.length; i++) {
                String dir = directions[i];
                if (dir.equals("")) {
                    JPanel p = new JPanel();
                    buttonPanel.add(p);
                } else {
                    JButton b = new JButton(dir);
                    b.addActionListener(actionListener);
                    buttonPanel.add(b);
                }
            }

            int option = JOptionPane.showOptionDialog(null, buttonPanel, "Choose Direction of Emboss Filter",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                if (!target.getImage().getCurrentImage().equals(og)) {
                    target.getImage().undo();
                    target.repaint();
                    target.getParent().revalidate();
                }
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                return;
            }
        }

    }
}
