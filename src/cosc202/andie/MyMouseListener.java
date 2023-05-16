package cosc202.andie;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouseListener implements MouseListener {

        private static boolean isRecording = false;



        @Override
        public void mouseClicked(MouseEvent e) {
            // Implement your code here
            System.out.println("click");
        }
    
        @Override
        public void mousePressed(MouseEvent e) {
            // Implement your code here
            System.out.println("press");
        }
    
        @Override
        public void mouseReleased(MouseEvent e) {
            // Implement your code here
            System.out.println("release");
        }
    
        @Override
        public void mouseEntered(MouseEvent e) {
            // Implement your code here
        }
    
        @Override
        public void mouseExited(MouseEvent e) {
            // Implement your code here
        }

        public static void setIsRecording(Boolean record){
            isRecording = record;
        }
    
        public static boolean getIsRecording(){
            return isRecording;
        }
    
}
