package command;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * This class represents a load command.
 */
public class LoadCommand implements ImageProcessingCommand {
  private final String imagePath;
  private final String imageName;

  /**
   * Constructs a new load command.
   *
   * @param imagePath the path in which the program will load the image from
   * @param imageName the name which the program refers to this image
   * @throws IllegalArgumentException if either arguments are null
   */
  public LoadCommand(String imagePath, String imageName) throws IllegalArgumentException {
    if (imagePath == null || imageName == null) {
      throw new IllegalArgumentException("The arguments cannot be null");
    }
    this.imagePath = imagePath;
    this.imageName = imageName;
  }

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This method is not supported by this command object");
  }

  @Override
  public void execute(ImageProcessingView view) throws IllegalArgumentException {
    view.loadImage(this.imagePath, this.imageName);
  }
}
