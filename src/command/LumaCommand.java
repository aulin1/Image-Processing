package command;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * This class represents a luma component command.
 */
public class LumaCommand implements ImageProcessingCommand {

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) {
    ImageProcessingModel processed = model.returnLumaImage();
    return processed;
  }

  @Override
  public void execute(ImageProcessingView view) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This method is not supported by this command oject");
  }
}
