package command;

import model.ImageProcessingModel;
import model.PPMProcessingModel;
import view.ImageProcessingView;

/**
 * A command that represents running a filter over an image.
 * */
abstract class FilterCommand implements ImageProcessingCommand{

  /**
   * The maximum a color can be.
   * */
  private int max;

  /**
   * A helper function to check the limits for the changeBrightness function.
   *
   * @param value the integer to be checked.
   * @return the result within the limits set.
   */
  protected int checkLimits(int value){
    if (value < 0) {
      return 0;
    }
    if (value > max) {
      return max;
    }
    return value;
  }

  /**
   * Gets the correct value for a pixel in the image.
   *
   * @param img the image that is getting turned to greyscale.
   * @param row the row of the pixel.
   * @param col the column of the pixel.
   * @returns the values that the pixel should have as an array.
   * */
  abstract int[] getCorrectValues(int[][][] img, int row, int col);

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) {
    if(model == null){
      throw new IllegalArgumentException("The model cannot be null");
    }
    this.max = model.getMax();
    int[][][] img = new int[model.getHeight()][model.getWidth()][3];
    for (int i = 0; i < model.getHeight(); i++) {
      for (int j = 0; j < model.getWidth(); j++) {
        int[] vals = getCorrectValues(model.getImage(), i, j);
        img[i][j][0] = vals[0];
        img[i][j][1] = vals[1];
        img[i][j][2] = vals[2];
      }
    }
    return new PPMProcessingModel(img, model.getMax());
  }

  @Override
  public void execute(ImageProcessingView view) {
    throw new UnsupportedOperationException("This method is not supported by this command object.");
  }
}
