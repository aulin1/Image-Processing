package command;

/**
 * A class that represents a command that puts a Gaussian filter on an image.
 * */
public class GaussianBlurCommand extends BlurSharpenCommand{
  @Override
  double[][] getWeightMatrix() {
    return new double[][]{{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125}, {0.0625, 0.125, 0.0625}};
  }
}
