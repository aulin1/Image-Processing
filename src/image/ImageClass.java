package image;

/**
 * An interface which contains the methods that a representation of an image should contain.
 */
public interface ImageClass {

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

  /**
   * Returns the max value for the colors in the image.
   *
   * @return the max value for the colors in the image.
   * */
  int getMax();
}