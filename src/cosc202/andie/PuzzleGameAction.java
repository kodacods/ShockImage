package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.imageio.*;

public class PuzzleGameAction {
    public static void createAndShowGame(int pieces, BufferedImage input){
        JFrame frame = new JFrame("Puzzle Game");
        frame.pack();
        frame.setVisible(true);
    }
}
