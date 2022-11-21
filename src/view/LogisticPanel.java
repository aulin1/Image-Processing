package view;

import image.ImageClass;

/**
 * This interface contains all methods which a logistic panel is required to implement.
 */
public interface LogisticPanel extends DisplayedPanel {
  /**
   * Renders the provided log at the appropriate panel.
   *
   * @param text the text to be rendered in the log
   */
  void renderLog(String text);

  /**
   * Allows the histogram to get updated with a new image.
   *
   * @param image the image that the program is now displaying.
   * */
  void changeLogistics(ImageClass image);
}
