package cosc202.andie;

import java.util.*;
import java.util.prefs.Preferences;

public class MultilingualSupport {

    private String language;

    MultilingualSupport(String language){
        this.language = language;
    }

    

    public void setLanguage(){

        Preferences prefs = Preferences.userNodeForPackage(MultilingualSupport.class);

        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));

        ResourceBundle bundle = ResourceBundle.getBundle("TMessageBundle");
        Enumeration<String> bundleKeys = bundle.getKeys();
        while (bundleKeys.hasMoreElements()) {
            String key = bundleKeys.nextElement();
            bundle.getString(key);
        }

            if (language.equals("German")){
                prefs.put("language", "de");
                prefs.put("country", "DE");

            } else if (language.equals("English")){
                prefs.put("language", "en");
                prefs.put("country", "NZ");
            }
    }

}
