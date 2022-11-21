package model;

import java.util.Map;


import controller.ImageUtil;
import image.ImageClass;

import static controller.ImageUtil.saveIMG;

/**
 * This class represents a model for the program which handles the saving and loading of images to
 * the program. It supports all functionality of the previous view version but also able to save,
 * load and store images of all supported format besides PPM, such as JPEG, PNG,....
 */
public class UpdatedProcessingModel extends PPMProcessingModel {

  /**
   * Basic constructor for UpdatedProcessingModel.
   */
  public UpdatedProcessingModel() {
    super();
  }

  /**
   * Another constructor for the updated model.
   */
  public UpdatedProcessingModel(Map<String, ImageClass> map) {
    super(map);
  }

  @Override
  public void saveImage(String imagePath, String imageName)
          throws IllegalArgumentException, IllegalStateException {
    if (imagePath == null || imageName == null) {
      throw new IllegalArgumentException("The fields cannot be null.");
    }

    String[] imagePathParsed = imagePath.split("\\.");
    String formatName = imagePathParsed[imagePathParsed.length - 1];
    if (formatName.equals("ppm")) {
      super.saveImage(imagePath, imageName);
    }

    // buffered image then ImageIO write
    ImageClass model = this.getImage(imageName);
    saveIMG(imagePath, model, formatName);
  }

  @Override
  public ImageClass loadImage(String imagePath, String imageName)
          throws IllegalArgumentException {
    ImageClass model = ImageUtil.readIMG(imagePath);
    this.storeImage(imageName, model);
    return model;
  }
}
