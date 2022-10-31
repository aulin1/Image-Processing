package command;

import model.ImageProcessingModel;

/**
 * This class represents a value component command.
 */
public class ValueCommand implements ImageProcessingCommand {

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) {
    ImageProcessingModel processed = model.returnValueImage();
    return processed;
  }
}
