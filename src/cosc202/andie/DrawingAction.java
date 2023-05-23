
package cosc202.andie;

import javax.swing.*;
import java.awt.Color;
import javax.swing.JColorChooser;

import java.awt.event.ActionEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class DrawingAction extends ImageAction {

    private ImagePanel imagePanel;
    private Color currentColor = Color.RED; // Default color is set to red

    private boolean fillShape = false;

    DrawingAction(String name, ImageIcon icon, String desc, Integer mnemonic, ImagePanel imagePanel) {
        super(name, icon, desc, mnemonic);
        this.imagePanel = imagePanel;
    }

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
                // fillShape = false;
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

    public JMenu createDrawingMenu() {
        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        ResourceBundle bundle = ResourceBundle.getBundle("TMessageBundle");

        JMenu drawingMenu = new JMenu(bundle.getString("Drawing"));
        String[] shapeTypes = { "Circle", "Rectangle", "Line" };
        MyActionListener actionListener = new MyActionListener();

        for (String shapeType : shapeTypes) {
            JMenuItem shapeMenuItem = new JMenuItem(shapeType);
            shapeMenuItem.addActionListener(this);
            shapeMenuItem.addActionListener(actionListener);
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