package command;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * This class represents a change name command which allows the user to change the name an image
 * which is previously loaded to the program to a new name.
 */
public class ChangeNameCommand implements ImageProcessingCommand {
  private final String oldName;
  private final String newName;

  /**
   * Constructs a new change name command.
   *
   * @param oldName the current name which an image which is loaded to the program is referred to
   * @param newName the new name of the image to change to
   * @throws IllegalArgumentException if either of the arguments is null
   */
  public ChangeNameCommand(String oldName, String newName) throws IllegalArgumentException {
    if (oldName == null || newName == null) {
      throw new IllegalArgumentException("The arguments cannot be null. ");
    }
    this.oldName = oldName;
    this.newName = newName;
  }

  @Override
  public ImageProcessingModel execute(ImageProcessingModel model) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This method is not supported by this command object.");
  }

  @Override
  public void execute(ImageProcessingView view) throws IllegalArgumentException {
    if (view == null) {
      throw new IllegalArgumentException("The view cannot be null");
    }
    view.changeName(this.oldName, this.newName);
  }
}