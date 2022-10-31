package command;

import model.ImageProcessingModel;

/**
 * This class represents a blue component command.
 */
public class BlueCompCommand implements ImageProcessingCommand {

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) {
    ImageProcessingModel processed = model.returnBlueImage();
    return processed;
  }
}
