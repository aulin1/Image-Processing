package command;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * This class represents a blue component command.
 */
public class BlueCompCommand implements ImageProcessingCommand {

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) {
    ImageProcessingModel processed = model.returnBlueImage();
    return processed;
  }

  @Override
  public void execute(ImageProcessingView view) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This method is not supported by this command object.");
  }
}
