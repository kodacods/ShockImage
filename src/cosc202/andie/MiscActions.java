package cosc202.andie;

import java.util.*;
import java.util.prefs.Preferences;
import java.awt.event.*;
import javax.swing.*;

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

    public class PuzzleGame extends ImageAction{

        PuzzleGame(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

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
