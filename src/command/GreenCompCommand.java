package command;

/**
 * This class represents a green component command.
 */
public class GreenCompCommand extends ColorTransformCommand {

  @Override
  protected double[][] getColorMatrix() {
    return new double[][]{{0, 1, 0}, {0, 1, 0}, {0, 1, 0}};
  }
}