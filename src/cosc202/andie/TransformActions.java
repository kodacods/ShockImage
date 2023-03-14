package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Transform menu.
 * </p>
 * 
 * <p>
 * The Transform menu encompasses the following tasks regarding image modification:
 * Rotation, reflection / flip and dilation / resize.
 * </p>
 * 
 * @author Meg Albarico
 * @version 1.0
 */

public class TransformActions {
    /** List of actions for Transform menu */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create list of actions
     * </p>
     */

    public TransformActions(){
        actions = new ArrayList<Action>();
    }

    /**
     * <p>
     * Creates the menu containing said list of image transformation actions.
     * </p>
     */

     public JMenu createMenu(){
        JMenu fileMenu = new JMenu("Transform");

        for(Action action : actions){
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
     }

     
}
