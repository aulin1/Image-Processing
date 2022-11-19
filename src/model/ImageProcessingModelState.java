package model;

import image.ImageClass;

/**
 * This interface represents an observable version of the interface Image Processing Model and
 * contains methods which does not alter the model.
 */
public interface ImageProcessingModelState {
  /**
   * Returns the model stored in the map with the provided name.
   *
   * @param imageName the name of the image to be retrieved the model for
   * @return the model if the model exists in the map and null if the model is not in the map.
   */
  ImageClass getImage(String imageName);
}
