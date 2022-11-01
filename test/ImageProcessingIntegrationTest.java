import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;

import static org.junit.Assert.*;

/**
 * Tests for integrating the model, view, and controller.
 * */
public class ImageProcessingIntegrationTest {

  private String filePath;
  private String fileName;

  @Before
  public void getImageFile(){
    filePath = "res/Pixel.ppm";
    fileName = "Pixel.ppm";
  }

  /**
   * Tests the constructor of ImageProcessingControllerImpl.
   * */
  @Test
  public void testConstructor(){
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("");
    ImageProcessingController test = new ImageProcessingControllerImpl(out, in);
    assertNotNull("Failed", test);
  }

  /**
   * Tests if loading an image works.
   * */
  @Test
  public void testLoad(){
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load " + filePath + " " + fileName
    + " q");
    ImageProcessingController test = new ImageProcessingControllerImpl(out, in);
    test.start();
    //add more here
  }

  /**
   * Tests if saving an image works.
   * */
  @Test
  public void testSave(){

  }

  /**
   * Tests if using multiple correct command works.
   * */
  @Test
  public void testCommands(){

  }

  /**
   * Tests if using an incorrect command works.
   * */
  @Test
  public void testIncorrectCommand(){

  }

  /**
   * Tests if using an incorrect, then correct command works.
   * */
  @Test
  public void testIncorrectCorrectCommand(){

  }

  /**
   * Tests if it throws an IllegalStateException if not quit.
   * */
  @Test(expected = IllegalStateException.class)
  public void testNoQuit(){

  }
}
