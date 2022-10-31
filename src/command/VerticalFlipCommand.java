package command;

import model.ImageProcessingModel;

/**
 * This class represents a vertical flip command.
 */
public class VerticalFlipCommand implements ImageProcessingCommand {

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) {
    ImageProcessingModel processed = model.flipImageVertically();
    return processed;
  }
}
