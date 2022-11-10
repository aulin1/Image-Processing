import org.junit.Test;

import controller.ImageUtil;
import image.ImageClass;

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
  String pixelImgJPG = "7\n" + "0\n" + "8\n" + "153\n" + "221\n" + "230\n" + "145\n" + "218\n"
          + "227\n" + "126\n" + "131\n" + "124\n" + "150\n" + "219\n" + "234\n" + "234\n" + "28\n"
          + "28\n" + "205\n" + "90\n" + "217\n" + "155\n" + "209\n" + "247\n" + "157\n" + "217\n"
          + "218\n" + "15\n" + "95\n" + "44\n" + "255\n" + "240\n" + "0\n" + "169\n" + "215\n"
          + "238\n" + "0\n" + "169\n" + "238\n" + "153\n" + "222\n" + "237\n" + "160\n" + "219\n"
          + "233\n" + "255\n" + "247\n" + "248\n";

  /**
   * Test if readPPM() correctly reads a ppm file to a 3D array of the imageBoard pixels.
   */
  @Test
  public void readPPMWorks() {
    // check if the method build the array with the correct dimension to the image
    ImageClass model = ImageUtil.readPPM(koalaPath);
    assertEquals(768, model.getHeight());
    assertEquals(1024, model.getWidth());

    ImageClass pixelModel = ImageUtil.readPPM(pixelPath);
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
      ImageClass model = ImageUtil.readPPM("bool");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot find file! ", e.getMessage());
    }
  }

  /**
   * Test if readIMG() throw an exception if the file does not exist.
   */
  @Test
  public void readPPMThrowsExceptionPPMDoesntExist() {
    try {
      ImageClass model = ImageUtil.readPPM("Chicken.ppm");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot find file! ", e.getMessage());
    }
  }

  /**
   * Test if readIMG() throw an exception if the file is not a P3 but still a PPM.
   */
  @Test
  public void readPPMThrowsExceptionNotP3PPM() {
    try {
      ImageClass model = ImageUtil.readPPM("res/PixelP6.ppm");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid PPM file: plain RAW file should begin with P3", e.getMessage());
    }
  }

  /**
   * Test if readPPM() throw an exception if the filename is null.
   */
  @Test
  public void readPPMThrowsExceptionNullFileName() {
    try {
      ImageClass model = ImageUtil.readPPM(null);
    } catch (IllegalArgumentException e) {
      assertEquals("The filename cannot be null.", e.getMessage());
    }
  }

  /**
   * Test if the new readIMG() method reads a PNG properly against known rgb values of an image.
   */
  @Test
  public void readIMGWorksPNG() {
    ImageClass pixelModel = ImageUtil.readIMG("res/Pixel.png");
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
   * Test if the new readIMG() method reads a JPG properly against known rgb values of an image.
   * This test tests against a different value because the image's rgb value changed when
   * converting from Pixel.ppm to Pixel.jpg.
   */
  @Test
  public void readIMGWorksJPG() {
    ImageClass pixelModel = ImageUtil.readIMG("res/Pixel.jpg");
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
    assertEquals(pixelImgJPG, imageRead.toString());
  }

  /**
   * Test if the new readIMG() method reads a BMP properly against known rgb values of an image.
   */
  @Test
  public void readIMGWorksBMP() {
    ImageClass pixelModel = ImageUtil.readIMG("res/Pixel.bmp");
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
   * Test if the new readIMG() method reads a PPM properly against known rgb values of an image.
   */
  @Test
  public void readIMGWorksPPM() {
    ImageClass pixelModel = ImageUtil.readIMG("res/Pixel.ppm");
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
   * Test if readIMG() throw an exception if the file cannot be found.
   */
  @Test
  public void readIMGThrowsExceptionUnfoundFile() {
    try {
      ImageClass model = ImageUtil.readIMG("res/chicken.png");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot find file or format not supported.", e.getMessage());
    }
  }

  /**
   * Test if readIMG() throw an exception if the filename is null.
   */
  @Test
  public void readIMGThrowsExceptionNullFileName() {
    try {
      ImageClass model = ImageUtil.readPPM(null);
    } catch (IllegalArgumentException e) {
      assertEquals("The filename cannot be null.", e.getMessage());
    }
  }
}