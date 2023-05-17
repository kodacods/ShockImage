package cosc202.andie;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.Action;
import javax.swing.JMenuItem;

public class MacroRecorder implements Serializable {
    private static List<ActionEvent> events= new ArrayList<>();

    public static void startRecording() {
        if (events!=null){
            events.clear();
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
        events.add(e);
    }

    public static void saveToFile(String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName + ".ops"))) {
            out.writeObject(events);
            out.close();
            System.out.println("Macro saved to " + fileName + ".");
        } catch (IOException e) {
            System.err.println("Error saving to file");
        }
    }
    
    @SuppressWarnings("unchecked")
    public static void replayFromFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        List<ActionEvent> readEvents = new ArrayList<>();

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName + ".ops"))) {
            readEvents = (List<ActionEvent>)input.readObject();
        } catch (IOException e){
             System.err.println("Error reading file");
        } catch (ClassNotFoundException cnfe){
            System.err.println("Class not found");
        }
        
        System.out.println(readEvents);
        
             
            for (ActionEvent event : events) {
                        Object source = event.getSource();
                        if (source instanceof JMenuItem) {
                            JMenuItem menuItem = (JMenuItem) source;
                            String actionCommand = menuItem.getActionCommand();
                            if (actionCommand.equals("Exit")) {
                                System.exit(0);
                            } else {
                                innit();
                            }
                            System.out.println("Macro replayed from " + fileName + ".");
                        }
            }
            */
    }
    
}
