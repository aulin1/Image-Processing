package command;

import model.ImageProcessingModel;

/**
 * This class represents a green component command.
 */
public class GreenCompCommand implements ImageProcessingCommand {

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) {
    ImageProcessingModel processed = model.returnGreenImage();
    return processed;
  }
}
