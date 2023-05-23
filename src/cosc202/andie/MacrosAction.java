package cosc202.andie;

import java.util.*;
import java.awt.AWTException;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

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
            EditableImage.setRecording(true);
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
            EditableImage.setRecording(false);
            
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                try{
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    EditableImage.saveMacrosToFile(imageFilepath);
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(null, "No image has been opened!");
                } catch (Exception ex) {
                    System.exit(1);
                }
            }
        }
    }
    
    /**
     * Class that replays macros saved from a file onto an image
     */
    public class ReplayAction extends ImageAction{
        
        ReplayAction (String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        @SuppressWarnings("unchecked")
        public void actionPerformed(ActionEvent e) {
            List<ImageOperation> readEvents = new ArrayList<>();

            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter( "Ops files", "ops");
            fileChooser.setFileFilter(filter);

            int result = fileChooser.showOpenDialog(target);

            if(result == JFileChooser.APPROVE_OPTION){
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();

                    FileInputStream fis = (new FileInputStream(imageFilepath));
                    ObjectInputStream ois = new ObjectInputStream(fis);

                    readEvents = (List<ImageOperation>)ois.readObject();
                    ois.close();

                } catch (ClassNotFoundException | IOException e1) {
                    System.err.println("error at fis/ois");
                }

                for (ImageOperation event : readEvents){
                    System.out.println(event);
                    target.getImage().apply(event);
                    target.repaint();
                    target.getParent().revalidate();
                }
                
            }
        }
    
    }
}
