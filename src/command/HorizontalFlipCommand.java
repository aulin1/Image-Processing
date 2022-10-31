package command;

import model.ImageProcessingModel;

/**
 * This class represents a horizontal flip command.
 */
public class HorizontalFlipCommand implements ImageProcessingCommand {

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) {
    ImageProcessingModel processed = model.flipImageHorizontally();
    return processed;
  }
}
