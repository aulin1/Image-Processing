package command;

import model.ImageProcessingModel;

/**
 * This class represents a luma component command.
 */
public class LumaCommand implements ImageProcessingCommand {

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) {
    ImageProcessingModel processed = model.returnLumaImage();
    return processed;
  }
}
