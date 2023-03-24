package cosc202.andie;

import java.util.*;
import java.util.prefs.Preferences;

public class MultilingualSupport {
    Preferences prefs = Preferences.userNodeForPackage(App.class);

    Locale.setDefault(new Locale(prefs.get("language", "en"), 
            prefs.get("country", "NZ")));

}
