package cosc202.andie.test.cosc202.andie;

import java.awt.image.*;
import javax.imageio.*;
import java.net.URL;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions; //class
import org.junit.jupiter.api.BeforeAll;

import cosc202.andie.MedianFilter;
import cosc202.andie.EmbossFilter;
import cosc202.andie.SobelFilter;
import cosc202.andie.SharpenFilter;
import cosc202.andie.Brightness;
import cosc202.andie.Contrast;
import cosc202.andie.Rotate180Transform;
import cosc202.andie.RotateClock90Transform;
import cosc202.andie.RotateAntiClock90Transform;
import cosc202.andie.FlipHorzTransform;
import cosc202.andie.FlipVertTransform;

import java.util.Random;

/*Compares the pixel values of a specified position on two copies of the same picture after a filter has been applied, 
 *one picture is manually read from file while the other is stored as a data field.
 *Testing for the rotation and flipping methods are based on checking the position of the top right and left pixels,
 *before and after they have been rotated or flipped.
 */

public class RGBTest {

    private static BufferedImage testingImage;

    // retrieve image via file path
    @BeforeAll
    static void controlImage() {
        try {
            BufferedImage in = ImageIO.read(new URL(
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/University_of_Otago_Registry_Building2.jpg/1200px-University_of_Otago_Registry_Building2.jpg"));
            testingImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
        } catch (Exception e) {
            System.out.println("No image found here");
        }
    }

    // random int method
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    // Median filter test
    @Test
    void MedianFilterTest() {
        MedianFilter filter = new MedianFilter();
        BufferedImage manualImage = testingImage;
        BufferedImage testImage1 = filter.apply(testingImage);
        BufferedImage testImage2 = filter.apply(manualImage);
        int x = randInt(10, testImage1.getWidth() - 10);
        int y = randInt(10, testImage1.getHeight() - 10);

        Assertions.assertEquals(testImage1.getRGB(x, y), testImage2.getRGB(x, y));

    }

    // Brightness test
    @Test
    void BrightnessTest() {
        Brightness filter = new Brightness(50);
        BufferedImage manualImage;
        try {
            BufferedImage in = ImageIO.read(new URL(
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/University_of_Otago_Registry_Building2.jpg/1200px-University_of_Otago_Registry_Building2.jpg"));
            manualImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
        } catch (Exception e) {
            System.out.println("No image found");
            return;
        }
        BufferedImage testImage1 = filter.apply(manualImage);
        BufferedImage testImage2 = filter.apply(testingImage);
        int x = randInt(0, testImage1.getWidth());
        int y = randInt(0, testImage1.getHeight());

        Assertions.assertEquals(testImage1.getRGB(x, y), testImage2.getRGB(x, y));
    }

    // Contrast Test
    @Test
    void ContrastTest() {
        Contrast filter = new Contrast(50);

        BufferedImage manualImage;
        try {
            BufferedImage in = ImageIO.read(new URL(
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/University_of_Otago_Registry_Building2.jpg/1200px-University_of_Otago_Registry_Building2.jpg"));
            manualImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
        } catch (Exception e) {
            System.out.println("No image found");
            return;
        }
        BufferedImage testImage1 = filter.apply(manualImage);
        BufferedImage testImage2 = filter.apply(testingImage);
        int x = randInt(0, testImage1.getWidth());
        int y = randInt(0, testImage1.getHeight());

        Assertions.assertEquals(testImage1.getRGB(x, y), testImage2.getRGB(x, y));

    }

    // Emboss filter Test
    @Test
    void EmbossFilterTest() {
        EmbossFilter filter = new EmbossFilter();

        BufferedImage manualImage;
        try {
            BufferedImage in = ImageIO.read(new URL(
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/University_of_Otago_Registry_Building2.jpg/1200px-University_of_Otago_Registry_Building2.jpg"));
            manualImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
        } catch (Exception e) {
            System.out.println("No image found");
            return;
        }
        BufferedImage testImage1 = filter.apply(manualImage);
        BufferedImage testImage2 = filter.apply(testingImage);
        int x = randInt(0, testImage1.getWidth());
        int y = randInt(0, testImage1.getHeight());

        Assertions.assertEquals(testImage1.getRGB(x, y), testImage2.getRGB(x, y));

    }

    // Sobel filter Test
    @Test
    void SobelFilterTest() {
        SobelFilter filter = new SobelFilter();

        BufferedImage manualImage;
        try {
            BufferedImage in = ImageIO.read(new URL(
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/University_of_Otago_Registry_Building2.jpg/1200px-University_of_Otago_Registry_Building2.jpg"));
            manualImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
        } catch (Exception e) {
            System.out.println("No image found");
            return;
        }
        BufferedImage testImage1 = filter.apply(manualImage);
        BufferedImage testImage2 = filter.apply(testingImage);
        int x = randInt(0, testImage1.getWidth());
        int y = randInt(0, testImage1.getHeight());

        Assertions.assertEquals(testImage1.getRGB(x, y), testImage2.getRGB(x, y));

    }

    // Sharpen filter test

    @Test
    void SharpenFilterTest() {
        SharpenFilter filter = new SharpenFilter();

        BufferedImage manualImage;
        try {
            BufferedImage in = ImageIO.read(new URL(
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/University_of_Otago_Registry_Building2.jpg/1200px-University_of_Otago_Registry_Building2.jpg"));
            manualImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
        } catch (Exception e) {
            System.out.println("No image found");
            return;
        }
        BufferedImage testImage1 = filter.apply(manualImage);
        BufferedImage testImage2 = filter.apply(testingImage);
        int x = randInt(0, testImage1.getWidth());
        int y = randInt(0, testImage1.getHeight());

        Assertions.assertEquals(testImage1.getRGB(x, y), testImage2.getRGB(x, y));

    }

    // 180 Rotate test
    @Test
    void Rotate180Test() {
        Rotate180Transform filter = new Rotate180Transform();
        BufferedImage testImage = filter.apply(testingImage);
        Assertions.assertEquals(testingImage.getRGB(0, 0),
                testImage.getRGB(testImage.getWidth() - 1, testImage.getHeight() - 1));
        Assertions.assertEquals(testingImage.getRGB(testImage.getWidth() - 1, 0),
                testImage.getRGB(0, testImage.getHeight() - 1));

    }

    // 90 Rotate Test
    @Test
    void Rotate90Test() {
        RotateClock90Transform filter = new RotateClock90Transform();

        BufferedImage testImage = filter.apply(testingImage);
        Assertions.assertEquals(testingImage.getRGB(0, 0), testImage.getRGB(testImage.getWidth() - 1, 0));
        Assertions.assertEquals(testingImage.getRGB(testImage.getHeight() - 1, 0),
                testImage.getRGB(testImage.getWidth() - 1, testImage.getHeight() - 1));
    }

    // AntiClockwise 90 Rotate Test
    @Test
    void Anti90Test() {
        RotateAntiClock90Transform filter = new RotateAntiClock90Transform();

        BufferedImage testImage = filter.apply(testingImage);
        Assertions.assertEquals(testingImage.getRGB(0, 0), testImage.getRGB(0, testImage.getHeight() - 1));
        Assertions.assertEquals(testingImage.getRGB(testImage.getHeight() - 1, 0), testImage.getRGB(0, 0));

    }

    // Flipping Horzisontally test
    @Test
    void FlipHorzTest() {
        FlipHorzTransform filter = new FlipHorzTransform();

        BufferedImage testImage = filter.apply(testingImage);
        Assertions.assertEquals(testingImage.getRGB(0, 0), testImage.getRGB(testImage.getWidth() - 1, 0));
        Assertions.assertEquals(testingImage.getRGB(testImage.getWidth() - 1, 0), testImage.getRGB(0, 0));

    }

    // Flipping Vertically test
    @Test
    void FlipVertTest() {
        FlipVertTransform filter = new FlipVertTransform();

        BufferedImage testImage = filter.apply(testingImage);
        Assertions.assertEquals(testingImage.getRGB(0, 0), testImage.getRGB(0, testImage.getHeight() - 1));
        Assertions.assertEquals(testingImage.getRGB(testImage.getWidth() - 1, 0),
                testImage.getRGB(testImage.getWidth() - 1, testImage.getHeight() - 1));

    }

}