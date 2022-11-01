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


  /**
   * Tests if saving an image works.
   * */
  /**
   * Tests if using a correct command works.
   * */
  /**
   * Tests if using an incorrect command works.
   * */
  /**
   * Tests if using an incorrect, then correct command works.
   * */
  /**
   * Tests if it throws an IllegalStateException if not quit.
   * */
}
