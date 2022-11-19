package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

import image.ImageClass;
import image.ImageClassImpl;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;


/**
 * This class contains utility methods to read, save, and load various files.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file
   * @return an ImageProcessingModel representation of the loaded image
   * @throws IllegalArgumentException if the file cannot be found, the file is not a PPM file or
   *                                  the file name is null
   */
  public static ImageClass readPPM(String filename) throws IllegalArgumentException {
    if (filename == null) {
      throw new IllegalArgumentException("The filename cannot be null.");
    }

    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Cannot find file! ");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    int[][][] imageBoard = new int[height][width][3];

    for (int i = 0; i < height; i++) { // row
      for (int j = 0; j < width; j++) { // col
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        imageBoard[i][j][0] = r;
        imageBoard[i][j][1] = g;
        imageBoard[i][j][2] = b;
      }
    }

    ImageClass img = new ImageClassImpl(imageBoard, maxValue);
    return img;
  }

  /**
   * Read an image file in the supported format and print the colors.
   *
   * @param filename the path of the file
   * @return an ImageProcessingModel representation of the loaded image
   * @throws IllegalArgumentException if the file cannot be found or if the file is not a PPM file
   */
  public static ImageClass readIMG(String filename) throws IllegalArgumentException {
    ImageClass img;

    if (filename == null) {
      throw new IllegalArgumentException("The filename cannot be null.");
    }
    // if file is a ppm, use the previous reader
    String[] format = filename.split("\\.");
    if (format[1].equals("ppm")) {
      img = readPPM(filename);
      return img;
    }

    BufferedImage buffImg;

    try {
      FileInputStream fileStream = new FileInputStream(filename);
      buffImg = ImageIO.read(fileStream);
    } catch (IOException e) {
      throw new IllegalArgumentException("Cannot find file or format not supported.");
    }

    int width = buffImg.getWidth();
    int height = buffImg.getHeight();
    int maxValue = 255;

    int[][][] imageBoard = new int[height][width][3];

    for (int i = 0; i < height; i++) { // row
      for (int j = 0; j < width; j++) { // col
        Color color = new Color(buffImg.getRGB(j, i));
        imageBoard[i][j][0] = color.getRed();
        imageBoard[i][j][1] = color.getGreen();
        imageBoard[i][j][2] = color.getBlue();
      }
    }

    img = new ImageClassImpl(imageBoard, maxValue);
    return img;
  }

  /**
   * Saves a PPM image to a destination.
   *
   * @param filepath the path to save the PPM at.
   * @param image the image to be saved.
   * @throws IllegalArgumentException if any of the params are null.
   * @throws IllegalStateException if the image is unable to be saved at the location.
   * */
  public static void savePPM(String filepath, ImageClass image) throws IllegalArgumentException,
          IllegalStateException {
    if (filepath == null || image == null) {
      throw new IllegalArgumentException("The arguments cannot be null.");
    }

    String[] imagePathParsed = filepath.split("\\.");
    if (!imagePathParsed[imagePathParsed.length - 1].equals("ppm")) {
      throw new IllegalArgumentException("The provided filepath indicate the export of a ppm file"
              + ".");
    }

    try {
      PrintWriter outfile = new PrintWriter(filepath);
      outfile.println("P3");
      outfile.println("# Image created by Trang Do and Audrey Lin's program");
      outfile.println(image.getWidth() + " " + image.getHeight());
      outfile.println(255);

      int[][][] imageBoard = image.getImage();
      for (int r = 0; r < image.getHeight(); r++) {
        for (int c = 0; c < image.getWidth(); c++) {
          outfile.println(imageBoard[r][c][0]); // print red value
          outfile.println(imageBoard[r][c][1]); // print green value
          outfile.println(imageBoard[r][c][2]); // print blue value
        }
      }
      outfile.close();
    } catch (Exception e) {
      throw new IllegalStateException("Unable to save file to destination. ");
    }
  }

  /**
   * Saves an image of a supported format to a destination.
   *
   * @param filepath the path to save the PPM at.
   * @param image the image to be saved.
   * @throws IllegalArgumentException if any of the params are null.
   * @throws IllegalStateException if the image is unable to be saved at the location.
   * */
  public static void saveIMG(String filepath, ImageClass image, String formatName)
          throws IllegalArgumentException, IllegalStateException {
    if(filepath == null || image == null || formatName == null){
      throw new IllegalArgumentException("The arguments cannot be null.");
    }

    BufferedImage buffImg = getBuffImage(image);

    try {
      ImageOutputStream imgOutStream = new FileImageOutputStream(new File(filepath));
      ImageIO.write(buffImg, formatName, imgOutStream);
    } catch (IOException e) {
      throw new IllegalStateException("Unable to save file to destination. ");
    }
  }

  /**
   * Generate a buffered image from the provided object from the ImageClass.
   *
   * @param image the image to be converted to a buffered image
   * @return the buffered image version of the image provided
   * @throws IllegalArgumentException if the provided image is null
   */
  public static BufferedImage getBuffImage(ImageClass image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("The argument cannot be null"); //TODO: test
    }

    int[][][] imageBoard = image.getImage();
    BufferedImage buffImg = new BufferedImage(image.getWidth(), image.getHeight(), TYPE_INT_RGB);

    // transfer rgb by pixels from model to the buffered img
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        int r = imageBoard[row][col][0]; // print red value
        int rg = (r << 8) + imageBoard[row][col][1]; // print green value
        int rgb = (rg << 8) + imageBoard[row][col][2]; // print blue value
        buffImg.setRGB(col, row, rgb); //pos-x, pos-y, 8 bit rep of rgb
      }
    }
    return buffImg;
  }

  /**
   * Reads a script file and returns
   *
   * @param filepath the path of the script file.
   * @return a Readable of the commands.
   * @throws IllegalArgumentException if the filepath is null, or is not a text file.
   * @throws IllegalStateException if it cannot find the file.
   * */
  public static Readable parseTXT(String filepath) throws IllegalArgumentException,
          IllegalStateException {
    if(filepath == null){
      throw new IllegalArgumentException("Path cannot be null.");
    }
    String[] textArr = filepath.split("\\.");
    if (!textArr[1].equals("txt")) {
      throw new IllegalArgumentException("The provided file is not a txt file.");
    }
    try {
      FileInputStream fileInputStream = new FileInputStream(filepath);
      InputStreamReader streamReader = new InputStreamReader(fileInputStream);
      return streamReader;
    } catch(FileNotFoundException e){
      throw new IllegalStateException("Cannot find file.");
    }
  }
}

