import org.junit.Test;

import controller.ImageUtil;
import model.ImageProcessingModel;

import static org.junit.Assert.assertEquals;

/**
 * This class represents tests for controller.ImageUtil.
 */
public class ImageUtilTest {
  String koalaPath = "res/Koala.ppm";
  String pixelPath = "res/Pixel.ppm";

  String pixelImg = "0\n" + "0\n" + "0\n" + "153\n" + "217\n" + "234\n" + "153\n" + "217\n"
          + "234\n" + "127\n" + "127\n" + "127\n" + "153\n" + "217\n" + "234\n" + "237\n" + "28\n"
          + "36\n" + "205\n" + "85\n" + "207\n" + "153\n" + "217\n" + "234\n" + "153\n" + "217\n"
          + "234\n" + "12\n" + "102\n" + "36\n" + "255\n" + "242\n" + "0\n" + "153\n" + "217\n"
          + "234\n" + "0\n" + "162\n" + "232\n" + "153\n" + "217\n" + "234\n" + "153\n" + "217\n"
          + "234\n" + "255\n" + "255\n" + "255\n";

  /**
   * Test if readPPM() correctly reads a ppm file to a 3D array of the imageBoard pixels.
   */
  @Test
  public void readPPMWorks() {
    // check if the method build the array with the correct dimension to the image
    ImageProcessingModel model = ImageUtil.readPPM(koalaPath);
    assertEquals(768, model.getHeight());
    assertEquals(1024, model.getWidth());

    ImageProcessingModel pixelModel = ImageUtil.readPPM(pixelPath);
    assertEquals(4, pixelModel.getHeight());
    assertEquals(4, pixelModel.getWidth());

    // check if every pixel has the correct rgb
    int[][][] image = pixelModel.getImage();
    StringBuilder imageRead = new StringBuilder();
    for (int i = 0; i < pixelModel.getHeight(); i++) {
      for (int j = 0; j < pixelModel.getWidth(); j++) {
        imageRead.append(image[i][j][0]).append("\n"); // red value
        imageRead.append(image[i][j][1]).append("\n"); // green value
        imageRead.append(image[i][j][2]).append("\n"); // blue value
      }
    }
    assertEquals(pixelImg, imageRead.toString());
  }

  /**
   * Test if readPPM() throws IllegalArgumentException when the file cannot be found or the
   * format of the input is not valid (not a file path).
   */
  @Test
  public void readPPMThrowsExceptionIncorrectInput() {
    try {
      ImageProcessingModel model = ImageUtil.readPPM("bool");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot find file! ", e.getMessage());
    }
  }

  @Test
  public void readPPMThrowsExceptionPPMDoesntExist() {
    try {
      ImageProcessingModel model = ImageUtil.readPPM("Chicken.ppm");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot find file! ", e.getMessage());
    }
  }

  @Test
  public void readPPMThrowsExceptionNotP3PPM() {
    try {
      ImageProcessingModel model = ImageUtil.readPPM("res/PixelP6.ppm");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid PPM file: plain RAW file should begin with P3", e.getMessage());
    }
  }
}