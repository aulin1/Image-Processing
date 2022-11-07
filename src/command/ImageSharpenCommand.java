package command;

/**
 * A class that represents a command that can sharpen an image.
 * */
public class ImageSharpenCommand extends BlurSharpenCommand{
  @Override
  double[][] getWeightMatrix() {
    return new double[][]{{-1.0/8, -1.0/8, -1.0/8, -1.0/8, -1.0/8},
            {-1.0/8, 1.0/4, 1.0/4, 1.0/4, -1.0/8}, {-1.0/8, 1.0/4, 1, 1.0/5, -1.0/8},
            {-1.0/8, 1.0/4, 1.0/4, 1.0/4, -1.0/8},
            {-1.0/8, -1.0/8, -1.0/8, -1.0/8, -1.0/8}};
  }
}
