package command;

/**
 * A class that represents a command that can transform an image into a Sepia tone.
 * */
public class SepiaToneCommand extends ColorTransformCommand{
  @Override
  double[][] getColorMatrix() {
    return new double[][]{{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}};
  }
}
