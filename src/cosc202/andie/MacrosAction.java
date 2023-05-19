package cosc202.andie;

import java.util.*;
import java.awt.AWTException;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import java.util.prefs.Preferences;

public class MacrosAction {

    protected ArrayList<Action> actions;

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
    
    public class StartRecordingAction extends ImageAction{
        
        StartRecordingAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            MacroRecorder.startRecording();
        }
    }

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
            }

        }
    }

}
