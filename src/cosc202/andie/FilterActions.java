package cosc202.andie;

import java.util.*;
import java.util.prefs.Preferences;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
        actions.add(new SharpenFilterAction(bundle.getString("SharpenFilter"), null, "Apply a Sharpen filter",
                Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new EdgeDetectionFiltersAction(bundle.getString("EdgeFilters"), null, "Apply a Sharpen filter",
                Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new BlurFiltersAction(bundle.getString("BlurFilters"), null, "Apply a Sharpen filter",
                Integer.valueOf(KeyEvent.VK_M)));
    }

    public Action getAction(int pos) {
        return actions.get(pos);
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

        class MyActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("action");
            }
        }
        MyActionListener actionListener = new MyActionListener();

        JMenu fileMenu = new JMenu(bundle.getString("Filter"));

        for (Action action : actions) {
            JMenuItem jmi = new JMenuItem(action);
            jmi.addActionListener(actionListener);
            fileMenu.add(jmi);
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
     * Show a popup will all edge detection filters
     * </p>
     * 
     */
    public class EdgeDetectionFiltersAction extends ImageAction {

        int[] operations;
        BufferedImage og;

        /**
         * <p>
         * Create a new edge-detection-filters action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        EdgeDetectionFiltersAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * This method undoes all operations that were clicked in this popup.
         * It does not show these changes to the user, as they are updated quickly after
         * when operations are applied. If not, then the image will need to be manually
         * refreshed (as done when the popup is cancelled.)
         */
        public void undoAllOperations() {
            for (int i = 0; i < operations.length; i++) {
                if (operations[i] != -1) {
                    target.getImage().undo();
                }
            }
        }

        /**
         * This method applies all operations, and updates the image to show the new
         * (filtered) image.
         */
        public void applyAllOperations() {
            for (int i = 0; i < operations.length; i++) {
                if (operations[i] != -1) {
                    if (i == 0) {
                        target.getImage().apply(new SobelFilter(operations[0] == 1));
                    } else if (i == 1) {
                        target.getImage().apply(new EmbossFilter(operations[i]));
                    }
                }
            }
            target.repaint();
            target.getParent().revalidate();
        }

        /**
         * This method is called when the popup is opened. It creates a panel which
         * contains the controls for the sobel/emboss filters. When the cancel button is
         * pressed all changes made inside of the popup are undone.
         */
        public void actionPerformed(ActionEvent e) {
            operations = new int[] { -1, -1 };
            og = target.getImage().getCurrentImage();
            JPanel optionsMenu = new JPanel();
            optionsMenu.setLayout(new GridLayout(2, 2));
            optionsMenu.add(new JLabel("Sobel Filter"));
            optionsMenu.add(getSobelPanel());
            optionsMenu.add(new JLabel("Emboss Filter"));
            optionsMenu.add(getEmbossPanel());
            int option = JOptionPane.showOptionDialog(null, optionsMenu, "Enter filter radius (1-10)",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                undoAllOperations();
                target.repaint();
                target.getParent().revalidate();
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                return;
            }

        }

        /**
         * This function returns the JPanel which controls the sobel filter, as well as
         * handling when a button is clicked (when the filter should be applied).
         * 
         * @return A JPanel, containing the controls for the sobel filter.
         */
        public JPanel getSobelPanel() {
            JPanel buttonPanel = new JPanel();
            String[] directions = { "← →", "↑ ↓" };

            ActionListener actionListener = new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    undoAllOperations();
                    operations[0] = java.util.Arrays.asList(directions).indexOf(actionEvent.getActionCommand());
                    applyAllOperations();
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
            return buttonPanel;
        }

        /**
         * This function returns a JPanel which contains the controls for the emboss
         * filter. It also handles when one of the buttons is clicked (applying the
         * filter).
         * 
         * @return a JPanel containing the controls the the emboss filter.
         */
        public JPanel getEmbossPanel() {
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(3, 3));
            String[] directions = { "↖", "↑", "↗", "←", "", "→", "↙", "↓", "↘" };

            ActionListener actionListener = new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    undoAllOperations();
                    operations[1] = java.util.Arrays.asList(directions).indexOf(actionEvent.getActionCommand());
                    applyAllOperations();
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
            return buttonPanel;
        }
    }

    /**
     * <p>
     * Show a popup will all blurring filters
     * </p>
     * 
     */
    public class BlurFiltersAction extends ImageAction {

        int[] operations;

        /**
         * <p>
         * Create a new blur-filters action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        BlurFiltersAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * This method undoes all operations that were clicked in this popup.
         * It does not show these changes to the user, as they are updated quickly after
         * when operations are applied. If not, then the image will need to be manually
         * refreshed (as done when the popup is cancelled.)
         */
        public void undoAllOperations() {
            for (int i = 0; i < operations.length; i++) {
                if (operations[i] != 0) {
                    target.getImage().undo();
                }
            }
        }

        /**
         * This method applies all operations, and updates the image to show the new
         * (filtered) image.
         */
        public void applyAllOperations() {
            for (int i = 0; i < operations.length; i++) {
                if (operations[i] != 0) {
                    if (i == 0) {
                        target.getImage().apply(new MeanFilter(operations[0]));
                    } else if (i == 1) {
                        target.getImage().apply(new GaussianBlur(operations[1]));
                    } else if (i == 2) {
                        target.getImage().apply(new MedianFilter(operations[2]));
                    }
                }
            }
            target.repaint();
            target.getParent().revalidate();
        }

        /**
         * This method is called when the popup is opened. It creates a panel which
         * contains the controls for the filters. When the cancel button is
         * pressed all changes made inside of the popup are undone.
         */
        public void actionPerformed(ActionEvent e) {
            operations = new int[3];
            JPanel optionsMenu = new JPanel();
            optionsMenu.setLayout(new GridLayout(3, 2));
            optionsMenu.add(new JLabel("Mean Blur"));
            optionsMenu.add(getMeanBlurPanel());
            optionsMenu.add(new JLabel("Gaussian Blur"));
            optionsMenu.add(getGaussianBlurPanel());
            optionsMenu.add(new JLabel("Median Blur"));
            optionsMenu.add(getMedianBlurPanel());
            int option = JOptionPane.showOptionDialog(null, optionsMenu, "Enter filter radius (1-10)",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                undoAllOperations();
                target.repaint();
                target.getParent().revalidate();
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                return;
            }

        }

        /**
         * This method creates the controls for the gaussian blur panel. This includes a
         * JPanel which contains a spinner, which updates the gaussian blur when it is
         * changed.
         * 
         * @return JPanel containing a spinner, which changes the blur value for the
         *         gaussian blur
         */
        public JPanel getGaussianBlurPanel() {
            JPanel panel = new JPanel();
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(0, 0, 10, 1);
            JSpinner radius = new JSpinner(radiusModel);
            radius.setEditor(new JSpinner.DefaultEditor(radius));
            panel.add(radius);

            radius.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    JSpinner source = (JSpinner) e.getSource();
                    int rad = (int) source.getValue();
                    undoAllOperations();
                    operations[1] = rad;
                    applyAllOperations();
                }
            });

            return panel;
        }

        /**
         * This method creates the controls for the mean blur panel. This includes a
         * JPanel which contains a spinner, which updates the mean blur when it is
         * changed.
         * 
         * @return JPanel containing a spinner, which changes the blur value for the
         *         mean blur
         */
        public JPanel getMeanBlurPanel() {
            JPanel panel = new JPanel();
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(0, 0, 10, 1);
            JSpinner radius = new JSpinner(radiusModel);
            radius.setEditor(new JSpinner.DefaultEditor(radius));
            panel.add(radius);

            radius.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    JSpinner source = (JSpinner) e.getSource();
                    int rad = (int) source.getValue();
                    undoAllOperations();
                    operations[0] = rad;
                    applyAllOperations();
                }
            });

            return panel;
        }

        /**
         * This method creates the controls for the median blur panel. This includes a
         * JPanel which contains a spinner, which updates the median blur when it is
         * changed.
         * 
         * @return JPanel containing a spinner, which changes the blur value for the
         *         median blur
         */
        public JPanel getMedianBlurPanel() {
            JPanel panel = new JPanel();
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(0, 0, 10, 1);
            JSpinner radius = new JSpinner(radiusModel);
            radius.setEditor(new JSpinner.DefaultEditor(radius));
            panel.add(radius);

            radius.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    JSpinner source = (JSpinner) e.getSource();
                    int rad = (int) source.getValue();
                    undoAllOperations();
                    operations[2] = rad;
                    applyAllOperations();
                }
            });

            return panel;
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
            EmbossFilterPopup();
        }

        /**
         * <p>
         * Ask the user for a direction for the emboss filter,
         * and apply the filter to the image in real time.
         * </p>
         */
        public void EmbossFilterPopup() {
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
                            new EmbossFilter(
                                    java.util.Arrays.asList(directions).indexOf(actionEvent.getActionCommand())));
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

        /**
         * <p>
         * Ask the user for an orientation for the Sobel filter,
         * and apply the filter to the image in real time.
         * </p>
         */
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

            int option = JOptionPane.showOptionDialog(null, buttonPanel, "Choose Orientation of Sobel Filter",
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
