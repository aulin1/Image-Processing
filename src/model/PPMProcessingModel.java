package model;

import static java.lang.Math.max;

/**
 * A model that processes PPM Images.
 * */
public class PPMProcessingModel implements ImageProcessingModel{

  /**
   * The image saved as a 3D integer array.
   * */
  private int[][][] imageBoard;

  /**
   * The constructor for the PPMProcessingModel.
   *
   * @param imageBoard the image as a 3D array.
   * @throws IllegalArgumentException if imageBoard is null.
   * */
  public PPMProcessingModel(int[][][] imageBoard){
    if(imageBoard == null){
      throw new IllegalArgumentException("Image cannot be null.");
    }
    this.imageBoard = imageBoard.clone();
  }

  /**
   * A helper function that gets the greyscale image of a specific color. 0 for red, 1 for green,
   * 2 for blue.
   *
   * @param factor the factor by which the greyscale image is created: "red", "blue", "green",
   *               "value", "intensity", "luma".
   * @return a 3D array which represents the greyscale image.
   * */
  private int[][][] getGreyScale(String factor){
    int[][][] img = new int[this.getWidth()][this.getHeight()][3];
    for(int i = 0; i < this.getWidth(); i++){
      for(int j = 0; j < this.getHeight(); j++){
        img[i][j][0] = getCorrectValue(factor, i, j);
        img[i][j][1] = getCorrectValue(factor, i, j);
        img[i][j][2] = getCorrectValue(factor, i, j);
      }
    }
    return img;
  }

  /**
   * Another helper function which gets the correct value for the greyscale image.
   *
   * @param factor the factor by which the greyscale image is created as specified by getGreyScale.
   * @param row the row of the pixel to get the value for.
   * @param col the column of the pixel to get the value for.
   * @return the correct value for the pixel.
   * @throws IllegalArgumentException if the factor value doesn't exist.
   * */
  private int getCorrectValue(String factor, int row, int col) throws IllegalArgumentException{
    switch (factor) {
      case "red":
        return this.imageBoard[row][col][0];
      case "green":
        return this.imageBoard[row][col][1];
      case "blue":
        return this.imageBoard[row][col][2];
      case "value":
        return max(max(this.imageBoard[row][col][0], this.imageBoard[row][col][1]),
                this.imageBoard[row][col][2]);
      case "intensity":
        return (this.imageBoard[row][col][0] + this.imageBoard[row][col][1]
                + this.imageBoard[row][col][2])/3;
      case "luma":
        return (int) (0.2126 * this.imageBoard[row][col][0]
                + 0.7152 * this.imageBoard[row][col][1]
                + 0.0722 * this.imageBoard[row][col][2]);
      default:
        throw new IllegalArgumentException("Invalid format.");
    }
  }

  @Override
  public ImageProcessingModel returnRedImage() {
    int[][][] img = this.getGreyScale("red");
    ImageProcessingModel red = new PPMProcessingModel(img);
    return red;
  }

  @Override
  public ImageProcessingModel returnGreenImage() {
    int[][][] img = this.getGreyScale("green");
    ImageProcessingModel green = new PPMProcessingModel(img);
    return green;
  }

  @Override
  public ImageProcessingModel returnBlueImage() {
    int[][][] img = this.getGreyScale("blue");
    ImageProcessingModel blue = new PPMProcessingModel(img);
    return blue;
  }

  @Override
  public ImageProcessingModel returnValueImage() {
    int[][][] img = this.getGreyScale("value");
    ImageProcessingModel value = new PPMProcessingModel(img);
    return value;
  }

  @Override
  public ImageProcessingModel returnIntensityImage() {
    int[][][] img = this.getGreyScale("intensity");
    ImageProcessingModel intensity = new PPMProcessingModel(img);
    return intensity;
  }

  @Override
  public ImageProcessingModel returnLumaImage() {
    int[][][] img = this.getGreyScale("luma");
    ImageProcessingModel luma = new PPMProcessingModel(img);
    return luma;
  }

  @Override
  public void flipImageVertically() {
    int[][][] img = new int[this.getWidth()][this.getHeight()][3];
    for(int i = 0; i < this.getWidth()/2; i++){
      for(int j = 0; j < this.getHeight(); j++){
        img[i][j] = this.imageBoard[this.getWidth() - 1 - i][j];
        img[this.getWidth() - 1 - i][j] = this.imageBoard[i][j];
      }
    }
    this.imageBoard = img;
  }

  @Override
  public void flipImageHorizontally() {
    int[][][] img = new int[this.getWidth()][this.getHeight()][3];
    for(int i = 0; i < this.getWidth(); i++){
      for(int j = 0; j < this.getHeight()/2; j++){
        img[i][j] = this.imageBoard[i][this.getHeight() - 1 - j];
        img[i][this.getHeight() - 1 - j] = this.imageBoard[i][j];
      }
    }
    this.imageBoard = img;
  }

  /**
   * A helper function to check the limits for the changeBrightness function.
   *
   * @param checked the integer to be checked.
   * @return the result within the limits set.
   * */
  private int checkLimits(int checked){
    if (checked < 0) {
      return 0;
    }
    if (checked > 255){
      return 255;
    }
    return checked;
  }

  @Override
  public void changeBrightness(int factor) {
    int[][][] img = new int[this.getWidth()][this.getHeight()][3];
    for(int i = 0; i < this.getWidth(); i++){
      for(int j = 0; j < this.getHeight(); j++){
        img[i][j][0] = this.checkLimits(this.imageBoard[i][j][0] + factor);
        img[i][j][1] = this.checkLimits(this.imageBoard[i][j][0] + factor);
        img[i][j][2] = this.checkLimits(this.imageBoard[i][j][0] + factor);
      }
    }
    this.imageBoard = img;
  }

  @Override
  public int getWidth() {
    return this.imageBoard.length;
  }

  @Override
  public int getHeight() {
    return this.imageBoard[0].length;
  }

  @Override
  public int[][][] getImage() {
    return this.imageBoard.clone();
  }

}
