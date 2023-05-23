package cosc202.andie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>
 * An action listener class that 
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Beka Rolleston
 * @version 1.0
 */
public class MyActionListener implements ActionListener {
    private static boolean isRecording = false;
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isRecording==true)
        System.out.println("action");
        MacroRecorder.addEvent(e);
    }

    /**
     * @param isRecording
     */
    public static void setIsRecording(boolean isRecording){
        MyActionListener.isRecording = isRecording;
    }

    public static boolean getIsRecording(){
        return isRecording;
    }
}