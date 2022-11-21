package view;

import javax.swing.*;

import image.ImageClass;

/**
 * This interface contains methods which panels that handle images are required to implement.
 */
public interface ImagePanel extends DisplayedPanel {
  /**
   * Set the image field in the panel to the provided image.
   *
   * @param image the image to be set
   */
  void setImage(ImageClass image);
}
