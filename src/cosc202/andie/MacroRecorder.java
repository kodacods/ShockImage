package cosc202.andie;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JMenuItem;

public class MacroRecorder implements Serializable {
    private static List<String> commands= new ArrayList<>();
    private static Map<String, String> actionMap = new HashMap<>();

    public static void addActionMapping(String actionCommand, String action) {
        actionMap.put(actionCommand, action);
    }

    public static void startRecording() {
        System.out.println(actionMap);
        if (commands!=null){
            commands.clear();
            }
        if (MyActionListener.getIsRecording()==false)
        MyActionListener.setIsRecording(true);
            System.out.println(MyActionListener.getIsRecording());
    }

    public static void stopRecording() {
        if (MyActionListener.getIsRecording()==true)
        MyActionListener.setIsRecording(false);
        System.out.println(MyActionListener.getIsRecording());
    }
     
    public static void addEvent(ActionEvent e) {
        commands.add(e.getActionCommand());
    }
    
    public static void saveToFile(String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName + ".ops"))) {
            out.writeObject(commands);
            out.close();
            System.out.println("Macro saved to " + fileName + ".");
        } catch (IOException e) {
            System.err.println("Error saving to file");
        }
    }

    /*
    @SuppressWarnings("unchecked")
    public static void replayFromFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException, AWTException {
        List<ActionEvent> readEvents = new ArrayList<>();

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName + ".ops"))) {
            readEvents = (List<ActionEvent>)input.readObject();
        } catch (IOException e){
             System.err.println("Error reading file");
        } catch (ClassNotFoundException cnfe){
            System.err.println("Class not found");
        }
        
        System.out.println(readEvents.toString());

            Robot bot = new Robot();
            for (ActionEvent event : readEvents) {

                System.out.println(txt + "\n" + mod + "\n" + source + "\n" + event.paramString());
                
                Object source = event.getSource();
                System.out.println("test");
                if(source instanceof JMenuItem){
                    System.out.println("hi");
                    JMenuItem menuItem = (JMenuItem)source;
                    System.out.println(menuItem.toString());
                    int actionCommand = menuItem.getMnemonic();
                    bot.keyPress(actionCommand);
                    bot.keyRelease(actionCommand);
                }
                

            }
        
            
            
    }

    */
    
}
