package model;

/**
 * This class represents a command that returns a greyscale image on an image's luma.
 */
public class LumaCommand extends ColorTransformCommand {

  @Override
  protected double[][] getColorMatrix() {
    return new double[][]{{0.2126, 0.7152, 0.0722},{0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}};
  }
}