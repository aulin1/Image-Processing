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
   * @throws IllegalArgumentException if the factor is negative
   */
  public BrightnessCommand(int factor) throws IllegalArgumentException {
    if (factor < 0) {
      throw new IllegalArgumentException("The factor cannot be negative. ");
    }
    this.factor = factor;
  }

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) {
    ImageProcessingModel processed = model.brighten(this.factor);
    return processed;
  }
}
