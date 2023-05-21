package cosc202.andie;

import java.util.*;
import java.util.prefs.Preferences;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Miscellaneous menu.
 * </p>
 * 
 * <p>
 * Not so much as actions performed on the image itself, 
 * but miscellaneous actions not related to image editing.
 * </p>
 * 
 * @author Meg Albarico
 * @version 1.0
 */

public class MiscActions {
    protected ArrayList<Action> actions;

    public MiscActions(){

        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        ResourceBundle bundle = ResourceBundle.getBundle("TMessageBundle");

        actions = new ArrayList<Action>();
        actions.add(new PuzzleGame("Puzzle Game (needs bundle transl.)", null, "A small puzzle game.", Integer.valueOf(KeyEvent.VK_M)));
    }

    public JMenu createMenu(){
        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        ResourceBundle bundle = ResourceBundle.getBundle("TMessageBundle");

        JMenu miscMenu = new JMenu("Misc. (needs transl.)");

        for(Action action: actions){
            miscMenu.add(new JMenuItem(action));
        }
        return miscMenu;
    }

    /**
     * <p>
     * Action to make a puzzle out of an image. Also accounts for operations made on the image.
     * </p>
     * 
     * @see PuzzleGame
     */

    public class PuzzleGame extends ImageAction{

        /**
         * <p>
         * Create a new puzzle action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */

        PuzzleGame(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the puzzle action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the PuzzleGameAction is triggered.
         * It prompts the user for the difficulty, and creates a
         * {@link PuzzleGameAction} with the appropriate amount of cells in the puzzle.
         * </p>
         * 
         * @param e The event triggering this callback.
         */

        public void actionPerformed(ActionEvent e){
            String[] options = {"2 X 2", "3 X 3", "4 X 4"};
            int difficulty = JOptionPane.showOptionDialog(null, "Select Difficulty", "Game Start",JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
            PuzzleGameAction thisGame = new PuzzleGameAction();
            if(difficulty == 0){
                thisGame.createAndShowGame(2, target.getImage());
            } else if(difficulty == 1){
                thisGame.createAndShowGame(3, target.getImage());
            } else if(difficulty == 2){
                thisGame.createAndShowGame(4, target.getImage());
            }
        }
    }
}
