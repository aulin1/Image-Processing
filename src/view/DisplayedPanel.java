package view;

import controller.IPFeature;

/**
 * This interface represents a generic panel being displayed on an Image Processing Program and
 * all methods which are required.
 */
public interface DisplayedPanel {
  /**
   * Register the provided feature to this panel to allow the feature to receive it input in the
   * future.
   *
   * @param feature the feature to be registered
   */
  void registerFeature(IPFeature feature);
}
