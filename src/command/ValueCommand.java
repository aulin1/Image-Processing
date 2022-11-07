package command;

import static java.lang.Math.max;

/**
 * This class represents a command that creates an greyscale image based on the value of an image.
 */
public class ValueCommand extends FilterCommand {

  @Override
  protected int[] getCorrectValues(int[][][] image, int row, int col) {
    int max = max(max(image[row][col][0], image[row][col][1]),
            image[row][col][2]);
    return new int[]{max, max, max};
  }
}