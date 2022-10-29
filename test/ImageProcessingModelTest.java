import org.junit.Before;
import org.junit.Test;

import model.*;

import static org.junit.Assert.*;

/**
 * Tests for the ImageProcessingModel.
 * */
public class ImageProcessingModelTest {

  /**
   * A 3D array for testing operations.
   * */
  private int[][][] testBoard;

  /**
   * Initializes and creates a standard testBoard for simple testing.
   * */
  @Before
  public void createTestBoard(){
    this.testBoard = new int[2][2][3];
    this.testBoard[0][0] = new int[]{1, 2, 3};
    this.testBoard[0][1] = new int[]{4, 5, 6};
    this.testBoard[1][0] = new int[]{7, 8, 9};
    this.testBoard[1][1] = new int[]{10, 11, 12};
  }


  /**
   * Tests the constructor of the model.
   * */
  @Test
  public void testConstructor(){
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, "test");
    
  }

  /**
   * Tests if the constructor of the model ensures that you cannot change the image.
   * */

  /**
   * Tests if the constructor of the model throws an IllegalArgumentException if the array is null.
   * */

  /**
   * Tests if the constructor of the model throws an IllegalArgumentException if the amount of
   * colors is too little.
   * */

  /**
   * Tests if the constructor of the model throws an IllegalArgumentException if the amount of
   * colors is too much.
   * */

  /**
   * Tests if the constructor of the model throws an Exception if the array is not in the correct
   * format.
   * */

  /**
   * Tests if returning the array still doesn't allow the user to edit the array in the model.
   * */

  /**
   * Tests if returnRedImage returns a greyscale image based on the red values.
   * */

  /**
   * Tests if returnGreenImage returns a greyscale image based on the green values.
   * */

  /**
   * Tests if returnBlueImage returns a greyscale image based on the blue values.
   * */

  /**
   * Tests if returnValueImage returns a greyscale image based on the values of the image.
   * */

  /**
   * Tests if returnIntensityImage returns a greyscale image based on the intensity of the image.
   * */

  /**
   * Tests if returnLumaImage returns a greyscale image based on the luma of the image.
   * */

  /**
   * Tests if flipImageVertically does what it is supposed to.
   * */

  /**
   * Tests if flipImageHorizontally does what it is supposed to.
   * */

  /**
   * Checks if brighten does what it is supposed to.
   * */

  /**
   * Checks if darken does what it is supposed to.
   * */

  /**
   * Checks if brighten would stop at a limit of 255.
   * */

  /**
   * Checks if darken stops at a limit of 0.
   * */

  /**
   * Checks if brighten throws an IllegalArgumentException if the factor is negative.
   * */

  /**
   * Checks if darken throws an IllegalArgumentException if the factor is negative.
   * */

  /**
   * Checks if getWidth returns the correct value.
   * */

  /**
   * Checks if getHeight returns the correct value.
   * */
}
