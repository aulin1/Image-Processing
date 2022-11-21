import org.junit.Test;

import java.awt.image.BufferedImage;
import java.util.Scanner;

import controller.ImageUtil;
import image.ImageClass;
import image.ImageClassImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This class represents tests for ImageUtil.
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

  String scriptTXT = "load Pixel.png pixel change-name pixel pixelOG red-component pixelOG "
          + "pixelRed green-component pixelOG pixelGreen blue-component pixelOG pixelBlue "
          + "value pixelRed pixelRedValue luma pixelBlue pixelBlueLuma intensity pixelGreen "
          + "pixelGreenIntensity horizontal-flip pixelOG pixelHorizontal vertical-flip pixelOG "
          + "pixelVertical brighten pixelRedValue pixelRedValueBrightened 10 brighten pixelOG "
          + "pixelDarken -20 brighten pixelOG pixelNoChange 0 gaussian-blur pixelBlueLuma "
          + "pixelBlueLumaBlur sharpen pixelGreen pixelGreenSharpen greyscale pixelOG "
          + "pixelGreyscale sepia pixelOG pixelSepia save PGI.bmp pixelGreenIntensity q";
  String scriptTXT2 = "load\nPixel.png\npixel\nchange-name\npixel\npixelOG\nred-component\npixelOG\n"
          + "pixelRed\ngreen-component\npixelOG\npixelGreen\nblue-component\npixelOG\npixelBlue\n"
          + "value\npixelRed\npixelRedValue\nluma\npixelBlue\npixelBlueLuma\nintensity\npixelGreen\n"
          + "pixelGreenIntensity\nhorizontal-flip\npixelOG\npixelHorizontal\nvertical-flip\npixelOG\n"
          + "pixelVertical\nbrighten\npixelRedValue\npixelRedValueBrightened\n10\nbrighten\npixelOG\n"
          + "pixelDarken\n-20\nbrighten\npixelOG\npixelNoChange\n0\ngaussian-blur\npixelBlueLuma\n"
          + "pixelBlueLumaBlur\nsharpen\npixelGreen\npixelGreenSharpen\ngreyscale\npixelOG\n"
          + "pixelGreyscale\nsepia\npixelOG\npixelSepia\nsave\nPGI.bmp\npixelGreenIntensity\nq";

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

  /**
   * Tests if parseTXT() throws an exception if the filepath is null.
   * */
  @Test
  public void testParseTXTNullFilepath(){
    try{
      ImageUtil.parseTXT(null);
    } catch (IllegalArgumentException e){
      assertEquals("Path cannot be null.", e.getMessage());
    }
  }

  /**
   * Tests if parseTXT() throws an exception if the filepath isn't a txt file.
   * */
  @Test
  public void testParseTXTNotTXT(){
    try{
      ImageUtil.parseTXT("res/Pixel.bmp");
    } catch (IllegalArgumentException e){
      assertEquals("The provided file is not a txt file.", e.getMessage());
    }
  }

  /**
   * Tests if parseTXT() throws an exception if the filepath doesn't exist.
   * */
  @Test
  public void testParseTXTNotExist(){
    try{
      ImageUtil.parseTXT("res/file.txt");
    } catch (IllegalStateException e){
      assertEquals("Cannot find file.", e.getMessage());
    }
  }

  /**
   * Tests if parseTXT() reads a text file correctly with spaces.
   * */
  @Test
  public void testParseTXT(){
    Readable txt = ImageUtil.parseTXT("res/exampleScript.txt");
    Scanner sc = new Scanner(txt);
    String[] splitText = scriptTXT.split(" ");
    for(int i = 0; i < splitText.length; i++){
      assertEquals(splitText[i], sc.next());
    }
  }

  /**
   * Tests if parseTXT() reads a text file correctly with new lines.
   * */
  @Test
  public void testParseTXT2(){
    Readable txt = ImageUtil.parseTXT("res/exampleScript.txt");
    Scanner sc = new Scanner(txt);
    String[] splitText = scriptTXT2.split("\n");
    for(int i = 0; i < splitText.length; i++){
      assertEquals(splitText[i], sc.next());
    }
  }

  /**
   * Tests if getBuffImage throws an IllegalArgumentException if the image is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetBuffImage(){
    ImageUtil.getBuffImage(null);
  }

  /**
   * Tests if getBuffImage gives a result.
   * */
  @Test
  public void testGetBuffImage2(){
    ImageClass img = new ImageClassImpl(new int[][][]{{{0, 0, 0}}}, 10);
    BufferedImage test = ImageUtil.getBuffImage(img);
    assertNotNull(test);
  }
}