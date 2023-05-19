package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.*;

public class PuzzleGameAction implements ActionListener{
    private ArrayList<JButton> buttons = new ArrayList<JButton>();
    private JLabel status = new JLabel("Selected piece: " + "Not selected");
    private boolean isSelected = false;
    private JLabel showIsSelected = new JLabel("Not swapping");
    private JLabel showMoves = new JLabel("Current moves: ");
    private JLabel showCompletion = new JLabel(); //How many section icons are in the correct position, and do something when completed
    private JButton currentButton;
    private int newWidth;
    private int newHeight;
    private ArrayList<Icon> selection = new ArrayList<Icon>();
    private ArrayList<Icon> correctArrangement = new ArrayList<Icon>();
    private int moves;
    private int completed;

    public PuzzleGameAction(){}

    public void createAndShowGame(int pieces, EditableImage input){ 
        
        JFrame frame = new JFrame("Puzzle Game");
        frame.setLayout(new GridLayout());

        BufferedImage usedImage = input.getCurrentImage();
        
        if(usedImage != null){
            int ogImageWidth = usedImage.getWidth();
            int ogImageHeight = usedImage.getHeight();

            JLabel fullImage = new JLabel();
            fullImage.setMaximumSize(new Dimension(400, 320));

            if(usedImage.getWidth() > 400){
            int factor = ogImageWidth / 400;
            newWidth = ogImageWidth / factor;
            newHeight = ogImageHeight / factor;
            } else {
                newWidth = ogImageWidth;
                newHeight = ogImageHeight;
            }


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
            puzzleSide.setLayout(new GridBagLayout());
            GridBagConstraints verticalFormat = new GridBagConstraints();
            verticalFormat.gridwidth = GridBagConstraints.REMAINDER;
            verticalFormat.fill = GridBagConstraints.HORIZONTAL;
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
                Collections.addAll(selection, new ImageIcon(section1),  new ImageIcon(section2),  
                                              new ImageIcon(section3), new ImageIcon(section4));

                correctArrangement = new ArrayList<Icon>(selection);

                for(int i = 0; i < selection.size(); i++){
                    int elem = r.nextInt(selection.size());
                    Collections.swap(selection, i, elem);
                }

                p1.setIcon(selection.get(0)); p2.setIcon(selection.get(1)); 
                p3.setIcon(selection.get(2)); p4.setIcon(selection.get(3)); 

                puzzle.add(buttons.get(0)); puzzle.add(buttons.get(1)); 
                puzzle.add(buttons.get(2)); puzzle.add(buttons.get(3));
                puzzleSide.add(puzzle, verticalFormat);
                showCompletion.setText(completed + " out of " + buttons.size());
                puzzleSide.add(showCompletion, verticalFormat);
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

                Collections.addAll(selection, new ImageIcon(section1),  new ImageIcon(section2),  new ImageIcon(section3), 
                                              new ImageIcon(section4),  new ImageIcon(section5),  new ImageIcon(section6),
                                              new ImageIcon(section7),  new ImageIcon(section8),  new ImageIcon(section9)); //Change to icons?

                correctArrangement = new ArrayList<Icon>(selection);
                
                for(int i = 0; i < selection.size(); i++){
                    int elem = r.nextInt(selection.size());
                    Collections.swap(selection, i, elem);
                }

                p1.setIcon(selection.get(0)); p2.setIcon(selection.get(1)); p3.setIcon(selection.get(2)); 
                p4.setIcon(selection.get(3)); p5.setIcon(selection.get(4)); p6.setIcon(selection.get(5));
                p7.setIcon(selection.get(6)); p8.setIcon(selection.get(7)); p9.setIcon(selection.get(8));

                puzzle.add(buttons.get(0)); puzzle.add(buttons.get(1)); puzzle.add(buttons.get(2)); 
                puzzle.add(buttons.get(3)); puzzle.add(buttons.get(4)); puzzle.add(buttons.get(5)); 
                puzzle.add(buttons.get(6)); puzzle.add(buttons.get(7)); puzzle.add(buttons.get(8));
                puzzleSide.add(puzzle, verticalFormat);
                showCompletion.setText(completed + " out of " + buttons.size());
                puzzleSide.add(showCompletion, verticalFormat);
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

                Collections.addAll(selection, new ImageIcon(section1),  new ImageIcon(section2),  new ImageIcon(section3),  new ImageIcon(section4),  
                                              new ImageIcon(section5),  new ImageIcon(section6),  new ImageIcon(section7),  new ImageIcon(section8),  
                                              new ImageIcon(section9), new ImageIcon(section10),  new ImageIcon(section11),  new ImageIcon(section12),
                                              new ImageIcon(section13), new ImageIcon(section14),  new ImageIcon(section15),  new ImageIcon(section16));

                correctArrangement = new ArrayList<Icon>(selection);

                for(int i = 0; i < selection.size(); i++){
                    int elem = r.nextInt(selection.size());
                    Collections.swap(selection, i, elem);
                }

                p1.setIcon(selection.get(0)); p2.setIcon(selection.get(1)); p3.setIcon(selection.get(2)); p4.setIcon(selection.get(3)); 
                p5.setIcon(selection.get(4)); p6.setIcon(selection.get(5)); p7.setIcon(selection.get(6)); p8.setIcon(selection.get(7)); 
                p9.setIcon(selection.get(8)); p10.setIcon(selection.get(9)); p11.setIcon(selection.get(10)); p12.setIcon(selection.get(11));
                p13.setIcon(selection.get(12)); p14.setIcon(selection.get(13)); p15.setIcon(selection.get(14)); p16.setIcon(selection.get(15));

                puzzle.add(buttons.get(0)); puzzle.add(buttons.get(1)); puzzle.add(buttons.get(2)); puzzle.add(buttons.get(3)); 
                puzzle.add(buttons.get(4)); puzzle.add(buttons.get(5)); puzzle.add(buttons.get(6)); puzzle.add(buttons.get(7)); 
                puzzle.add(buttons.get(8)); puzzle.add(buttons.get(9)); puzzle.add(buttons.get(10)); puzzle.add(buttons.get(11)); 
                puzzle.add(buttons.get(12)); puzzle.add(buttons.get(13)); puzzle.add(buttons.get(14)); puzzle.add(buttons.get(15)); 
                puzzleSide.add(puzzle, verticalFormat);
                showCompletion.setText(completed + " out of " + buttons.size());
                puzzleSide.add(showCompletion, verticalFormat);
                completed = 0;
            } 
            
            
            puzzleSide.add(status, verticalFormat);
            puzzleSide.add(showIsSelected, verticalFormat);
            puzzleSide.add(showMoves, verticalFormat);
            
            for(int i = 0; i < selection.size(); i++){
                if(selection.get(i).equals(correctArrangement.get(i))){
                    completed++;
                }
            }
            showCompletion.setText(completed + " out of " + buttons.size());
            if(completed == buttons.size() && moves == 0){
                showCompletion.setText("Already completed...?");
            }

            //swapping icons of buttons...

            /*  Process:
            * Upon clicking a button, 
            * save the subimage of the button that was clicked.
            * Show which button was clicked.
            * Upon clicking another,
            * swap the image.
            * Clear the selected image out of current selection, as well as label.
            */

            /* That's complete now.
             * The process to complete it...
             * Record the 'proper' placement of the icons
             * Hmm.
             */

            frame.add(fullImageSide);
            frame.add(puzzleSide);
            frame.pack();
            frame.setVisible(true);
        }
        
    }

    public void actionPerformed(ActionEvent e){
        JButton selected = (JButton)e.getSource();
        int num = buttons.indexOf(selected);
        status.setText("Selected piece: " + num);
        Icon currentSection = selected.getIcon();
        //status.setIcon(currentSection);
        if(isSelected == false){
            currentButton = selected;
            isSelected = true;
            showIsSelected.setText("Currently swapping");
        } else {
            Icon previousIcon = currentButton.getIcon();
            //System.out.println(selection);
            Collections.swap(selection, selection.indexOf(previousIcon), selection.indexOf(currentSection));
            //System.out.println(selection);
            currentButton.setIcon(currentSection);
            selected.setIcon(previousIcon);
            isSelected = false;
            showIsSelected.setText("Not swapping");
            if(currentButton != selected){
                moves++;
                showMoves.setText("Current moves: " + moves);
            }
            
            completed = 0;
            for(int i = 0; i < selection.size(); i++){
                if(selection.get(i).equals(correctArrangement.get(i))){
                    completed++;
                }
            }
            showCompletion.setText(completed + " out of " + buttons.size());
            if(completed == buttons.size()){
                showCompletion.setText(completed + " out of " + buttons.size() + " - Completed in " + moves + " moves");
            }
        }
    }
}
