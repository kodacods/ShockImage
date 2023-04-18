package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.prefs.Preferences;


public class SelectionActions {
    protected ArrayList<Action> actions;
    /**
     * <p>
     * Create a set of Selection menu actions.
     * </p>
     */
    public SelectionActions() {

    Preferences prefs = Preferences.userNodeForPackage(Andie.class);
    Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
    ResourceBundle bundle = ResourceBundle.getBundle("TMessageBundle");

        actions = new ArrayList<Action>();
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

        for(Action action: actions) {
            selectMenu.add(new JMenuItem(action));
        }

        return selectMenu;
    }
}
