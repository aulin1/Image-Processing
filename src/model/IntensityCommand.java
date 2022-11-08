package model;

/**
 * This class represents a command that returns a greyscale image based on the intensity of an
 * image.
 */
public class IntensityCommand extends FilterCommand {

  @Override
  protected int[] getCorrectValues(int[][][] image, int row, int col) {
    int avg = (int) Math.round((image[row][col][0] + image[row][col][1]
            + image[row][col][2]) / 3.0);
    return new int[]{avg, avg, avg};
  }
}