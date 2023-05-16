package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.imageio.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.*;

public class PuzzleGameAction implements ActionListener{
    private static ArrayList<JButton> buttons = new ArrayList<JButton>();
    private static JLabel status = new JLabel("Selected piece: " + "Not selected");
    private static boolean isSelected = false;
    private static JLabel showIsSelected = new JLabel("Not swapping");
    private static JButton currentButton;

    public PuzzleGameAction(){}

    public void createAndShowGame(int pieces, EditableImage input){ 
        JFrame frame = new JFrame("Puzzle Game");
        frame.setLayout(new GridLayout());

        BufferedImage usedImage = input.getCurrentImage();
        int ogImageWidth = usedImage.getWidth();
        int ogImageHeight = usedImage.getHeight();

        JLabel fullImage = new JLabel();
        fullImage.setMaximumSize(new Dimension(400, 320));

        int factor = ogImageWidth / 400;
        int newWidth = ogImageWidth / factor;
        int newHeight = ogImageHeight / factor;

        Image out = usedImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage useThis = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_4BYTE_ABGR); //oh. yeha there we go
        Graphics2D lay = useThis.createGraphics();
        lay.drawImage(out, 0, 0, null);
        lay.dispose();

        fullImage.setIcon(new ImageIcon(useThis));

        JPanel fullImageSide = new JPanel();
        fullImageSide.setLayout(new GridBagLayout());
        fullImageSide.setBorder(new EmptyBorder(20, 10, 20, 10));
        fullImageSide.add(fullImage);

        JPanel puzzleSide = new JPanel();
        fullImageSide.setLayout(new GridBagLayout());
        puzzleSide.setBorder(new EmptyBorder(20, 10, 20, 10));

        JPanel puzzle = new JPanel();
        puzzle.setLayout(new GridLayout(pieces, pieces));
        Random r = new Random();
        
        
        if (pieces <= 2){
            int sectionWidth = useThis.getWidth() / 2;
            int sectionHeight = useThis.getHeight() / 2;

            JButton p1 = new JButton(); JButton p2 = new JButton();
            JButton p3 = new JButton(); JButton p4 = new JButton();
            p1.addActionListener(this); p2.addActionListener(this); 
            p3.addActionListener(this); p4.addActionListener(this);

            Collections.addAll(buttons, p1, p2, 
                                        p3, p4);

            BufferedImage section1 = useThis.getSubimage(0, 0, sectionWidth, sectionHeight);
            BufferedImage section2 = useThis.getSubimage(sectionWidth, 0, sectionWidth, sectionHeight);
            BufferedImage section3 = useThis.getSubimage(0, sectionHeight, sectionWidth, sectionHeight);
            BufferedImage section4 = useThis.getSubimage(sectionWidth, sectionHeight, sectionWidth, sectionHeight);
            //shuffle image sections
            BufferedImage[] selection = {section1, section2, 
                                         section3, section4};
            for(int i = 0; i < selection.length; i++){
                int elem = r.nextInt(selection.length);
                BufferedImage temp = selection[i];
                selection[i] = selection[elem];
                selection[elem] = temp;
            }

            p1.setIcon(new ImageIcon(selection[0])); p2.setIcon(new ImageIcon(selection[1]));
            p3.setIcon(new ImageIcon(selection[2])); p4.setIcon(new ImageIcon(selection[3]));

            puzzle.add(buttons.get(0)); puzzle.add(buttons.get(1)); 
            puzzle.add(buttons.get(2)); puzzle.add(buttons.get(3));
            puzzleSide.add(puzzle);
        } else if (pieces == 3){
            int sectionWidth = useThis.getWidth() / 3;
            int sectionHeight = useThis.getHeight() / 3;

            JButton p1 = new JButton(); JButton p2 = new JButton(); JButton p3 = new JButton(); 
            JButton p4 = new JButton(); JButton p5 = new JButton(); JButton p6 = new JButton();
            JButton p7 = new JButton(); JButton p8 = new JButton(); JButton p9 = new JButton();

            p1.addActionListener(this); p2.addActionListener(this); p3.addActionListener(this); 
            p4.addActionListener(this); p5.addActionListener(this); p6.addActionListener(this); 
            p7.addActionListener(this); p8.addActionListener(this); p9.addActionListener(this); 

            Collections.addAll(buttons, p1, p2, p3, 
                                        p4, p5, p6, 
                                        p7, p8, p9);

            BufferedImage section1 = useThis.getSubimage(0, 0, sectionWidth, sectionHeight);
            BufferedImage section2 = useThis.getSubimage(sectionWidth, 0, sectionWidth, sectionHeight);
            BufferedImage section3 = useThis.getSubimage(sectionWidth * 2, 0, sectionWidth, sectionHeight);
            BufferedImage section4 = useThis.getSubimage(0, sectionHeight, sectionWidth, sectionHeight);
            BufferedImage section5 = useThis.getSubimage(sectionWidth, sectionHeight, sectionWidth, sectionHeight);
            BufferedImage section6 = useThis.getSubimage(sectionWidth * 2, sectionHeight, sectionWidth, sectionHeight);
            BufferedImage section7 = useThis.getSubimage(0, sectionHeight * 2, sectionWidth, sectionHeight);
            BufferedImage section8 = useThis.getSubimage(sectionWidth, sectionHeight * 2, sectionWidth, sectionHeight);
            BufferedImage section9 = useThis.getSubimage(sectionWidth * 2, sectionHeight * 2, sectionWidth, sectionHeight);

            BufferedImage[] selection = {section1, section2, section3, 
                                         section4, section5, section6,
                                         section7, section8, section9};
            for(int i = 0; i < selection.length; i++){
                int elem = r.nextInt(selection.length);
                BufferedImage temp = selection[i];
                selection[i] = selection[elem];
                selection[elem] = temp;
            }

            p1.setIcon(new ImageIcon(selection[0])); p2.setIcon(new ImageIcon(selection[1])); p3.setIcon(new ImageIcon(selection[2])); 
            p4.setIcon(new ImageIcon(selection[3])); p5.setIcon(new ImageIcon(selection[4])); p6.setIcon(new ImageIcon(selection[5]));
            p7.setIcon(new ImageIcon(selection[6])); p8.setIcon(new ImageIcon(selection[7])); p9.setIcon(new ImageIcon(selection[8]));

            puzzle.add(buttons.get(0)); puzzle.add(buttons.get(1)); puzzle.add(buttons.get(2)); 
            puzzle.add(buttons.get(3)); puzzle.add(buttons.get(4)); puzzle.add(buttons.get(5)); 
            puzzle.add(buttons.get(6)); puzzle.add(buttons.get(7)); puzzle.add(buttons.get(8));
            puzzleSide.add(puzzle);
        } else if (pieces >= 4){ //yeah i wish i knew how to make this faster.
            int sectionWidth = useThis.getWidth() / 4;
            int sectionHeight = useThis.getHeight() / 4;

            JButton p1 = new JButton(); JButton p2 = new JButton(); JButton p3 = new JButton(); JButton p4 = new JButton(); 
            JButton p5 = new JButton(); JButton p6 = new JButton(); JButton p7 = new JButton(); JButton p8 = new JButton(); 
            JButton p9 = new JButton(); JButton p10 = new JButton(); JButton p11 = new JButton(); JButton p12 = new JButton();
            JButton p13 = new JButton(); JButton p14 = new JButton(); JButton p15 = new JButton(); JButton p16 = new JButton();

            p1.addActionListener(this); p2.addActionListener(this); p3.addActionListener(this); p4.addActionListener(this);
            p5.addActionListener(this); p6.addActionListener(this); p7.addActionListener(this); p8.addActionListener(this);
            p9.addActionListener(this); p10.addActionListener(this); p11.addActionListener(this); p12.addActionListener(this);
            p13.addActionListener(this); p14.addActionListener(this); p15.addActionListener(this); p16.addActionListener(this);

            Collections.addAll(buttons, p1, p2, p3, p4,
                                        p5, p6, p7, p8,
                                        p9, p10, p11, p12,
                                        p13, p14, p15, p16);

            BufferedImage section1 = useThis.getSubimage(0, 0, sectionWidth, sectionHeight);
            BufferedImage section2 = useThis.getSubimage(sectionWidth, 0, sectionWidth, sectionHeight);
            BufferedImage section3 = useThis.getSubimage(sectionWidth * 2, 0, sectionWidth, sectionHeight);
            BufferedImage section4 = useThis.getSubimage(sectionWidth * 3, 0, sectionWidth, sectionHeight);
            BufferedImage section5 = useThis.getSubimage(0, sectionHeight, sectionWidth, sectionHeight);
            BufferedImage section6 = useThis.getSubimage(sectionWidth, sectionHeight, sectionWidth, sectionHeight);
            BufferedImage section7 = useThis.getSubimage(sectionWidth * 2, sectionHeight, sectionWidth, sectionHeight);
            BufferedImage section8 = useThis.getSubimage(sectionWidth * 3, sectionHeight, sectionWidth, sectionHeight);
            BufferedImage section9 = useThis.getSubimage(0, sectionHeight * 2, sectionWidth, sectionHeight);
            BufferedImage section10 = useThis.getSubimage(sectionWidth, sectionHeight * 2, sectionWidth, sectionHeight);
            BufferedImage section11 = useThis.getSubimage(sectionWidth * 2, sectionHeight * 2, sectionWidth, sectionHeight);
            BufferedImage section12 = useThis.getSubimage(sectionWidth * 3, sectionHeight * 2, sectionWidth, sectionHeight);
            BufferedImage section13 = useThis.getSubimage(0, sectionHeight * 3, sectionWidth, sectionHeight);
            BufferedImage section14 = useThis.getSubimage(sectionWidth, sectionHeight * 3, sectionWidth, sectionHeight);
            BufferedImage section15 = useThis.getSubimage(sectionWidth * 2, sectionHeight * 3, sectionWidth, sectionHeight);
            BufferedImage section16 = useThis.getSubimage(sectionWidth * 3, sectionHeight * 3, sectionWidth, sectionHeight);

            BufferedImage[] selection = {section1, section2, section3, section4, 
                                         section5, section6, section7, section8, 
                                         section9, section10, section11, section12,
                                         section13, section14, section15, section16};
            for(int i = 0; i < selection.length; i++){
                int elem = r.nextInt(selection.length);
                BufferedImage temp = selection[i];
                selection[i] = selection[elem];
                selection[elem] = temp;
            }
            p1.setIcon(new ImageIcon(selection[0])); p2.setIcon(new ImageIcon(selection[1])); p3.setIcon(new ImageIcon(selection[2])); p4.setIcon(new ImageIcon(selection[3])); 
            p5.setIcon(new ImageIcon(selection[4])); p6.setIcon(new ImageIcon(selection[5])); p7.setIcon(new ImageIcon(selection[6])); p8.setIcon(new ImageIcon(selection[7])); 
            p9.setIcon(new ImageIcon(selection[8])); p10.setIcon(new ImageIcon(selection[9])); p11.setIcon(new ImageIcon(selection[10])); p12.setIcon(new ImageIcon(selection[11]));
            p13.setIcon(new ImageIcon(selection[12])); p14.setIcon(new ImageIcon(selection[13])); p15.setIcon(new ImageIcon(selection[14])); p16.setIcon(new ImageIcon(selection[15]));

            puzzle.add(buttons.get(0)); puzzle.add(buttons.get(1)); puzzle.add(buttons.get(2)); puzzle.add(buttons.get(3)); 
            puzzle.add(buttons.get(4)); puzzle.add(buttons.get(5)); puzzle.add(buttons.get(6)); puzzle.add(buttons.get(7)); 
            puzzle.add(buttons.get(8)); puzzle.add(buttons.get(9)); puzzle.add(buttons.get(10)); puzzle.add(buttons.get(11)); 
            puzzle.add(buttons.get(12)); puzzle.add(buttons.get(13)); puzzle.add(buttons.get(14)); puzzle.add(buttons.get(15)); 
            puzzleSide.add(puzzle);
        } 
        
        
        puzzleSide.add(status);
        puzzleSide.add(showIsSelected);
        

        //swapping icons of buttons...

        /*  Process:
         * Upon clicking a button, 
         * save the subimage of the button that was clicked.
         * Show which button was clicked.
         * Upon clicking another,
         * swap the image.
         * Clear the selected image out of current selection, as well as label.
         * 
         */

        frame.add(fullImageSide);
        frame.add(puzzleSide);
        frame.pack();
        frame.setVisible(true);
        
    }

    public void actionPerformed(ActionEvent e){
        JButton selected = (JButton)e.getSource();
        int num = buttons.indexOf(selected);
        status.setText("Selected piece: " + num);
        Icon currentSection = selected.getIcon();
        status.setIcon(currentSection);
        if(isSelected == false){
            currentButton = selected;
            isSelected = true;
            showIsSelected.setText("Currently swapping");
        } else {
            Icon previousIcon = currentButton.getIcon();
            currentButton.setIcon(currentSection);
            selected.setIcon(previousIcon);
            isSelected = false;
            showIsSelected.setText("Not swapping");
        }
    }
}
