package cosc202.andie;

import java.util.*;
import java.awt.AWTException;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import java.util.prefs.Preferences;

/**
 * <p>
 * Actions for Macros
 * </p>
 * 
 * <p>
 * The Macros menu allows the user to record, save and replay macros on any image
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Beka Rolleston
 * @version 1.0
 */

public class MacrosAction {
    /** A list of actions for the Language menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Macro menu actions.
     * </p>
     */
    public MacrosAction() throws AWTException {

        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        ResourceBundle bundle = ResourceBundle.getBundle("TMessageBundle");

        actions = new ArrayList<Action>();
        actions.add(new StartRecordingAction(bundle.getString("RecordEdits"), null, "Records macros of editing", null));
        actions.add(new StopRecordingAction(bundle.getString("StopRecording"), null, "Stops recording macros", null ));
        //actions.add(new SaveMacrosAction(bundle.getString("SaveEditRecording"), null, "Save a copy", null);
        actions.add(new ReplayAction(bundle.getString("ReplayEditRecording"), null, "Export Image", null));
    
    }

    /**
     * Returns a specific action from the actions ArrayList given its index
     * @param pos
     * @return
     */
    public Action getAction (int pos){
        return actions.get(pos);
    }

    /**
     * <p>
     * Create a menu contianing the list of macro actions.
     * </p>
     * 
     * @return The File menu UI element.
     */    
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
    
    /**
     * Class that begins the recording of Macros
     */
    public class StartRecordingAction extends ImageAction{
        
        StartRecordingAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * Calls the startRecording method in MacroRecorder when an event has taken place
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            MacroRecorder.startRecording();
        }
    }

    /**
     * Class that stops the recording of Macros and saves it to a file
     */
    public class StopRecordingAction extends ImageAction{
        
        StopRecordingAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            MacroRecorder.stopRecording();
            MacroRecorder.saveToFile("macro2");

        }
    }
    
    /**
     * Class that replays macros saved from a file onto an image
     */
    public class ReplayAction extends ImageAction{
        
        ReplayAction (String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                MacroRecorder.replayFromFile("macro2");
            } 
            catch (AWTException | ClassNotFoundException | IOException ex){
                System.err.println("Error reading file");
            } catch (InterruptedException e1) {
                System.out.println("Interupted");
            }

        }
    }
    

}
