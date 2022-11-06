package command;

import static java.lang.Math.max;

/**
 * This class represents a command that creates an greyscale image based on the value of an image.
 */
public class ValueCommand extends GreyscaleCommand {

  @Override
  int getCorrectValue(int[][][] image, int row, int col) {
    return max(max(image[row][col][0], image[row][col][1]),
            image[row][col][2]);
  }
}