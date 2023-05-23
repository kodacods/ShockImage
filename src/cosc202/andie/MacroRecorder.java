package cosc202.andie;


import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * Contains Map of all runable methods that can occur without an event
 * Contains List of strings of action command names that are added when they are actioned by an event
 * 
 * Records the macros of events that happen while recording, saves to file and replays on any image 
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Beka Rolleston
 * @version 1.0
 */
public class MacroRecorder implements Serializable {
    private static List<String> commands= new ArrayList<>();
    private static Map<String, Runnable> actionMap = new HashMap<>();

    /**
     * Adds runnable methods to the map as the action command name as the key
     * 
     * @param actionCommand
     * @param action
     */
    public static void addActionMapping(String actionCommand, Runnable action) {
        actionMap.put(actionCommand, action);
    }

    /**
     * Sets the isRecording value in MyActionListener class to true
     */
    public static void startRecording() {
        //System.out.println(actionMap);
        if (commands!=null){
            commands.clear();
            }
        if (MyActionListener.getIsRecording()==false)
        MyActionListener.setIsRecording(true);
            System.out.println(MyActionListener.getIsRecording());
    }

    /**
     * Sets the isRecording value in MyActionListener to false
     */
    public static void stopRecording() {
        if (MyActionListener.getIsRecording()==true)
        MyActionListener.setIsRecording(false);
        System.out.println(MyActionListener.getIsRecording());
    }
     
    /**
     * Method that takes an event from MyActionListener and adds the command name to the List of commands
     * @param e
     */
    public static void addEvent(ActionEvent e) {
        commands.add(e.getActionCommand());
    }
    
    /**
     * Saves the List of commands to a .ops file
     * 
     * @param fileName
     */
    public static void saveToFile(String fileName) {
        System.out.println(actionMap);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName + ".ops"))) {
            out.writeObject(commands);
            out.close();
            System.out.println("Macro saved to " + fileName + ".");
        } catch (IOException e) {
            System.err.println("Error saving to file");
        }
    }

    /**
     * Opens the .ops file and reads the list of action commands
     * Iterates through the list, and runs the method associated to that key in actionMap
     * 
     * @param fileName
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws AWTException
     * @throws InterruptedException
     */
    @SuppressWarnings("unchecked")
    public static void replayFromFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException, AWTException, InterruptedException {
        List<String> readEvents = new ArrayList<>();

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName + ".ops"))) {
            readEvents = (List<String>)input.readObject();
        } catch (IOException e){
             System.err.println("Error reading file");
        } catch (ClassNotFoundException cnfe){
            System.err.println("Class not found");
        }
        
        System.out.println(readEvents.toString());


            for (String event : readEvents) {
                System.out.println(event);
                Runnable r = actionMap.get(event);
                System.out.println(r.toString());
                Thread thread = new Thread(r);
                thread.start();
                Thread.sleep(1000);

                System.out.println("done");
            }
        
            
            
    }

    
}
