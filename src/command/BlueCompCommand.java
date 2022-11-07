package command;

/**
 * This class represents a command that returns a greyscale image based on the blue colors of
 * an image.
 */
public class BlueCompCommand extends ColorTransformCommand {

  @Override
  protected double[][] getColorMatrix() {
    return new double[][]{{0, 0, 1}, {0, 0, 1}, {0, 0, 1}};
  }
}