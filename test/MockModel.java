import image.ImageClass;
import model.ImageProcessingModel;

/**
 * This class represents a mock model for testing the controller.
 */
public class MockModel implements ImageProcessingModel {
  StringBuilder receivedInput;

  /**
   * Constructor for mock model.
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
    this.receivedInput = this.receivedInput.append(imagePath).append(" ")
            .append(imageName).append(" ");
    return null;
  }

  @Override
  public void saveImage(String imagePath, String imageName) throws IllegalStateException {
    this.receivedInput = this.receivedInput.append(imagePath).append(" ")
            .append(imageName).append(" ");
  }

  @Override
  public void storeImage(String imageName, ImageClass model) {
    this.receivedInput = this.receivedInput.append(imageName).append(" ").append(model).append(" ");
  }

  @Override
  public void changeName(String oldName, String newName) throws IllegalArgumentException {
    this.receivedInput = this.receivedInput.append(oldName).append(" ").append(newName).append(" ");
  }

  @Override
  public ImageClass getImage(String imageName) {
    this.receivedInput = this.receivedInput.append(imageName).append(" ");
    return null;
  }
}
