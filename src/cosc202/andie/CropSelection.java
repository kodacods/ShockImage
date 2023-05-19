package cosc202.andie;

import java.awt.image.*;

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
        BufferedImage inpCrop = input.getSubimage(x, y, w, h);
        input = inpCrop;
        return inpCrop;
    }
}
