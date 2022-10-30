package command;

import model.ImageProcessingModel;

/**
 * This class represents a load command.
 */
public class RedCompCommand implements ImageProcessingCommand {
  private final String imageName;
  private final String destImageName;

  /**
   * Constructs a new function object LoadCommand.
   *
   * @param imageName the name of the image to be processed
   * @param destImageName the destination name of the processed image
   * @throws IllegalArgumentException if either arguments are null
   */
  public RedCompCommand(String imageName, String destImageName) {
    if (destImageName == null || imageName == null) {
      throw new IllegalArgumentException("Arguments cannot be null. ");
    }
    this.imageName = imageName;
    this.destImageName = destImageName;
  }


  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) {
    return model.returnRedImage();
  }
}
