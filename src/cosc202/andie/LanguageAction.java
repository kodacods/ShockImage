package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the language menu.
 * </p>
 * 
 * <p>
 * The language menu gives the user the option to change language for the ui/
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Beka Rolleston
 * @version 1.0
 */
public class LanguageAction {

    /** A list of actions for the Language menu. */
    protected ArrayList<Action> actions;
    //MultilingualSupport ms = new MultilingualSupport("English");
    /**
     * <p>
     * Create a set of File menu actions.
     * </p>
     */
    public LanguageAction() {
        actions = new ArrayList<Action>();
        actions.add(new LangChangeLanguage ("Change Language", null, "changes language", null));
    }

    /**
     * <p>
     * Create a menu contianing the list of language actions.
     * </p>
     * 
     * @return The File menu UI element.
     */
    public JMenu createMenu() {
        JMenu languageMenu = new JMenu("Language");

        for(Action action: actions) {
            languageMenu.add(new JMenuItem(action));
        }

        return languageMenu;
    }

    public class LangChangeLanguage extends ImageAction {

        LangChangeLanguage (String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String language = "English";
            String [] languageList = {"English", "German"};

            SpinnerListModel languageModel = new SpinnerListModel(languageList);
            JSpinner languageSpinner = new JSpinner(languageModel);
            int option = JOptionPane.showOptionDialog(null, languageSpinner, "Select language", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                language = (String) languageModel.getValue();
            }

                        

        }
    }
}
