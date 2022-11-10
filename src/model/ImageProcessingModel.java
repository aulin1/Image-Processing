package model;

import image.ImageClass;

/**
 * An interface for an image processing model.
 */
public interface ImageProcessingModel {

  /**
   * Loads an image using a given imagePath and imageName.
   *
   * @param imagePath the path to the image which need to be load
   * @param imageName the name of the image to be referred to while using the program
   * @return an ImageProcessingModel which represents the image you have loaded in.
   */
  ImageClass loadImage(String imagePath, String imageName)
          throws IllegalArgumentException;

  /**
   * Saves a given image to the image path with the format indicated through the provided image
   * path.
   *
   * @param imagePath  the path which the image will be saved to
   * @param imageName  the referred name of the image in the program to be saved
   * @throws IllegalStateException if the image cannot be saved
   * @throws IllegalArgumentException if the image name provided does not exist in the memory
   */
  void saveImage(String imagePath, String imageName) throws IllegalStateException,
          IllegalArgumentException;

  /**
   * Stores the provided model with the provided image name as the key.
   *
   * @param imageName the key to the model in the map
   * @param model     the model to be stored
   */
  void storeImage(String imageName, ImageClass model);

  /**
   * Returns the model stored in the map with the provided name.
   *
   * @param imageName the name of the image to be retrieved the model for
   * @return the model if the model exists in the map and null if the model is not in the map.
   */
  ImageClass getImage(String imageName);

  /**
   * Changes the name of an image.
   *
   * @param oldName the old name of the image.
   * @param newName the new name of the image.
   * @throws IllegalArgumentException if either name is null.
   */
  void changeName(String oldName, String newName) throws IllegalArgumentException;
}
