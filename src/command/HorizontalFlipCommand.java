package command;

import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.ImageProcessingView;

/**
 * This class represents a command the flips an image horizontally (across a vertical axis).
 */
public class HorizontalFlipCommand implements ImageProcessingCommand {

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("The model cannot be null");
    }
    int[][][] img = new int[model.getHeight()][model.getWidth()][3];
    for (int i = 0; i < model.getHeight(); i++) {
      for (int j = 0; j <= model.getWidth() / 2; j++) {
        img[i][j] = model.getImage()[i][model.getWidth() - 1 - j];
        img[i][model.getWidth() - 1 - j] = model.getImage()[i][j];
      }
    }
    return new ImageProcessingModelImpl(img, model.getMax());
  }

  @Override
  public void execute(ImageProcessingView view) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This method is not supported by this command object.");
  }
}