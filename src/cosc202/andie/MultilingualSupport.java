package cosc202.andie;

import java.util.*;
import java.util.prefs.Preferences;

public class MultilingualSupport {

    private String language;

    MultilingualSupport(String language){
        this.language = language;
    }
    
    Preferences prefs = Preferences.userNodeForPackage(MultilingualSupport.class);

    Locale english = new Locale (prefs.get("language", "en"), prefs.get("country", "NZ"));
    Locale german  = new Locale (prefs.get("language", "de"), prefs.get("country", "DE"));
    
    {if (language.equals("English")){
        prefs.put("language", "en");
        prefs.put("country", "NZ");

    } else if (language.equals("German")){
        prefs.put("language", "mi");
        prefs.put("country", "NZ");
    }}

    public String setLanguage (String key){
        ResourceBundle MessageBundle = ResourceBundle.getBundle("MessageBundle");
        return MessageBundle.getString(key);
    }
}
