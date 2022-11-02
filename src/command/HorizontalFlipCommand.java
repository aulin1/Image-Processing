package command;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * This class represents a horizontal flip command.
 */
public class HorizontalFlipCommand implements ImageProcessingCommand {

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) {
    ImageProcessingModel processed = model.flipImageHorizontally();
    return processed;
  }

  @Override
  public void execute(ImageProcessingView view) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This method is not supported by this command oject");
  }
}
