package command;

import model.ImageProcessingModel;

/**
 * This class represents an intensity component command.
 */
public class IntensityCommand implements ImageProcessingCommand {

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) {
    ImageProcessingModel processed = model.returnIntensityImage();
    return processed;
  }
}
