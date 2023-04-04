package cosc202.andie.test.cosc202.andie;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions; //class

import cosc202.andie.Brightness;
import cosc202.andie.Contrast;

//tests for the trunacate formula

public class TrunacateTest {
    @Test
    void initialTest(){
    }

    @Test
    void BrightnessTest1(){
        Assertions.assertEquals(0,Brightness.truncate(-2));
        Assertions.assertEquals(255,Brightness.truncate(500));
        Assertions.assertEquals(3,Brightness.truncate(3));
    }

    @Test
    void ContrastTest1(){
        Assertions.assertEquals(0,Contrast.truncate(-2));
        Assertions.assertEquals(255,Contrast.truncate(500));
        Assertions.assertEquals(3,Contrast.truncate(3));
    }

    




}