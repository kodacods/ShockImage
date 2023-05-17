package cosc202.andie;

import java.util.*;
import java.util.prefs.Preferences;
import java.awt.event.*;
import javax.swing.*;

import java.awt.Point;

public class SelectionActions {
    protected ArrayList<Action> actions;
    /**
     * <p>
     * Create a set of Selection menu actions.
     * 
     * </p>
     */

    protected static Point selFirst, selSecond;
    protected static Point originPoint = new Point(0, 0);
    protected static int selWidth, selHeight;

    public SelectionActions() {

        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        ResourceBundle bundle = ResourceBundle.getBundle("TMessageBundle");

        actions = new ArrayList<Action>();
        actions.add(new RectangularSelectionAction(bundle.getString("SelectPortion"), null,
                "Select a rectangular portion of the image", Integer.valueOf(KeyEvent.VK_M)));
    }

    /**
     * <p>
     * Create a menu contianing the list of Selection actions.
     * </p>
     * 
     * @return The Selection menu UI element.
     */
    public JMenu createMenu() {

        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        ResourceBundle bundle = ResourceBundle.getBundle("TMessageBundle");

        JMenu selectMenu = new JMenu(bundle.getString("Selection"));

        for (Action action : actions) {
            selectMenu.add(new JMenuItem(action));
        }

        return selectMenu;
    }

    public class RectangularSelectionAction extends ImageAction implements MouseListener {

        private Point first;
        private Point second;

        RectangularSelectionAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);

        }

        public void actionPerformed(ActionEvent e) {
            target.addMouseListener(this);
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
            second = e.getPoint();
            SelectionActions.selFirst = first;
            SelectionActions.selSecond = second;
            target.removeMouseListener(this);
            openMenu();
        }

        public void mousePressed(MouseEvent e) {
            first = e.getPoint();
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
        }

        private void openMenu() {

            if (selFirst.x < selSecond.x) {
                SelectionActions.originPoint.x = selFirst.x;
                SelectionActions.selWidth = selSecond.x - selFirst.x;
            } else {
                SelectionActions.originPoint.x = selSecond.x;
                SelectionActions.selWidth = selFirst.x - selSecond.x;
            }

            if (selFirst.y < selSecond.y) {
                SelectionActions.originPoint.y = selFirst.y;
                SelectionActions.selHeight = selSecond.y - selFirst.y;
            } else {
                originPoint.y = selSecond.y;
                SelectionActions.selHeight = selFirst.y - selSecond.y;
            }

            target.getImage().apply(new RectangularShowSelection());
            target.repaint();
            target.getParent().revalidate();

            String[] options = { "Cancel", "Crop" };
            int option = JOptionPane.showOptionDialog(null, "Select functions go here.", "Selection Operations Menu",
                    JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);

            target.getImage().undo();
            target.repaint();
            target.getParent().revalidate();

            if (option == 1) {
                target.getImage().apply(new CropSelection((int) originPoint.getX(), (int) originPoint.getY(),
                        SelectionActions.selWidth, SelectionActions.selHeight));
                target.repaint();
                target.getParent().revalidate();
            }
            selFirst = new Point(0, 0);
            selSecond = new Point(0, 0);
            originPoint = new Point(0, 0);
        }

    }
}
