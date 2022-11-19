package controller;

import image.ImageClass;
import model.ImageProcessingModel;

/**
 * This class represents a change name command which allows the user to change the name an image
 * which is previously loaded to the program to a new name.
 */
public class ChangeNameCommand implements ModelCommand {
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
  public ImageClass execute(ImageProcessingModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("The model cannot be null");
    }
    model.changeName(this.oldName, this.newName);
    return model.getImage(this.newName);
  }
}