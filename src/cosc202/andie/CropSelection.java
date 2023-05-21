package cosc202.andie;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CropSelection implements ImageOperation, java.io.Serializable {
    private int x;
    private int y;
    private int w;
    private int h;

    public CropSelection(int posx, int posy, int width, int height) {
        x = posx;
        y = posy;
        w = width;
        h = height;
    }

    public BufferedImage apply(BufferedImage input) {
        input = input.getSubimage(x, y, w, h);
        return input;
    }

    public class CropAction extends ImageAction {
        /**
         * Create a new crop action.
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        CropAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * This method is called whenever the CropAction is triggered. It applies the
         * CropSelection to the target image and repaints the image panel.
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Create and apply CropSelection
            target.getImage().apply(new CropSelection(ImagePanel.origin.x, ImagePanel.origin.y, ImagePanel.selWidth,
                    ImagePanel.selHeight));
            target.repaint();
            target.getParent().revalidate();
            // Reset selection rectangle
            ImagePanel.origin = null;
            ImagePanel.selWidth = 0;
            ImagePanel.selHeight = 0;

            ;
        }

    }

    /**
     * Creates a new JButton with the CropAction as its action.
     * 
     * @return The new JButton.
     */
    public JButton createButton() {
        // Create a new CropAction with the desired name, icon, and description
        CropAction cropAction = new CropAction("Crop", null, "Crop", KeyEvent.VK_C);

        // Create a new JButton with the CropAction as its action

        JButton cropButton = new JButton(cropAction);

        // Return the new JButton
        return cropButton;
    }

}
