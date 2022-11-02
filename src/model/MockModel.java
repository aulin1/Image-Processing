package model;

/**
 * This class represents a mock model for testing input from the Image Processing Controller.
 */
public class MockModel implements ImageProcessingModel {
  StringBuilder log;

  /**
   * Constructor for a Mock Model.
   *
   * @param log the log of inputs received from the controller
   * @throws IllegalArgumentException if the log is null
   */
  public MockModel(StringBuilder log) throws IllegalArgumentException {
    if (log == null) {
      throw new IllegalArgumentException("The log cannot be null. ");
    }
    this.log = log;
  }

  @Override
  public ImageProcessingModel returnRedImage() {
    return null;
  }

  @Override
  public ImageProcessingModel returnGreenImage() {
    return null;
  }

  @Override
  public ImageProcessingModel returnBlueImage() {
    return null;
  }

  @Override
  public ImageProcessingModel returnValueImage() {
    return null;
  }

  @Override
  public ImageProcessingModel returnIntensityImage() {
    return null;
  }

  @Override
  public ImageProcessingModel returnLumaImage() {
    return null;
  }

  @Override
  public ImageProcessingModel flipImageVertically() {
    return null;
  }

  @Override
  public ImageProcessingModel flipImageHorizontally() {
    return null;
  }

  @Override
  public ImageProcessingModel changeBrightness(int factor) throws IllegalArgumentException {
    log.append("factor: ").append(factor).append("\n");
    return null;
  }

  @Override
  public int getWidth() {
    return 0;
  }

  @Override
  public int getHeight() {
    return 0;
  }

  @Override
  public int[][][] getImage() {
    return new int[0][][];
  }
}
