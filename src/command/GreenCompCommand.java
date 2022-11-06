package command;

/**
 * This class represents a green component command.
 */
public class GreenCompCommand extends GreyscaleCommand {

  @Override
  int getCorrectValue(int[][][] image, int row, int col) {
    return image[row][col][1];
  }
}