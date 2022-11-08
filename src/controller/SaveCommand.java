package controller;

import model.ImageProcessingModel;

/**
 * This class represents a save command.
 */
public class SaveCommand implements ModelCommand {
  private final String filePath;
  private final String fileName;

  /**
   * Constructs a new save command which saves the image in the program to a PPM file.
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
  public void execute(ImageProcessingModel view) throws IllegalStateException,
          IllegalArgumentException {
    if (view == null) {
      throw new IllegalArgumentException("The view cannot be null");
    }

    view.saveImage(this.filePath, this.fileName);
  }
}