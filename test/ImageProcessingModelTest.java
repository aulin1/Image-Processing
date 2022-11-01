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
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, 255, "test");
    int[][][] returnTest = test.getImage();
    assertArrayEquals(returnTest, this.testBoard);
  }

  /**
   * Tests if the constructor of the model ensures that you cannot change the image.
   */
  @Test
  public void testConstructor2() {
    int[][][] otherTest = new int[][][]{{{1, 1, 1}, {1, 1, 1}}, {{1, 1, 1}, {1, 1, 1}}};
    ImageProcessingModel test = new PPMProcessingModel(otherTest, 255, "test");
    this.testBoard[0][0][0] = 10;
    int[][][] returnTest = new int[][][]{{{1, 1, 1}, {1, 1, 1}}, {{1, 1, 1}, {1, 1, 1}}};
    assertArrayEquals(test.getImage(), returnTest);
  }

  /**
   * Tests if the constructor of the model throws an IllegalArgumentException if the array is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullArray() {
    ImageProcessingModel test = new PPMProcessingModel(null, 255, "test");
  }

  /**
   * Tests if the constructor of the model throws an IllegalArgumentException if the name is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullName() {
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, 255, null);
  }

  /**
   * Tests if the constructor of the model throws an IllegalArgumentException if the amount of
   * colors is too little.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTwoColors() {
    int[][][] test = new int[2][2][2];
    ImageProcessingModel test2 = new PPMProcessingModel(test, 255, "test");
  }


  /**
   * Tests if the constructor of the model throws an IllegalArgumentException if the amount of
   * colors is too much.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testFourColors() {
    int[][][] test = new int[2][2][4];
    ImageProcessingModel test2 = new PPMProcessingModel(test, 255, "test");
  }

  /**
   * Tests if returning the array still doesn't allow the user to edit the array in the model.
   */
  @Test
  public void testGetImage() {
    int[][][] otherTest = new int[][][]{{{1, 1, 1}, {1, 1, 1}}, {{1, 1, 1}, {1, 1, 1}}};
    ImageProcessingModel test = new PPMProcessingModel(otherTest, 255, "test");
    int[][][] returnTest = test.getImage();
    returnTest[0][0][0] = 10;
    assertArrayEquals(otherTest, test.getImage());
  }

  /**
   * Tests if returnRedImage returns a greyscale image based on the red values.
   */
  @Test
  public void testRed() {
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, 255, "test");
    ImageProcessingModel red = test.returnRedImage();
    assertEquals("test_red", red.getName());
    assertArrayEquals(new int[][][]{{{1, 1, 1}, {4, 4, 4}}, {{7, 7, 7}, {10, 10, 10}}},
            red.getImage());
  }

  /**
   * Tests if returnGreenImage returns a greyscale image based on the green values.
   */
  @Test
  public void testGreen() {
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, 255, "test");
    ImageProcessingModel green = test.returnGreenImage();
    assertEquals("test_green", green.getName());
    assertArrayEquals(new int[][][]{{{2, 2, 2}, {5, 5, 5}}, {{8, 8, 8}, {11, 11, 11}}},
            green.getImage());
  }

  /**
   * Tests if returnBlueImage returns a greyscale image based on the blue values.
   */
  @Test
  public void testBlue() {
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, 255, "test");
    ImageProcessingModel blue = test.returnBlueImage();
    assertEquals("test_blue", blue.getName());
    assertArrayEquals(new int[][][]{{{3, 3, 3}, {6, 6, 6}}, {{9, 9, 9}, {12, 12, 12}}},
            blue.getImage());
  }

  /**
   * Tests if returnValueImage returns a greyscale image based on the values of the image.
   */
  @Test
  public void testValue() {
    int[][][] otherTest = new int[][][]{{{1, 2, 3}, {6, 5, 4}}, {{7, 9, 8}, {12, 12, 10}}};
    ImageProcessingModel test = new PPMProcessingModel(otherTest, 255, "test");
    ImageProcessingModel value = test.returnValueImage();
    assertEquals("test_value", value.getName());
    assertArrayEquals(new int[][][]{{{3, 3, 3}, {6, 6, 6}}, {{9, 9, 9}, {12, 12, 12}}},
            value.getImage());
  }

  /**
   * Tests if returnIntensityImage returns a greyscale image based on the intensity of the image.
   */
  @Test
  public void testIntensity() {
    int[][][] otherTest = new int[][][]{{{1, 2, 3}, {6, 5, 4}}, {{7, 9, 8}, {12, 12, 10}}};
    ImageProcessingModel test = new PPMProcessingModel(otherTest, 255, "test");
    ImageProcessingModel intensity = test.returnIntensityImage();
    assertEquals("test_intensity", intensity.getName());
    assertArrayEquals(new int[][][]{{{2, 2, 2}, {5, 5, 5}}, {{8, 8, 8}, {11, 11, 11}}},
            intensity.getImage());
  }

  /**
   * Tests if returnLumaImage returns a greyscale image based on the luma of the image.
   */
  @Test
  public void testLuma() {
    int[][][] otherTest = new int[][][]{{{1, 2, 3}, {100, 4, 2}}, {{7, 10, 8}, {12, 12, 10}}};
    ImageProcessingModel test = new PPMProcessingModel(otherTest, 255, "test");
    ImageProcessingModel luma = test.returnLumaImage();
    assertEquals("test_luma", luma.getName());
    assertArrayEquals(new int[][][]{{{2, 2, 2}, {24, 24, 24}}, {{9, 9, 9}, {12, 12, 12}}},
            luma.getImage());
  }

  /**
   * Tests if flipImageVertically does what it is supposed to.
   */
  @Test
  public void testFlipVert() {
    int[][][] otherBoard = new int[][][]{{{1, 1, 1}, {2, 2, 2}}, {{3, 3, 3}, {4, 4, 4}}, {{5, 5,
            5}, {6, 6, 6}}};
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, 255, "test");
    ImageProcessingModel test2 = new PPMProcessingModel(otherBoard, 255, "test2");
    ImageProcessingModel flip = test.flipImageVertically();
    ImageProcessingModel flip2 = test2.flipImageVertically();
    assertEquals("test_verticalFlip", flip.getName());
    assertEquals("test2_verticalFlip", flip2.getName());
    assertArrayEquals(new int[][][]{{{7, 8, 9}, {10, 11, 12}}, {{1, 2, 3}, {4, 5, 6}}},
            flip.getImage());
    assertArrayEquals(new int[][][]{{{5, 5, 5}, {6, 6, 6}}, {{3, 3, 3}, {4, 4, 4}}, {{1, 1, 1},
            {2, 2, 2}}}, flip2.getImage());
  }

  /**
   * Tests if flipImageHorizontally does what it is supposed to.
   */
  @Test
  public void testFlipHoriz() {
    int[][][] otherBoard = {{{1, 1, 1}, {2, 2, 2}, {3, 3, 3}}, {{4, 4, 4}, {5, 5, 5}, {6, 6, 6}}};
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, 255, "test");
    ImageProcessingModel test2 = new PPMProcessingModel(otherBoard, 255, "test2");
    ImageProcessingModel flip = test.flipImageHorizontally();
    ImageProcessingModel flip2 = test2.flipImageHorizontally();
    assertEquals("test_horizontalFlip", flip.getName());
    assertEquals("test2_horizontalFlip", flip2.getName());
    assertArrayEquals(new int[][][]{{{4, 5, 6}, {1, 2, 3}}, {{10, 11, 12}, {7, 8, 9}}},
            flip.getImage());
    assertArrayEquals(new int[][][]{{{3, 3, 3}, {2, 2, 2}, {1, 1, 1}}, {{6, 6, 6}, {5, 5, 5}, {4,
            4, 4}}}, flip2.getImage());
  }

  /**
   * Checks if brighten does what it is supposed to.
   */
  @Test
  public void testBrighten() {
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, 255, "test");
    ImageProcessingModel brighten = test.changeBrightness(10);
    assertEquals("test_changedBrightness", brighten.getName());
    assertArrayEquals(new int[][][]{{{11, 12, 13}, {14, 15, 16}}, {{17, 18, 19}, {20, 21, 22}}},
            brighten.getImage());
  }

  /**
   * Checks if darken does what it is supposed to.
   */
  @Test
  public void testDarken() {
    int[][][] otherTest = new int[][][]{{{11, 12, 13}, {14, 15, 16}}, {{17, 18, 19}, {20, 21, 22}}};
    ImageProcessingModel test = new PPMProcessingModel(otherTest, 255, "test");
    ImageProcessingModel darken = test.changeBrightness(-10);
    assertEquals("test_changedBrightness", darken.getName());
    assertArrayEquals(new int[][][]{{{1, 2, 3}, {4, 5, 6}}, {{7, 8, 9}, {10, 11, 12}}},
            darken.getImage());
  }

  /**
   * Checks if brighten would stop at a limit of 255.
   */
  @Test
  public void testBrightenLimit() {
    int[][][] otherTest = new int[][][]{{{250, 251, 252}, {253, 254, 255}}, {{0, 10, 20}, {249,
            248, 50}}};
    ImageProcessingModel test = new PPMProcessingModel(otherTest, 255, "test");
    ImageProcessingModel brighten = test.changeBrightness(5);
    assertEquals("test_brighten", brighten.getName());
    assertArrayEquals(new int[][][]{{{255, 255, 255}, {255, 255, 255}}, {{5, 15, 25}, {254, 253,
            55}}}, brighten.getImage());
  }

  /**
   * Checks if darken stops at a limit of 0.
   */
  @Test
  public void testDarkenLimit() {
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, 255, "test");
    ImageProcessingModel darken = test.changeBrightness(-6);
    assertEquals("test_darken", darken.getName());
    assertArrayEquals(new int[][][]{{{0, 0, 0}, {0, 0, 0}}, {{1, 2, 3}, {4, 5, 6}}},
            darken.getImage());
  }

  /**
   * Checks if getWidth returns the correct value. Width is the amount of columns in a row.
   */
  @Test
  public void testGetWidth() {
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, 255, "test");
    ImageProcessingModel test2 = new PPMProcessingModel(new int[3][5][3], 255, "test");
    ImageProcessingModel test3 = new PPMProcessingModel(new int[10][1][3], 255, "test");
    assertEquals(2, test.getWidth());
    assertEquals(5, test2.getWidth());
    assertEquals(1, test3.getWidth());
  }

  /**
   * Checks if getHeight returns the correct value. Height is the amount of rows in a column.
   */
  @Test
  public void testGetHeight() {
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, 255, "test");
    ImageProcessingModel test2 = new PPMProcessingModel(new int[3][5][3], 255, "test");
    ImageProcessingModel test3 = new PPMProcessingModel(new int[10][1][3], 255, "test");
    assertEquals(2, test.getHeight());
    assertEquals(3, test2.getHeight());
    assertEquals(10, test3.getHeight());
  }

  /**
   * Tests if getName returns the correct value.
   */
  @Test
  public void testGetName() {
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, 255, "test");
    ImageProcessingModel test2 = new PPMProcessingModel(new int[3][5][3], 255, "test2");
    ImageProcessingModel test3 = new PPMProcessingModel(new int[10][1][3], 255, "test3");
    assertEquals("test", test.getName());
    assertEquals("test2", test2.getName());
    assertEquals("test3", test3.getName());
  }

  /**
   * Tests if changeName works as intended.
   */
  @Test
  public void testChangeName() {
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, 255, "test");
    test.changeName("New name!");
    assertEquals("New name!", test.getName());
  }
}
