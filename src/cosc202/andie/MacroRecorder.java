package cosc202.andie;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MacroRecorder {
    private Robot robot;
    private List<KeyEvent> keyboard;
    private List<MouseEvent> mousepad;
    private Boolean isRecording;
    
    public MacroRecorder() throws AWTException {
        robot = new Robot();
        keyboard = new ArrayList<>();
        mousepad = new ArrayList<>();
    }

    public void startRecording(){
        isRecording = true;
        keyboard.clear();
        mousepad.clear();
    }

    public void stopRecording(){
        isRecording = false;
    }

    public void keyRecord (KeyEvent action){
        if (isRecording == true){
            keyboard.add(action);
        }
    }

    public void mousepadRecord (MouseEvent action){
        if (isRecording == true){
            mousepad.add(action);;;
        }
    }

    public void saveMacros (String filename) throws IOException{
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.close();
    }
}
