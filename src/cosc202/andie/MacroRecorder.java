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



public class MacroRecorder implements Serializable {
    private static List<String> commands= new ArrayList<>();
    private static Map<String, Runnable> actionMap = new HashMap<>();

    public static void addActionMapping(String actionCommand, Runnable action) {
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
        System.out.println(actionMap);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName + ".ops"))) {
            out.writeObject(commands);
            out.close();
            System.out.println("Macro saved to " + fileName + ".");
        } catch (IOException e) {
            System.err.println("Error saving to file");
        }
    }

    
    @SuppressWarnings("unchecked")
    public static void replayFromFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException, AWTException {
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
                System.out.println("done");
            }
        
            
            
    }

    
}
