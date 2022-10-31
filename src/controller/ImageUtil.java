package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.ImageProcessingModel;
import model.PPMProcessingModel;


/**
 * This class contains utility methods to read a PPM image from file and simply print its
 * contents.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file
   * @throws IllegalArgumentException if the file cannot be found or if the file is not a PPM file
   */
  public static ImageProcessingModel readPPM(String filename) throws IllegalArgumentException {
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

    ImageProcessingModel model = new PPMProcessingModel(imageBoard, maxValue, filename);
    return model;
  }
}

