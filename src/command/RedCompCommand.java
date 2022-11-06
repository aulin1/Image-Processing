package command;

/**
 * This class represents a red component command.
 */
public class RedCompCommand extends GreyscaleCommand {

  @Override
  int getCorrectValue(int[][][] image, int row, int col) {
    return image[row][col][0];
  }
}