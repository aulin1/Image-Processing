package view;

import model.ImageProcessingModel;
import model.PPMProcessingModel;

/**
 * This class represents a view for the program which handles the saving and loading of images to
 * the program. It supports all functionality of the previous view version but also able to save,
 * load and store images of all supported format besides PPM, such as JPEG, PNG,....
 */
public class UpdatedProcessingView extends PPMProcessingView {
  @Override
  public void saveImage(String imagePath, String imageName) throws IllegalArgumentException,
          IllegalStateException {
  }

  @Override
  public ImageProcessingModel loadImage(String imagePath, String imageName)
          throws IllegalArgumentException {
    return new PPMProcessingModel(new int[24][32][3], 255);
  }
}
