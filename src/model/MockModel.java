package model;

import image.ImageClass;

/**
 * This class represents a mock view for testing the controller.
 */
public class MockModel implements ImageProcessingModel {
  StringBuilder receivedInput;

  /**
   * Constructor for mock view.
   *
   * @param receivedInput the stringBuilder use to view the input received by the view
   * @throws IllegalArgumentException if received input stringbuilder is null
   */
  public MockModel(StringBuilder receivedInput) throws IllegalArgumentException {
    if (receivedInput == null) {
      throw new IllegalArgumentException("The received input stringbuilder cannot be null.");
    }
    this.receivedInput = receivedInput;
  }

  @Override
  public ImageClass loadImage(String imagePath, String imageName) throws IllegalArgumentException {
    this.receivedInput = this.receivedInput.append(imagePath).append(" ").append(imageName);
    return null;
  }

  @Override
  public void saveImage(String imagePath, String imageName) throws IllegalStateException {
    this.receivedInput = this.receivedInput.append(imagePath).append(" ").append(imageName);
  }

  @Override
  public void storeImage(String imageName, ImageClass model) {
    this.receivedInput = this.receivedInput.append(imageName).append(" ").append(model);
  }

  @Override
  public void changeName(String oldName, String newName) throws IllegalArgumentException {
    this.receivedInput = this.receivedInput.append(oldName).append(" ").append(newName);
  }

  @Override
  public ImageClass getImage(String imageName) {
    this.receivedInput = this.receivedInput.append(imageName);
    return null;
  }
}
