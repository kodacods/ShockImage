package cosc202.andie;

import java.util.*;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import java.util.prefs.Preferences;


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
    /**
     * <p>
     * Create a set of Language menu actions.
     * </p>
     */
    public LanguageAction() {
        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        ResourceBundle bundle = ResourceBundle.getBundle("TMessageBundle");

        actions = new ArrayList<Action>();
        actions.add(new LangChangeLanguage (bundle.getString("ChangeLanguage"), null, "changes language", null));
    }

    /**
     * <p>
     * Create a menu contianing the list of language actions.
     * </p>
     * 
     * @return The File menu UI element.
     */
    public JMenu createMenu() {
        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        ResourceBundle bundle = ResourceBundle.getBundle("TMessageBundle");

        JMenu languageMenu = new JMenu(bundle.getString("Language"));

        for(Action action: actions) {
            languageMenu.add(new JMenuItem(action));
        }

        return languageMenu;
    }
    /**
     * <p>
     * This method is called whenever the LanguageAction is triggered.
     * It prompts the user for a language to change the ui to.
     * </p>
     * 
     * @param e The event triggering this callback.
     */
    public class LangChangeLanguage extends ImageAction {

        LangChangeLanguage (String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_L, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String language = "English";
            String [] languageList = {"English", "Deutsch"};

            SpinnerListModel languageModel = new SpinnerListModel(languageList);
            JSpinner languageSpinner = new JSpinner(languageModel);
            int option = JOptionPane.showOptionDialog(null, languageSpinner, "Select language", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            Preferences prefs = Preferences.userNodeForPackage(Andie.class);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                language = (String) languageModel.getValue();
                System.out.println(language);
                if (language.equals("Deutsch")){
                    prefs.put("language", "de");
                    prefs.put("country", "DE");
    
                } else if (language.equals("English")){
                    prefs.put("language", "en");
                    prefs.put("country", "NZ");
                }    
            }    

        }
    }
}
