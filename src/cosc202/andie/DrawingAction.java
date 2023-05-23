
package cosc202.andie;

import javax.swing.*;
import java.awt.Color;
import javax.swing.JColorChooser;

import java.awt.event.ActionEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

/**
 * 
 * <p>
 * This class extends ImageAction to allow for drawing of shapes on the image.
 * </p>
 * 
 * <p>
 * This class creates a drawing menu with options to draw circles, rectangles,
 * and lines as well as fill and colour.
 * </p>
 * 
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Stella Yan
 * @version 1.0
 */

public class DrawingAction extends ImageAction {

    private ImagePanel imagePanel;
    private Color currentColor = Color.RED; // Default color is set to red

    private boolean fillShape = false;

    /**
     * <p>
     * Create a new drawing action.
     * </p>
     * 
     */
    DrawingAction(String name, ImageIcon icon, String desc, Integer mnemonic, ImagePanel imagePanel) {
        super(name, icon, desc, mnemonic);
        this.imagePanel = imagePanel;
    }

    /**
     * <p>
     * Create a new menu using Jmenu for shapes,fill and color using action event.
     * </p>
     * 
     * 
     * 
     * 
     * @param e the ActionEvent that triggered this method
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem = (JMenuItem) e.getSource();
        String shapeType = menuItem.getText();

        switch (shapeType) {
            case "Circle":
                imagePanel.setCurrentShapeType(ImagePanel.ShapeType.CIRCLE);
                imagePanel.setShapeType(ImagePanel.ShapeType.CIRCLE);
                ImagePanel.selection = false;
                imagePanel.setShapeFilled(fillShape);
                break;

            case "Rectangle":
                imagePanel.setCurrentShapeType(ImagePanel.ShapeType.RECTANGLE);
                imagePanel.setShapeType(ImagePanel.ShapeType.RECTANGLE);
                ImagePanel.selection = false;
                imagePanel.setShapeFilled(fillShape);
                break;

            case "Line":
                imagePanel.setCurrentShapeType(ImagePanel.ShapeType.LINE);
                imagePanel.setShapeType(ImagePanel.ShapeType.LINE);
                ImagePanel.selection = false;
                imagePanel.setShapeFilled(fillShape);

                break;

            case "Colour":
                Color selectedColor = JColorChooser.showDialog(imagePanel, " Colour", currentColor);
                if (selectedColor != null) {
                    currentColor = selectedColor;
                    imagePanel.setCurrentColor(currentColor);
                    imagePanel.repaint();

                }
                break;

            case "Fill Shape":
                fillShape = !fillShape; // Toggle the fill shape flag

                menuItem.setSelected(fillShape);
                imagePanel.setShapeFilled(fillShape);
                imagePanel.repaint();
                break;

        }
    }

    /**
     * <p>
     * Creates menu items to add to the menu using action listener.
     * </p>
     * 
     * 
     * 
     * 
     */
    public JMenu createDrawingMenu() {
        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        ResourceBundle bundle = ResourceBundle.getBundle("TMessageBundle");

        JMenu drawingMenu = new JMenu(bundle.getString("Drawing"));
        String[] shapeTypes = { "Circle", "Rectangle", "Line" };

        for (String shapeType : shapeTypes) {
            JMenuItem shapeMenuItem = new JMenuItem(shapeType);
            shapeMenuItem.addActionListener(this);
            drawingMenu.add(shapeMenuItem);
        }

        JMenuItem colorMenuItem = new JMenuItem("Colour");
        colorMenuItem.addActionListener(this);
        drawingMenu.addSeparator();
        drawingMenu.add(colorMenuItem);

        JCheckBoxMenuItem fillMenuItem = new JCheckBoxMenuItem("Fill Shape");
        fillMenuItem.addActionListener(this);
        drawingMenu.add(fillMenuItem);

        return drawingMenu;
    }

    public void setCurrentColor(Color colour) {
        currentColor = colour;
    }
}