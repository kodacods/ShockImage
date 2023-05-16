package cosc202.andie;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.Action;
import javax.swing.JMenuItem;

public class MacroRecorder {
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
            System.out.println("Macro saved to " + fileName + ".");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void replayFromFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName + ".ops");
        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            int s = ois.read();
            System.out.println(s);
        }
        
        
        //List<ActionEvent> events = ois.read();
    
            for (ActionEvent event : events) {
                        Object source = event.getSource();
                        if (source instanceof JMenuItem) {
                            JMenuItem menuItem = (JMenuItem) source;
                            String actionCommand = menuItem.getActionCommand();
                            if (actionCommand.equals("Exit")) {
                                System.exit(0);
                            }
                            System.out.println("Macro replayed from " + fileName + ".");
                        }
            }
        }
}
