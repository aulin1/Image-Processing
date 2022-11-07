package command;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * This class represents a command object which saves a pre-existing image model in the program
 * to the user's machine.
 */
public class SaveCommand implements ImageProcessingCommand {
  private final String filePath;
  private final String fileName;

  /**
   * Constructs a new save command which saves the image to the designated form.
   *
   * @param filePath the path where the image will be saved to
   * @param fileName the name of the image as it is referred to in the program which the user
   *                 wants to save
   * @throws IllegalArgumentException if either of the arguments are null
   */
  public SaveCommand(String filePath, String fileName) throws IllegalArgumentException {
    if (filePath == null || fileName == null) {
      throw new IllegalArgumentException("The arguments cannot be null. ");
    }
    this.filePath = filePath;
    this.fileName = fileName;
  }

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) {
    return null; //TODO: implement this.
  }

  @Override
  public void execute(ImageProcessingView view) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This method is not supported by this command object.");
  }
}
