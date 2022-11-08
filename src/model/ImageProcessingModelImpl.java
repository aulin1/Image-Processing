package model;

import static java.lang.Math.max;

/**
 * A model that processes PPM Images.
 */
public class ImageProcessingModelImpl implements ImageProcessingModel {

  /**
   * The image saved as a 3D integer array.
   */

  private final int[][][] imageBoard;

  /**
   * The max value of a color.
   */
  private final int maxValue;

  /**
   * The constructor for the PPMProcessingModel.
   *
   * @param imageBoard the image as a 3D array.
   * @param maxValue   the max value of a color in this file
   * @throws IllegalArgumentException if imageBoard is null, or if the max value is less than or
   * equal to 0.
   */
  public ImageProcessingModelImpl(int[][][] imageBoard, int maxValue) {
    if (imageBoard == null) {
      throw new IllegalArgumentException("Image or name cannot be null.");
    }
    if(maxValue <= 0 ){
      throw new IllegalArgumentException("Max value must be greater than 0.");
    }
    this.imageBoard = imageBoard.clone();
    this.maxValue = maxValue;
  }

  @Override
  public int getHeight() {
    return this.imageBoard.length;
  }

  @Override
  public int getWidth() {
    return this.imageBoard[0].length;
  }

  @Override
  public int[][][] getImage() {
    return this.imageBoard.clone();
  }

  @Override
  public int getMax() {
    return this.maxValue;
  }

}
