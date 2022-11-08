package command;

/**
 * A class that represents a command that can sharpen an image.
 * */
public class ImageSharpenCommand extends BlurSharpenCommand{
  @Override
  double[][] getWeightMatrix() {
    return new double[][]{{-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125}, {-0.125, 0.25, 1.0, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}};
  }
}
