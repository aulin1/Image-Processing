package controller;

import model.ImageProcessingModel;

/**
 * This class represents a command that loads in a PPM image.
 */
public class LoadCommand implements ModelCommand {
  private final String imagePath;
  private final String imageName;

  /**
   * Constructs a new load command for loading PPM image.
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
  public void execute(ImageProcessingModel view) throws IllegalArgumentException {
    if (view == null) {
      throw new IllegalArgumentException("The view cannot be null");
    }

    view.loadImage(this.imagePath, this.imageName);
  }
}