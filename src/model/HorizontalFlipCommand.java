package model;

import image.ImageClass;
import image.ImageClassImpl;

/**
 * This class represents a command the flips an image horizontally (across a vertical axis).
 */
public class HorizontalFlipCommand implements ImageProcessingCommand {

  @Override
  public ImageClass execute(ImageClass model) throws IllegalArgumentException {
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
    return new ImageClassImpl(img, model.getMax());
  }
}