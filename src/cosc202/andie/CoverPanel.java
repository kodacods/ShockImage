package cosc202.andie;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CoverPanel extends JPanel{
    {setOpaque(false);}
    public void paintComponent(Graphics g){
        g.setColor(getBackground());
        Rectangle r = g.getClipBounds();
        g.fillRect(r.x, r.y, r.width, r.height);
        super.paintComponent(g);
    }
}
