package command;

import model.ImageProcessingModel;

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
  public BrightnessCommand(int factor) throws IllegalArgumentException {
    this.factor = factor;
  }

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) {
    if (this.factor == 0) {
      return model;
    } else {
      ImageProcessingModel processed = model.brighten(this.factor); //TODO: change after fixing
                                                                    // to changeBrightness
      return processed;
    }
  }
}
