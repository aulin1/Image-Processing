package model;

/**
 * This class represents a red component command.
 */
public class RedCompCommand extends ColorTransformCommand {
  @Override
  protected double[][] getColorMatrix() {
    return new double[][]{{1, 0, 0},{1, 0, 0},{1, 0, 0}};
  }
}