import org.junit.Before;
import org.junit.Test;

import model.*;

import static org.junit.Assert.*;

/**
 * Tests for the ImageProcessingModel.
 * */
//FIX: flip board issues
  //FIX: darken and brighten issues
  //FIX: expected and actual are switched
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
  public void testConstructor() {
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, "test");
    int[][][] returnTest = test.getImage();
    assertArrayEquals(returnTest, this.testBoard);
  }

  /**
   * Tests if the constructor of the model ensures that you cannot change the image.
   * */
  @Test
  public void testConstructor2(){
    int[][][] otherTest = new int[][][]{{{1, 1, 1}, {1, 1, 1}}, {{1, 1, 1}, {1, 1, 1}}};
    ImageProcessingModel test = new PPMProcessingModel(otherTest, "test");
    this.testBoard[0][0][0] = 10;
    int[][][] returnTest = new int[][][]{{{1, 1, 1}, {1, 1, 1}}, {{1, 1, 1}, {1, 1, 1}}};
    assertArrayEquals(test.getImage(), returnTest);
  }

  /**
   * Tests if the constructor of the model throws an IllegalArgumentException if the array is null.
   * */
  @Test(expected = IllegalArgumentException.class)
  public void testNullArray(){
    ImageProcessingModel test = new PPMProcessingModel(null, "test");
  }

  /**
   * Tests if the constructor of the model throws an IllegalArgumentException if the name is null.
   * */
  @Test(expected = IllegalArgumentException.class)
  public void testNullName(){
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, null);
  }

  /**
   * Tests if the constructor of the model throws an IllegalArgumentException if the amount of
   * colors is too little.
   * */
  @Test(expected = IllegalArgumentException.class)
  public void testTwoColors(){
    int[][][] test = new int[2][2][2];
    ImageProcessingModel test2 = new PPMProcessingModel(test, "test");
  }


  /**
   * Tests if the constructor of the model throws an IllegalArgumentException if the amount of
   * colors is too much.
   * */
  @Test(expected = IllegalArgumentException.class)
  public void testFourColors(){
    int[][][] test = new int[2][2][4];
    ImageProcessingModel test2 = new PPMProcessingModel(test, "test");
  }

  /**
   * Tests if returning the array still doesn't allow the user to edit the array in the model.
   * */
  @Test
  public void testGetImage(){
    int[][][] otherTest = new int[][][]{{{1, 1, 1}, {1, 1, 1}}, {{1, 1, 1}, {1, 1, 1}}};
    ImageProcessingModel test = new PPMProcessingModel(otherTest, "test");
    int[][][] returnTest = test.getImage();
    returnTest[0][0][0] = 10;
    assertArrayEquals(test.getImage(), otherTest);
  }

  /**
   * Tests if returnRedImage returns a greyscale image based on the red values.
   * */
  @Test
  public void testRed(){
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, "test");
    ImageProcessingModel red = test.returnRedImage();
    assertEquals("test_red", red.getName());
    assertArrayEquals(red.getImage(), new int[][][]{{{1, 1, 1},{4, 4, 4}},{{7, 7, 7}
            ,{10, 10, 10}}});
  }

  /**
   * Tests if returnGreenImage returns a greyscale image based on the green values.
   * */
  @Test
  public void testGreen(){
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, "test");
    ImageProcessingModel green = test.returnGreenImage();
    assertEquals("test_green", green.getName());
    assertArrayEquals(green.getImage(), new int[][][]{{{2, 2, 2},{5, 5, 5}},{{8, 8, 8}
            ,{11, 11, 11}}});
  }

  /**
   * Tests if returnBlueImage returns a greyscale image based on the blue values.
   * */
  @Test
  public void testBlue(){
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, "test");
    ImageProcessingModel blue = test.returnBlueImage();
    assertEquals("test_blue", blue.getName());
    assertArrayEquals(blue.getImage(), new int[][][]{{{3, 3, 3},{6, 6, 6}},{{9, 9, 9}
            ,{12, 12, 12}}});
  }

  /**
   * Tests if returnValueImage returns a greyscale image based on the values of the image.
   * */
  @Test
  public void testValue(){
    int[][][] otherTest = new int[][][]{{{1, 2, 3}, {6, 5, 4}}, {{7, 9, 8}, {12, 12, 10}}};
    ImageProcessingModel test = new PPMProcessingModel(otherTest, "test");
    ImageProcessingModel value = test.returnValueImage();
    assertEquals("test_value", value.getName());
    assertArrayEquals(value.getImage(), new int[][][]{{{3, 3, 3},{6, 6, 6}},{{9, 9, 9}
            ,{12, 12, 12}}});
  }

  /**
   * Tests if returnIntensityImage returns a greyscale image based on the intensity of the image.
   * */
  @Test
  public void testIntensity(){
    int[][][] otherTest = new int[][][]{{{1, 2, 3}, {6, 5, 4}}, {{7, 9, 8}, {12, 12, 10}}};
    ImageProcessingModel test = new PPMProcessingModel(otherTest, "test");
    ImageProcessingModel intensity = test.returnIntensityImage();
    assertEquals("test_intensity", intensity.getName());
    assertArrayEquals(intensity.getImage(), new int[][][]{{{2, 2, 2},{5, 5, 5}},{{8, 8, 8}
            ,{11, 11, 11}}});
  }

  /**
   * Tests if returnLumaImage returns a greyscale image based on the luma of the image.
   * */
  @Test
  public void testLuma(){
    int[][][] otherTest = new int[][][]{{{1, 2, 3}, {100, 4, 2}}, {{7, 10, 8}, {12, 12, 10}}};
    ImageProcessingModel test = new PPMProcessingModel(otherTest, "test");
    ImageProcessingModel luma = test.returnLumaImage();
    assertEquals("test_luma", luma.getName());
    assertArrayEquals(luma.getImage(), new int[][][]{{{2, 2, 2},{24, 24, 24}},{{9, 9, 9}
            ,{12, 12, 12}}});
    }

  /**
   * Tests if flipImageVertically does what it is supposed to.
   * */
  @Test
  public void testFlipVert(){
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, "test");
    ImageProcessingModel flip = test.flipImageHorizontally();
    assertEquals("test_horizontalFlip", flip.getName());
    assertArrayEquals(flip.getImage(), new int[][][]{{{10, 11, 12},{7, 8, 9}},{{4, 5, 6}
            ,{1, 2, 3}}});
  }

  /**
   * Tests if flipImageHorizontally does what it is supposed to.
   * */
  @Test
  public void testFlipHoriz(){
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, "test");
    ImageProcessingModel flip = test.flipImageHorizontally();
    assertEquals("test_horizontalFlip", flip.getName());
    assertArrayEquals(flip.getImage(), new int[][][]{{{3, 2, 1},{6, 5, 4}},{{9, 8, 7}
            ,{12, 11, 10}}});
  }

  /**
   * Checks if brighten does what it is supposed to.
   * */
  @Test
  public void testBrighten(){
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, "test");
    ImageProcessingModel brighten = test.brighten(10);
    assertEquals("test_brighten", brighten.getName());
    assertArrayEquals(brighten.getImage(), new int[][][]{{{11, 12, 13},{14, 15, 16}},{{17, 18, 19}
            ,{20, 21, 22}}});
  }

  /**
   * Checks if darken does what it is supposed to.
   * */
  @Test
  public void testDarken(){
    int[][][] otherTest = new int[][][]{{{11, 12, 13},{14, 15, 16}},{{17, 18, 19}
            ,{20, 21, 22}}};
    ImageProcessingModel test = new PPMProcessingModel(otherTest, "test");
    ImageProcessingModel darken = test.darken(10);
    assertEquals("test_darken", darken.getName());
    assertArrayEquals(darken.getImage(), new int[][][]{{{1, 2, 3},{4, 5, 6}},{{7, 8, 9}
            ,{10, 11, 12}}});
  }

  /**
   * Checks if brighten would stop at a limit of 255.
   * */
  @Test
  public void testBrightenLimit(){
    int[][][] otherTest = new int[][][]{{{250, 251, 252},{253, 254, 255}},{{0, 10, 20}
            ,{249, 248, 50}}};
    ImageProcessingModel test = new PPMProcessingModel(otherTest, "test");
    ImageProcessingModel brighten = test.brighten(5);
    assertEquals("test_brighten", brighten.getName());
    assertArrayEquals(brighten.getImage(), new int[][][]{{{255, 255, 255},{255, 255, 255}},{{5, 15, 25}
            ,{254, 254, 55}}});
  }

  /**
   * Checks if darken stops at a limit of 0.
   * */
  @Test
  public void testDarkenLimit(){
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, "test");
    ImageProcessingModel darken = test.darken(6);
    assertEquals("test_darken", darken.getName());
    assertArrayEquals(darken.getImage(), new int[][][]{{{0, 0, 0},{0, 0, 0}},{{1, 2, 3}
            ,{4, 5, 6}}});
  }

  /**
   * Checks if brighten throws an IllegalArgumentException if the factor is negative.
   * */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeBrighten(){
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, "test");
    ImageProcessingModel brighten = test.brighten(-5);
  }

  /**
   * Checks if darken throws an IllegalArgumentException if the factor is negative.
   * */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeDarken(){
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, "test");
    ImageProcessingModel darken = test.darken(-5);
  }

  /**
   * Checks if getWidth returns the correct value.
   * */
  @Test
  public void testGetWidth(){
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, "test");
    ImageProcessingModel test2 = new PPMProcessingModel(new int[3][5][3], "test");
    ImageProcessingModel test3 = new PPMProcessingModel(new int[10][1][3], "test");
    //assertEquals(2, test.getWidth());
    //finish
  }

  /**
   * Checks if getHeight returns the correct value.
   * */

  /**
   * Tests if getName returns the correct value.
   * */
  @Test
  public void testGetName(){
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, "test");
    ImageProcessingModel test2 = new PPMProcessingModel(new int[3][5][3], "test2");
    ImageProcessingModel test3 = new PPMProcessingModel(new int[10][1][3], "test3");
    assertEquals("test", test.getName());
    assertEquals("test2", test2.getName());
    assertEquals("test3", test3.getName());
  }

  /**
   * Tests if changeName works as intended.
   * */
  @Test
  public void testChangeName(){
    ImageProcessingModel test = new PPMProcessingModel(this.testBoard, "test");
    test.changeName("New name!");
    assertEquals("New name!", test.getName());
  }
}
