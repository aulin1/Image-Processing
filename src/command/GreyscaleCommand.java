package command;

import model.ImageProcessingModel;
import model.PPMProcessingModel;
import view.ImageProcessingView;

/**
 * A command that gets a greyscale image from another image.
 * */
abstract class GreyscaleCommand implements ImageProcessingCommand{

  /**
   * Gets the correct value for a pixel in the image.
   *
   * @param image the image that is getting turned to greyscale.
   * @param row the row of the pixel.
   * @param col the column of the pixel.
   * @returns the value that the pixel should have.
   * */
  abstract int getCorrectValue(int[][][] image, int row, int col);

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) {
    if(model == null){
      throw new IllegalArgumentException("The model cannot be null");
    }
    int[][][] img = new int[model.getHeight()][model.getWidth()][3];
    for (int i = 0; i < model.getHeight(); i++) {
      for (int j = 0; j < model.getWidth(); j++) {
        img[i][j][0] = getCorrectValue(model.getImage(), i, j);
        img[i][j][1] = getCorrectValue(model.getImage(), i, j);
        img[i][j][2] = getCorrectValue(model.getImage(), i, j);
      }
    }
    return new PPMProcessingModel(img, model.getMax());
  }

  @Override
  public void execute(ImageProcessingView view) {
    throw new UnsupportedOperationException("This method is not supported by this command object.");
  }
}
