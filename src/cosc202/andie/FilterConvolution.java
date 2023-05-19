package cosc202.andie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class FilterConvolution {
    /**
     * Apply a convolution to an image with no offset.
     * 
     * @param input  image to apply the convolution to
     * @param array  float array of the kernel
     * @param radius the radius of the applied filter kernel
     */
    public static BufferedImage applyConvolution(BufferedImage input, float[] array, int radius) {
        return applyConvolution(input, array, radius, 0);
    }

    /**
     * Apply a convolution to an image with an offset.
     * 
     * @param input  image to apply the convolution to
     * @param array  float array of the kernel
     * @param radius the radius of the applied filter kernel
     * @param offset the value to which the final rgb values are offsetted, so allow
     *               for negative values.
     */
    public static BufferedImage applyConvolution(BufferedImage input, float[] array, int radius, int offset) {
        int height = input.getHeight();
        int width = input.getWidth();

        // Apply naive image convolution, if there is an offset. If there is not an
        // offset, then only apply the naive image convolution to the edges of the
        // image.
        BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (offset != 0 || (y < radius || y >= height - radius || x < radius || x >= width - radius)) {
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
                    r += offset;
                    g += offset;
                    b += offset;
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

                    System.out.println(r + " " + g + " " + b);
                    output.setRGB(x, y, outputColor.getRGB());
                }
            }
        }
        // if there is no offset, then apply java's ConvolveOp convolution to apply the
        // convolution to the center of the image.
        if (offset == 0) {
            Kernel k = new Kernel(1 + (radius * 2), 1 + (radius * 2), array);
            BufferedImage convolvedImage = new BufferedImage(input.getWidth(),
                    input.getHeight(),
                    input.getType());
            ConvolveOp op = new ConvolveOp(k, ConvolveOp.EDGE_ZERO_FILL, null);
            op.filter(input, convolvedImage);
            Graphics g = output.getGraphics();
            convolvedImage = convolvedImage.getSubimage(radius, radius, width - (radius * 2),
                    height - (radius * 2));
            g.drawImage(convolvedImage, radius, radius, null);
            g.dispose();
        }
        return output;
    }
}
