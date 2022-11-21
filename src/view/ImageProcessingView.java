package view;

import controller.IPFeature;
import image.ImageClass;

/**
 * This interface contains all methods in which a view for an image processing program should
 * contain.
 */
public interface ImageProcessingView {
  /**
   * Make the view visible.
   */
  void makeVisible();
  /**
   * Refresh the screen. This is called when the something on the
   * screen is updated, and therefore it must be redrawn.
   */
  void refresh();

  /**
   * Display a message in a suitable area of the GUI.
   * @param message the message to be displayed
   */
  void renderMessage(String message);

  /**
   * Display an image in a suitable are of the GUI.
   *
   * @param image the image to be rendered
   */
  void renderImage(ImageClass image);

  /**
   * Register this feature to the view.
   *
   * @param feature the feature to be registered
   */
  void registerFeature(IPFeature feature);
}
