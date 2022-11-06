package command;

/**
 * This class represents a command that returns a greyscale image on an image's luma.
 */
public class LumaCommand extends GreyscaleCommand {

  @Override
  int getCorrectValue(int[][][] image, int row, int col) {
    return (int) Math.round(0.2126 * image[row][col][0]
            + 0.7152 * image[row][col][1]
            + 0.0722 * image[row][col][2]);
  }
}