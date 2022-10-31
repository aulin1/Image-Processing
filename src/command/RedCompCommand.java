package command;

import model.ImageProcessingModel;

/**
 * This class represents a load command.
 */
public class RedCompCommand implements ImageProcessingCommand {

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) {
    ImageProcessingModel processed = model.returnRedImage();
    return processed;
  }
}
