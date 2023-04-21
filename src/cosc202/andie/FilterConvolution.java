package cosc202.andie;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Apply a convolution to an image.
 * 
 * @param input  image to apply the convolution to
 * @param array  float array of the kernel
 * @param radius the radius of the applied filter kernel
 */
public class FilterConvolution {
    public static BufferedImage applyConvolution(BufferedImage input, float[] array, int radius) {
        int height = input.getHeight();
        int width = input.getWidth();

        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null),
                input.isAlphaPremultiplied(), null);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                float r = 0.0f;
                float g = 0.0f;
                float b = 0.0f;
                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {
                        int xValue = dx;
                        int yValue = dy;
                        if (x + xValue < 0 || x + xValue >= width - 1)
                            xValue = 0;
                        if (y + yValue < 0 || y + yValue >= height - 1)
                            yValue = 0;
                        float currentKernelValue = array[(dx + radius) + (((radius * 2) + 1) * (dy + radius))];
                        Color currentColor = new Color(input.getRGB(x + xValue, y + yValue));

                        float dr = currentColor.getRed() * currentKernelValue;
                        float dg = currentColor.getGreen() * currentKernelValue;
                        float db = currentColor.getBlue() * currentKernelValue;
                        r += dr;
                        g += dg;
                        b += db;
                    }
                }
                if (r > 255)
                    r = 255;
                if (g > 255)
                    g = 255;
                if (b > 255)
                    b = 255;
                if (r < 0)
                    r = 0;
                if (g < 0)
                    g = 0;
                if (b < 0)
                    b = 0;
                Color outputColor = new Color(Math.round(r), Math.round(g), Math.round(b));

                output.setRGB(x, y, outputColor.getRGB());
            }
        }
        return output;
    }
}
