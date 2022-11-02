package command;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * This class represents an intensity component command.
 */
public class IntensityCommand implements ImageProcessingCommand {

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) {
    ImageProcessingModel processed = model.returnIntensityImage();
    return processed;
  }

  @Override
  public void execute(ImageProcessingView view) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This method is not supported by this command oject");
  }
}
