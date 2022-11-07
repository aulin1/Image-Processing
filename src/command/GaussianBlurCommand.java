package command;

/**
 * A class that represents a command that puts a Gaussian filter on an image.
 * */
public class GaussianBlurCommand extends BlurSharpenCommand{
  @Override
  double[][] getWeightMatrix() {
    return new double[][]{{1.0/16, 1.0/8, 1.0/16}, {1.0/8, 1.0/4, 1.0/8}, {1.0/16, 1.0/8, 1.0/16}};
  }
}
