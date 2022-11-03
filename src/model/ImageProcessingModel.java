package model;

/**
 * An interface which contains the methods that a model for image processing should contain.
 */
public interface ImageProcessingModel {

  /**
   * Returns a greyscale image based on the red values of an image.
   *
   * @returns the greyscale image as a new PPMImage.
   */
  ImageProcessingModel returnRedImage();

  /**
   * Returns a greyscale image based on the green values of an image.
   *
   * @returns the greyscale image as a new PPMImage.
   */
  ImageProcessingModel returnGreenImage();

  /**
   * Returns a greyscale image based on the blue values of an image.
   *
   * @returns the greyscale image as a new PPMImage.
   */
  ImageProcessingModel returnBlueImage();

  /**
   * Returns a greyscale image based on the values of an image.
   *
   * @returns the greyscale image as a new PPMImage.
   */
  ImageProcessingModel returnValueImage();

  /**
   * Returns a greyscale image based on the intensity of an image.
   *
   * @returns the greyscale image as a new PPMImage.
   */
  ImageProcessingModel returnIntensityImage();

  /**
   * Returns a greyscale image based on the luma of an image.
   *
   * @returns the greyscale image as a new PPMImage.
   */
  ImageProcessingModel returnLumaImage();

  /**
   * Flips the image vertically.
   *
   * @return the flipped image.
   */
  ImageProcessingModel flipImageVertically();

  /**
   * Flips the image horizontally.
   *
   * @return the flipped image.
   */
  ImageProcessingModel flipImageHorizontally();

  /**
   * Changes the brightness of the image.
   *
   * @param factor the factor by which the brightness changes.
   * @return the changed image.
   */
  ImageProcessingModel changeBrightness(int factor);

  /**
   * Returns the width of the image.
   *
   * @return the width of the image.
   */
  int getWidth();

  /**
   * Returns the height of the image.
   *
   * @return the height of the image.
   */
  int getHeight();

  /**
   * Returns an 3D array of pixel values that indicates the amount of that particular color has
   * for that pixel. The values are kept as such: [row][column][color].
   *
   * @returns a 3D integer array.
   */
  int[][][] getImage();
}