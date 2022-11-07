package command;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * This class represents a load command object which loads a designated image from the user's
 * machine to the program.
 */
public class LoadCommand implements ImageProcessingCommand {
  private final String imagePath;
  private final String imageName;

  /**
   * Constructs a new load command for loading any supported format of image.
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
  public ImageProcessingModel execute(ImageProcessingModel model) {
    return null;
  }

  @Override
  public void execute(ImageProcessingView view) {

  }
}