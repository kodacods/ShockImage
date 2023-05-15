package cosc202.andie;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MacroRecorder {
    private List<MouseEvent> events;

    public MacroRecorder() {
        this.events = new ArrayList<>();
    }

    public void startRecording() {
        events.clear();
        if (Andie.getIsRecording()!=true)
        Andie.setIsRecording(true);
    }

    public void stopRecording() {
        if (Andie.getIsRecording()==true)
        Andie.setIsRecording(false);
    }

    public void addEvent(MouseEvent e) {
        events.add(e);
    }

    public void saveToFile(String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName + ".ops"))) {
            out.writeObject(events);
            System.out.println("Macro saved to " + fileName + ".");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void replayMouseEvents(String fileName) throws AWTException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            List<MouseEvent> replayEvents = (List<MouseEvent>) in.readObject();
            System.out.println("Macro loaded from " + fileName + ".");
            
            Robot robot = new Robot();
            for (MouseEvent event : replayEvents) {
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
