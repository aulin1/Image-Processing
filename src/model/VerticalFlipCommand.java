package model;

import image.ImageClass;
import image.ImageClassImpl;
import model.ImageProcessingModel;

/**
 * This class represents a command that flips an image vertically (across a horizontal axis).
 */
public class VerticalFlipCommand implements ImageProcessingCommand {

  @Override
  public ImageClass execute(ImageClass model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("The model cannot be null");
    }
    int[][][] img = new int[model.getHeight()][model.getWidth()][3];
    for (int i = 0; i <= model.getHeight() / 2; i++) {
      for (int j = 0; j < model.getWidth(); j++) {
        img[i][j] = model.getImage()[model.getHeight() - 1 - i][j];
        img[model.getHeight() - 1 - i][j] = model.getImage()[i][j];
      }
    }
    return new ImageClassImpl(img, model.getMax());
  }

  @Override
  public void execute(ImageProcessingModel view) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This method is not supported by this command object.");
  }
}