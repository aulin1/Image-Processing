package command;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * This class represents a red component command.
 */
public class RedCompCommand implements ImageProcessingCommand {

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("The model cannot be null");
    }

    ImageProcessingModel processed = model.returnRedImage();
    return processed;
  }

  @Override
  public void execute(ImageProcessingView view) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This method is not supported by this command object.");
  }
}
