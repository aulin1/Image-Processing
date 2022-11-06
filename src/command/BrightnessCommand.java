package command;

import model.ImageProcessingModel;
import model.PPMProcessingModel;
import view.ImageProcessingView;

/**
 * This class represents a command that brightens or darkens an image.
 */
public class BrightnessCommand implements ImageProcessingCommand {
  private final int factor;

  /**
   * Constructs a new brightness command.
   *
   * @param factor the brightness factor to process the image
   */
  public BrightnessCommand(int factor) {
    this.factor = factor;
  }

  /**
   * A helper function to check the limits for the changeBrightness function.
   *
   * @param max the maximum value that is allowed
   * @param value the integer to be checked.
   * @return the result within the limits set.
   */
  private int checkLimits(int max, int value){
    if (value < 0) {
      return 0;
    }
    if (value > max) {
      return max;
    }
    return value;
  }

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("The model cannot be null");
    }
    int[][][] img = new int[model.getWidth()][model.getHeight()][3];
    for (int i = 0; i < model.getWidth(); i++) {
      for (int j = 0; j < model.getHeight(); j++) {
        img[i][j][0] = this.checkLimits(model.getMax(), model.getImage()[i][j][0] + factor);
        img[i][j][1] = this.checkLimits(model.getMax(), model.getImage()[i][j][1] + factor);
        img[i][j][2] = this.checkLimits(model.getMax(), model.getImage()[i][j][2] + factor);
      }
    }
    return new PPMProcessingModel(img, model.getMax());
  }

  @Override
  public void execute(ImageProcessingView view) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This method is not supported by this command object.");
  }
}