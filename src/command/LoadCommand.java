package command;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * This class represents a command that loads in an image.
 */
public class LoadCommand implements ImageProcessingCommand {
  private final String imagePath;
  private final String imageName;

  /**
   * Constructs a new load command.
   *
   * @param imagePath the path of the image to be load to the program
   * @param imageName the name of the image to be referred by the program
   * @throws IllegalArgumentException if any of the arguments are null
   */
  public LoadCommand(String imagePath, String imageName) throws IllegalArgumentException {
    if (imagePath == null || imageName == null) {
      throw new IllegalArgumentException("The arguments cannot be null. ");
    }
    this.imagePath = imagePath;
    this.imageName = imageName;
  }


  @Override
  public ImageProcessingModel execute(ImageProcessingModel model)
          throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This method is not supported by this command object.");
  }

  @Override
  public void execute(ImageProcessingView view) throws IllegalArgumentException {
    if (view == null) {
      throw new IllegalArgumentException("The view cannot be null");
    }

    view.loadImage(this.imagePath, this.imageName);
  }
}