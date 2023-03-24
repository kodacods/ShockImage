package cosc202.andie.test.cosc202.andie;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions; //class

import cosc202.andie.ImagePanel;
public class ImagePanelTest {
    @Test
    void initialTest(){
    }


    @Test
    void getZoomInitialValue(){
        ImagePanel testPanel = new ImagePanel();
        Assertions.assertEquals(100.0,testPanel.getZoom());
        //boolean compares reults to expected answer .assertFalse / .assertTrue
    }
    
    
}


