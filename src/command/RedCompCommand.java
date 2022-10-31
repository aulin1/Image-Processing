package command;

import model.ImageProcessingModel;

/**
 * This class represents a red component command.
 */
public class RedCompCommand implements ImageProcessingCommand {

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) {
    ImageProcessingModel processed = model.returnRedImage();
    return processed;
  }
}
