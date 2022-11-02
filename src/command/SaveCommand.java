package command;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * This class represents a save command.
 */
public class SaveCommand implements ImageProcessingCommand {
  private final String imagePath;
  private final String imageName;

  /**
   * Constructs a new save command.
   *
   * @param imagePath the path in which the program will save the image to
   * @param imageName the name which the program refer to this image to be saved
   * @throws IllegalArgumentException if either arguments are null
   */
  public SaveCommand(String imagePath, String imageName) throws IllegalArgumentException {
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
  public void execute(ImageProcessingView view) throws IllegalStateException {
    view.saveImage(this.imagePath, this.imageName);
  }
}
