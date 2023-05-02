package cosc202.andie;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.imageio.*;
//aaaaaaaaaaaaa

/**
 * <p>
 * Main class for A Non-Destructive Image Editor (ANDIE).
 * </p>
 * STELLAS CHANGES
 * <p>
 * This class is the entry point for the program.
 * It creates a Graphical User Interface (GUI) that provides access to various
 * image editing and processing operations.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * Changed by the x-files!
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class Andie {

    /**
     * <p>
     * Launches the main GUI for the ANDIE program.
     * </p>
     * 
     * <p>
     * This method sets up an interface consisting of an active image (an
     * {@code ImagePanel})
     * and various menus which can be used to trigger operations to load, save,
     * edit, etc.
     * These operations are implemented {@link ImageOperation}s and triggerd via
     * {@code ImageAction}s grouped by their general purpose into menus.
     * </p>
     * 
     * @see ImagePanel
     * @see ImageAction
     * @see ImageOperation
     * @see FileActions
     * @see EditActions
     * @see ViewActions
     * @see FilterActions
     * @see ColourActions
     * @see TransformActions
     * 
     * @throws Exception if something goes wrong.
     */
    private static void createAndShowGUI() throws Exception {
        // Set up the main GUI frame
        JFrame frame = new JFrame("ANDIE");

        Image image = ImageIO.read(Andie.class.getClassLoader().getResource("icon.png"));

        frame.setIconImage(image);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // The main content area is an ImagePanel
        ImagePanel imagePanel = new ImagePanel();
        ImageAction.setTarget(imagePanel);
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Add in menus for various types of action the user may perform.
        JMenuBar menuBar = new JMenuBar();

        // File menus are pretty standard, so things that usually go in File menus go
        // here.
        FileActions fileActions = new FileActions();
        menuBar.add(fileActions.createMenu());

        // Likewise Edit menus are very common, so should be clear what might go here.
        EditActions editActions = new EditActions();
        menuBar.add(editActions.createMenu());

        // View actions control how the image is displayed, but do not alter its actual
        // content
        ViewActions viewActions = new ViewActions();
        menuBar.add(viewActions.createMenu());

        // Filters apply a per-pixel operation to the image, generally based on a local
        // window
        FilterActions filterActions = new FilterActions();
        menuBar.add(filterActions.createMenu());

        // Actions that affect the representation of colour in the image
        ColourActions colourActions = new ColourActions();
        menuBar.add(colourActions.createMenu());

        // Actions that concern rotation, reflection and dilation of the image
        TransformActions transformActions = new TransformActions();
        menuBar.add(transformActions.createMenu());

        LanguageAction languageAction = new LanguageAction();
        menuBar.add(languageAction.createMenu());

        // Add toolbar
        JToolBar toolBar = new JToolBar();
        toolBar.setBorder(new EtchedBorder());

        // Creates buttons and add them to tool bar
        JButton openButton = new JButton(fileActions.getAction(0));
        JButton saveButton = new JButton(fileActions.getAction(1));
        toolBar.add(openButton);
        toolBar.add(saveButton);

        JButton undoButton = new JButton(editActions.getAction(0));
        JButton redoButton = new JButton(editActions.getAction(1));
        toolBar.add(undoButton);
        toolBar.add(redoButton);

        JButton zoomInButton = new JButton(viewActions.getAction(0));
        JButton zoomOutButton = new JButton(viewActions.getAction(1));
        toolBar.add(zoomInButton);
        toolBar.add(zoomOutButton);

        JButton edgeDetectionButton = new JButton(filterActions.getAction(1));
        JButton blurButton = new JButton(filterActions.getAction(2));
        toolBar.add(edgeDetectionButton);
        toolBar.add(blurButton);

        // Selection?
        SelectionActions selectActions = new SelectionActions();
        menuBar.add(selectActions.createMenu());

        // Add the toolbar to the gui frame
        frame.add(toolBar, BorderLayout.PAGE_START);

        frame.setJMenuBar(menuBar);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * <p>
     * Main entry point to the ANDIE program.
     * </p>
     * 
     * <p>
     * Creates and launches the main GUI in a separate thread.
     * As a result, this is essentially a wrapper around {@code createAndShowGUI()}.
     * </p>
     * 
     * @param args Command line arguments, not currently used
     * @throws Exception If something goes awry
     * @see #createAndShowGUI()
     */
    public static void main(String[] args) throws Exception {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        });
    }
}
