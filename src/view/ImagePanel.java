package view;

import image.ImageClass;

public interface ImagePanel extends DisplayedPanel {
  /**
   * Set the image field in the panel to the provided image.
   *
   * @param image the image to be set
   */
  void setImage(ImageClass image);
}
