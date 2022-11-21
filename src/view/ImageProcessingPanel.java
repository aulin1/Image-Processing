package view;

/**
 * This interface contains all methods which a panel that is responsible for handling image
 * processing interactions are required to implement.
 */
public interface ImageProcessingPanel extends DisplayedPanel {
  /**
   * Set the image name of the image that is currently being processed to the panel.
   *
   * @param imageName the name of the image which is currently being processed
   * @throws IllegalArgumentException if the image name is null
   */
  void setImageName(String imageName) throws IllegalArgumentException;

  /**
   * Get the name of the image which is currently being processed.
   *
   * @return the name of the image being processed
   */
  String getImageName();
}
