package command;

/**
 * This class represents a command that returns a greyscale image based on the blue colors of
 * an image.
 */
public class BlueCompCommand extends GreyscaleCommand {

  @Override
  int getCorrectValue(int[][][] image, int row, int col) {
    return image[row][col][2];
  }
}