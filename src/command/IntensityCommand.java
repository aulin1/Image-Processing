package command;

/**
 * This class represents a command that returns a greyscale image based on the intensity of an
 * image.
 */
public class IntensityCommand extends GreyscaleCommand {

  @Override
  int getCorrectValue(int[][][] image, int row, int col) {
    return (image[row][col][0] + image[row][col][1]
            + image[row][col][2]) / 3;
  }
}