

package cosc202.andie;

import javax.swing.*;

import java.awt.Image;
import java.awt.event.ActionEvent;


public class DrawingAction  extends  ImageAction  {

    private ImagePanel imagePanel;

    DrawingAction(String name, ImageIcon icon, String desc, Integer mnemonic, ImagePanel imagePanel) {
        super(name, icon, desc, mnemonic);
        this.imagePanel = imagePanel;
    }





    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem = (JMenuItem) e.getSource();
        String shapeType = menuItem.getText();
        System.out.println("Shape type: " + shapeType);

        switch (shapeType) {

            case "Circle":
                imagePanel.setCurrentShapeType(ImagePanel.ShapeType.CIRCLE);

                break;
            case "Rectangle":
                imagePanel.setCurrentShapeType(ImagePanel.ShapeType.RECTANGLE);
                break;
            case "Square":
                imagePanel.setCurrentShapeType(ImagePanel.ShapeType.SQUARE);
                break;
            default:
                break;
        }
    }

    public JMenu createDrawingMenu() {
        JMenu drawingMenu = new JMenu("Drawing");
        String[] shapeTypes = {"Circle", "Rectangle", "Square"};

        for (String shapeType : shapeTypes) {
            JMenuItem shapeMenuItem = new JMenuItem(shapeType);
            shapeMenuItem.addActionListener(this);
            drawingMenu.add(shapeMenuItem);
        }

        return drawingMenu;
    }


}
