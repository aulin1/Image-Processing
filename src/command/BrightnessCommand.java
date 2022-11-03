package command;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * This class represents a brightness command.
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

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("The model cannot be null");
    }

    if (this.factor == 0) {
      return model;
    } else {
      ImageProcessingModel processed = model.changeBrightness(this.factor);
      return processed;
    }
  }

  @Override
  public void execute(ImageProcessingView view) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This method is not supported by this command object.");
  }
}
