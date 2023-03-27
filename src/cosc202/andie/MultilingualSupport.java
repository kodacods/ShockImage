package cosc202.andie;

import java.util.*;
import java.util.prefs.Preferences;

public class MultilingualSupport {

    private String language;

    MultilingualSupport(String language){
        this.language = language;
    }

    public String setLanguage (String key){
        Preferences prefs = Preferences.userNodeForPackage(MultilingualSupport.class);
        
        ResourceBundle MessageBundle = ResourceBundle.getBundle("MessageBundle");

        if (language.equals("English")){
            prefs.put("language", "en");
            prefs.put("country", "NZ");

        } else if (language.equals("German")){
            prefs.put("language", "mi");
            prefs.put("country", "NZ");
        }
        return MessageBundle.getString(key);
    }
}
