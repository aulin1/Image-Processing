import org.junit.Before;
import org.junit.Test;

import model.ImageProcessingModel;
import model.PPMProcessingModel;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Tests for the ImageProcessingModel.
 */
public class ImageProcessingModelTest {

  /**
   * A 3D array for testing operations.
   */
  private int[][][] testBoard;

  /**
   * Initializes and creates a standard testBoard for simple testing.
   */
  @Before
  public void createTestBoard() {
    this.testBoard = new int[2][2][3];
    this.testBoard[0][0] = new int[]{1, 2, 3};
    this.testBoard[0][1] = new int[]{4, 5, 6};
    this.testBoard[1][0] = new int[]{7, 8, 9};
    this.testBoard[1][1] = new int[]{10, 11, 12};
  }

  /**
   * Tests the constructor of the model.
   */
  @Test
  public void testConstructor() {
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, 255);
    int[][][] returnTest = test.getImage();
    assertArrayEquals(returnTest, this.testBoard);
  }

  /**
   * Tests if the constructor of the model ensures that you cannot change the image.
   */
  @Test
  public void testConstructor2() {
    int[][][] otherTest = new int[][][]{{{1, 1, 1}, {1, 1, 1}}, {{1, 1, 1}, {1, 1, 1}}};
    ImageProcessingModel test = new PPMProcessingModel(otherTest, 255);
    this.testBoard[0][0][0] = 10;
    int[][][] returnTest = new int[][][]{{{1, 1, 1}, {1, 1, 1}}, {{1, 1, 1}, {1, 1, 1}}};
    assertArrayEquals(test.getImage(), returnTest);
  }

  /**
   * Tests if the constructor of the model throws an IllegalArgumentException if the array is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullArray() {
    ImageProcessingModel test = new PPMProcessingModel(null, 255);
  }

  /**
   * Tests if the constructor of the model throws an IllegalArgumentException if the max value is
   * negative.
   * */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeMax(){
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, -10);
  }

  /**
   * Tests if the constructor of the model throws an IllegalArgumentException if the max value is 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testZeroMax(){
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, 0);
  }

  /**
   * Tests if returning the array still doesn't allow the user to edit the array in the model.
   */
  @Test
  public void testGetImage() {
    int[][][] otherTest = new int[][][]{{{1, 1, 1}, {1, 1, 1}}, {{1, 1, 1}, {1, 1, 1}}};
    ImageProcessingModel test = new PPMProcessingModel(otherTest, 255);
    int[][][] returnTest = test.getImage();
    returnTest[0][0][0] = 10;
    assertArrayEquals(otherTest, test.getImage());
  }

  /**
   * Tests if getWidth gets the correct value. Width is the amount of columns in a row.
   * */
  @Test
  public void testGetWidth() {
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, 255);
    ImageProcessingModel test2 = new PPMProcessingModel(new int[3][5][3], 255);
    ImageProcessingModel test3 = new PPMProcessingModel(new int[10][1][3], 255);
    assertEquals(2, test.getWidth());
    assertEquals(5, test2.getWidth());
    assertEquals(1, test3.getWidth());
  }

  /**
   * Checks if getHeight returns the correct value. Height is the amount of rows in a column.
   */
  @Test
  public void testGetHeight() {
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, 255);
    ImageProcessingModel test2 = new PPMProcessingModel(new int[3][5][3], 255);
    ImageProcessingModel test3 = new PPMProcessingModel(new int[10][1][3], 255);
    assertEquals(2, test.getHeight());
    assertEquals(3, test2.getHeight());
    assertEquals(10, test3.getHeight());
  }

  /**
   * Tests if getMax returns the correct value.
   * */
  @Test
  public void testGetMax(){
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, 255);
    ImageProcessingModel test2 = new PPMProcessingModel(new int[3][5][3], 10);
    ImageProcessingModel test3 = new PPMProcessingModel(new int[10][1][3], 300);
    assertEquals(255, test.getMax());
    assertEquals(10, test2.getMax());
    assertEquals(300, test3.getMax());
  }
}