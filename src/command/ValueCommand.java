package command;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * This class represents a value component command.
 */
public class ValueCommand implements ImageProcessingCommand {

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) {
    ImageProcessingModel processed = model.returnValueImage();
    return processed;
  }

  @Override
  public void execute(ImageProcessingView view) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This method is not supported by this command object.");
  }

}
