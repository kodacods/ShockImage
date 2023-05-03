package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.prefs.Preferences;

public class MacrosAction {

    protected ArrayList<Action> actions;

    public MacrosAction() {

    Preferences prefs = Preferences.userNodeForPackage(Andie.class);
    Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
    ResourceBundle bundle = ResourceBundle.getBundle("TMessageBundle");

        actions = new ArrayList<Action>();
        actions.add(new StartRecordingAction(bundle.getString("Record Edits"), null, "Open a file", Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new StopRecordingAction(bundle.getString("Stop Recording"), null, "Save the file", Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new SaveMacrosAction(bundle.getString("Save Edit Recording"), null, "Save a copy", Integer.valueOf(KeyEvent.VK_A)));
        actions.add(new ReplayAction(bundle.getString("Replay Edit Recording"), null, "Export Image", Integer.valueOf(KeyEvent.VK_A)));
    
    }

    public Action getAction (int pos){
        return actions.get(pos);
    }

    public JMenu createMenu() {

        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        ResourceBundle bundle = ResourceBundle.getBundle("TMessageBundle");

        JMenu macrosMenu = new JMenu(bundle.getString("Macros"));

        for(Action action: actions) {
            macrosMenu.add(new JMenuItem(action));
        }

        return macrosMenu;
    }
    
}
