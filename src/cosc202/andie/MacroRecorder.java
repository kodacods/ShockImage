package cosc202.andie;



import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MacroRecorder {
    private static List<ActionEvent> events= new ArrayList<>();

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
    

}
