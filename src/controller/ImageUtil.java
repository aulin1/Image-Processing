package controller;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import image.ImageClass;
import image.ImageClassImpl;


/**
 * This class contains utility methods to read a PPM image from file and simply print its
 * contents.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file
   * @return an ImageProcessingModel representation of the loaded image
   * @throws IllegalArgumentException if the file cannot be found, the file is not a PPM file or
   * the file name is null
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

    ImageClass model = new ImageClassImpl(imageBoard, maxValue);
    return model;
  }

  /**
   * Read an image file in the supported format and print the colors.
   *
   * @param filename the path of the file
   * @return an ImageProcessingModel representation of the loaded image
   * @throws IllegalArgumentException if the file cannot be found or if the file is not a PPM file
   */
  public static ImageClass readIMG(String filename) throws IllegalArgumentException {
    ImageClass model;

    if (filename == null) {
      throw new IllegalArgumentException("The filename cannot be null.");
    }
    // if file is a ppm, use the previous reader
    String[] format = filename.split("\\.");
    if (format[1].equals("ppm")) {
      model = readPPM(filename);
      return model;
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
        int color = buffImg.getRGB(j, i); //24 bits representation of the ARGB values
        int b = color & 0xff; // last 8 bits
        int g = (color >> 8) & 0xff; // second to last 8 bits
        int r = (color >> 16) & 0xff; // second from top 8 bits
        imageBoard[i][j][0] = r;
        imageBoard[i][j][1] = g;
        imageBoard[i][j][2] = b;
      }
    }

    model = new ImageClassImpl(imageBoard, maxValue);
    return model;
  }
}

