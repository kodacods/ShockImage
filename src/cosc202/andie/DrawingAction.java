

package cosc202.andie;

import javax.swing.*;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;


public class DrawingAction  extends  ImageAction {

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
        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        ResourceBundle bundle = ResourceBundle.getBundle("TMessageBundle");
        
        JMenu drawingMenu = new JMenu(bundle.getString("Drawing"));
        String[] shapeTypes = {"Circle", "Rectangle", "Square"};
        MyActionListener actionListener = new MyActionListener();

        for (String shapeType : shapeTypes) {
            JMenuItem shapeMenuItem = new JMenuItem(shapeType);
            shapeMenuItem.addActionListener(this);
            shapeMenuItem.addActionListener(actionListener);
            drawingMenu.add(shapeMenuItem);
        }


        return drawingMenu;
    }


}
