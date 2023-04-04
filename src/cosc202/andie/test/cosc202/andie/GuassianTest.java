package cosc202.andie.test.cosc202.andie;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions; //class

import cosc202.andie.GaussianBlur;
import java.text.DecimalFormat;


public class GuassianTest {


    @Test
    void initialTest(){
    }

    @Test
    void firstCalcTest(){
        Assertions.assertEquals(0.0099f,GaussianBlur.firstCalc(4), 0.0001);
    }

    @Test
    void secCalcTest(){
        Assertions.assertEquals(0.5697f,GaussianBlur.secondCalc(4), 0.0001);
    }

    

    



}
