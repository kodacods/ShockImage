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
    private List<MacroEvent> events;
    private boolean recording;

    public MacroRecorder() {
        this.events = new ArrayList<>();
        this.recording = false;
    }

    public void startRecording() {
        if (!recording) {
            recording = true;
            events.clear();
            System.out.println("Macro recording started.");
        }
    }

    public void stopRecording() {
        if (recording) {
            recording = false;
            System.out.println("Macro recording stopped.");

            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("macro.ops"))) {
                out.writeObject(events);
                System.out.println("Macro saved to macro.ops.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isRecording() {
        return recording;
    }

    public void addEvent(MacroEvent event) {
        if (recording) {
            events.add(event);
        }
    }

    public void saveToFile(String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(events);
            System.out.println("Macro saved to " + fileName + ".");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void replayMacro(String fileName) throws AWTException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            List<MacroEvent> replayEvents = (List<MacroEvent>) in.readObject();
            System.out.println("Macro loaded from " + fileName + ".");
    
            Robot robot = new Robot();
            for (MacroEvent event : replayEvents) {
                switch (event.getEventType()) {
                    case KEY_PRESSED:
                        robot.keyPress(event.getKeyCode());
                        break;
                    case KEY_RELEASED:
                        robot.keyRelease(event.getKeyCode());
                        break;
                    case MOUSE_PRESSED:
                        robot.mousePress(event.getButton());
                        break;
                    case MOUSE_RELEASED:
                        robot.mouseRelease(event.getButton());
                        break;
                }
    
                Thread.sleep(event.getY());
            }
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
