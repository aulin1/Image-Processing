package command;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * This class represents a green component command.
 */
public class GreenCompCommand implements ImageProcessingCommand {

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) {
    ImageProcessingModel processed = model.returnGreenImage();
    return processed;
  }

  @Override
  public void execute(ImageProcessingView view) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This method is not supported by this command object.");
  }
}
