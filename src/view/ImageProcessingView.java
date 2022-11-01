package view;

import model.ImageProcessingModel;

/**
 * An interface for an image processing view.
 * */
public interface ImageProcessingView {

  /**
   * Loads an image using a given imagePath and imageName.
   *
   * @param imagePath the path to the image which need to be load
   * @param imageName the name of the image to be referred to while using the program
   * @return an ImageProcessingModel which represents the image you have loaded in.
   * */
   ImageProcessingModel loadImage(String imagePath, String imageName) throws IllegalArgumentException;

  /**
   * Saves a given image to the image path.
   *
   * @param imagePath the path which the image will be saved to
   * @param imageName the referred name of the image in the program to be saved
   * @throws IllegalStateException if the image cannot be saved.
   * */
  void saveImage(String imagePath, String imageName) throws IllegalStateException;

  /**
   * Stores the provided model with the provided image name as the key.
   *
   * @param imageName the key to the model in the map
   * @param model the model to be stored
   */
  void storeImage(String imageName, ImageProcessingModel model);
  /**
   * Changes the image path to which it should be saved.
   *
   * @param imagePath the new image path.
   * */
  void changePath(String imagePath) throws IllegalArgumentException;
  //FIXME: I don't think we need this method since the file is directly saved only when using the
  // save method

  /**
   * Changes the image's name.
   *
   * @param oldName the old name of the image
   * @param newName the new name of the image
   * @throws IllegalArgumentException if the names provided are null
   * */
  void changeName(String oldName, String newName) throws IllegalArgumentException;

  /**
   * Returns the image's name.
   *
   * @return the name of the image.
   * */
  String getName();

  /**
   * Returns the path of the image.
   *
   * @return the path of the image.
   * */
  String getPath();
  //FIXME: I don't think we need this since the path has no use and should not be stored

  /**
   * Returns the model stored in the map with the provided name.
   *
   * @param imageName the name of the image to be retrieved the model for
   * @return the model if the model exists under the provided key and null if the model is not in
   * the map
   */
  ImageProcessingModel getModel(String imageName);
}
