package command;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * This class represents a change name command.
 */
public class ChangeNameCommand implements ImageProcessingCommand {
  private final String currentName;
  private final String newName;

  /**
   * Constructs a new change name command.
   *
   * @param currentName the current name which the program refers to the image
   * @param newName the new name which the program will refers to the image
   * @throws IllegalArgumentException if either arguments are null
   */
  public ChangeNameCommand(String currentName, String newName) throws IllegalArgumentException {
    if (currentName == null || newName == null) {
      throw new IllegalArgumentException("The arguments cannot be null");
    }
    this.currentName = currentName;
    this.newName = newName;
  }

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This method is not supported by this command object");
  }

  @Override
  public void execute(ImageProcessingView view) throws IllegalArgumentException {
    view.changeName(this.currentName, this.newName);
  }
}
