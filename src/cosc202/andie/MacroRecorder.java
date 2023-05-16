package cosc202.andie;


import java.awt.AWTException;
import java.awt.Robot;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MacroRecorder {
    private static List<MacroEvent> events;

    public MacroRecorder() {
        MacroRecorder.events = new ArrayList<>();
    }

    public static void startRecording() {
        if (events!=null){
            events.clear();
            }
        if (MyMouseListener.getIsRecording()==false)
        MyMouseListener.setIsRecording(true);
            System.out.println(MyMouseListener.getIsRecording());
    }

    public static void stopRecording() {
        if (MyMouseListener.getIsRecording()==true)
        MyMouseListener.setIsRecording(false);
        System.out.println(MyMouseListener.getIsRecording());
    }

    public static void addEvent(MacroEvent e) {
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

    public static void replayMouseEvents(String fileName) throws AWTException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            List<MacroEvent> replayEvents = (List<MacroEvent>) in.readObject();
            System.out.println("Macro loaded from " + fileName + ".");
            
            Robot robot = new Robot();
            for (MacroEvent event : replayEvents) {
                robot.mouseMove(event.getX(), event.getY());
                robot.mousePress(event.getButton());
                robot.mouseRelease(event.getButton());
                Thread.sleep(50);
            }
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    

}
