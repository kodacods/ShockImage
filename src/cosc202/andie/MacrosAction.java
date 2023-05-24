package cosc202.andie;

import java.util.*;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;
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
     * @throws IOException
     */
    public MacrosAction() throws AWTException, IOException {

        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        ResourceBundle bundle = ResourceBundle.getBundle("TMessageBundle");

        // <a href="https://www.flaticon.com/free-icons/dot" title="dot icons">Dot icons created by Google - Flaticon</a>
        Image recordImage = ImageIO.read(Andie.class.getClassLoader().getResource("button.png"));
        ImageIcon recordIcon = new ImageIcon(recordImage);

        // <a href="https://www.flaticon.com/free-icons/stop-button" title="stop button icons">Stop button icons created by Pixel perfect - Flaticon</a>
        Image stopRecordImage = ImageIO.read(Andie.class.getClassLoader().getResource("stop-button.png"));
        ImageIcon stopRecordIcon = new ImageIcon(stopRecordImage);

        actions = new ArrayList<Action>();
        actions.add(new StartRecordingAction(bundle.getString("RecordEdits"), recordIcon, "Records macros of editing", null));
        actions.add(new StopRecordingAction(bundle.getString("StopRecording"), stopRecordIcon, "Stops recording macros", null ));
        actions.add(new SaveMacrosAction(bundle.getString("SaveEditRecording"), null, "Save a copy", null));
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
         * Sets recording in EditableImage to true.
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            EditableImage.setRecording(true);
        }
    }

    /**
     * Class the stops the recording of macros
     */
    public class StopRecordingAction extends ImageAction{

        StopRecordingAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * Sets recording in EditableImage to false.
         * @param e
         */
        public void actionPerformed(ActionEvent e){
            EditableImage.setRecording(false);
        }
    }

    /**
     * Class that saves macros recorded in List to a file
     */
    public class SaveMacrosAction extends ImageAction{
        
        SaveMacrosAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         *  Allows the user to select the filename and path to save .op file.
         *  Passes onto saveMacrosToFile methos in EditableImage class
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
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

        /**
         * 
         * Allows the user to select a .ops file 
         * Loads the .ops file and executes operations to an image
         * 
         * @param e
         */
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

                } catch (ClassNotFoundException cnf) {
                    System.err.println("Class not found");
                } catch (IOException ioe){
                    System.err.println(ioe);
                }
                    
            }

                // Applies operations to the image
                for (ImageOperation event : readEvents){
                    System.out.println(event);
                    target.getImage().apply(event);
                    target.repaint();
                    target.getParent().revalidate();
                }
                
            }
        }
    
    
}
