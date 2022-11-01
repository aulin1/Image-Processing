package view;

import model.ImageProcessingModel;

/**
 * An interface for an image processing view.
 * */
public interface ImageProcessingView {

  /**
   * Loads an image using a given imagePath and imageName.
   *
   * @return an ImageProcessingModel which represents the image you have loaded in.
   * */
   ImageProcessingModel loadImage();

  /**
   * Saves a given image to the image path.
   *
   * @param img the image you are saving.
   * @throws IllegalArgumentException if the image is null.
   * @throws IllegalStateException if the image cannot be saved.
   * */
  void saveImage(ImageProcessingModel img) throws IllegalArgumentException, IllegalStateException;

  /**
   * Changes the image path to which it should be saved.
   *
   * @param imagePath the new image path.
   * */
  void changePath(String imagePath) throws IllegalArgumentException;

  /**
   * Changes the image's name.
   *
   * @param imageName the new image name.
   * */
  void changeName(String imageName) throws IllegalArgumentException;

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
}
