package cosc202.andie;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class FilterConvolution {
    public static BufferedImage applyConvolution(BufferedImage input, float[] array, int radius) {
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null),
                input.isAlphaPremultiplied(), null);
        for (int y = 0; y < input.getHeight(); y++) {
            for (int x = 0; x < input.getWidth(); x++) {
                float r = 0.0f;
                float g = 0.0f;
                float b = 0.0f;
                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {
                        int xValue = dx;
                        int yValue = dy;
                        if (x + xValue < 0 || x + xValue >= input.getWidth() - 1)
                            xValue = 0;
                        if (y + yValue < 0 || y + yValue >= input.getHeight() - 1)
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
                Color outputColor = new Color(Math.round(r), Math.round(g), Math.round(b));

                output.setRGB(x, y, outputColor.getRGB());
            }
        }

        return output;
    }
}
