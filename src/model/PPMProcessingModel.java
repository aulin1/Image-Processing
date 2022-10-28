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
   * A function that gets the greyscale image of a specific color. 0 for red, 1 for green,
   * 2 for blue.
   *
   * @param color the color to which the greyscale is based on.
   * @return a 3D array which represents the greyscale image.
   * */
  private int[][][] getGreyScale(int color){
    int[][][] img = new int[this.getWidth()][this.getHeight()][3];
    for(int i = 0; i < this.getWidth(); i++){
      for(int j = 0; j < this.getHeight(); j++){
        img[i][j][0] = this.imageBoard[i][j][color];
        img[i][j][1] = this.imageBoard[i][j][color];
        img[i][j][2] = this.imageBoard[i][j][color];
      }
    }
    return img;
  }

  @Override
  public ImageProcessingModel returnRedImage() {
    int[][][] img = this.getGreyScale(0);
    ImageProcessingModel red = new PPMProcessingModel(img);
    return red;
  }

  @Override
  public ImageProcessingModel returnGreenImage() {
    int[][][] img = this.getGreyScale(0);
    ImageProcessingModel green = new PPMProcessingModel(img);
    return green;
  }

  @Override
  public ImageProcessingModel returnBlueImage() {
    int[][][] img = this.getGreyScale(0);
    ImageProcessingModel blue = new PPMProcessingModel(img);
    return blue;
  }

  @Override
  public ImageProcessingModel returnValueImage() {
    int[][][] img = new int[this.getWidth()][this.getHeight()][3];
    for(int i = 0; i < this.getWidth(); i++){
      for(int j = 0; j < this.getHeight(); j++){
        int max = max(max(this.imageBoard[i][j][0],
                this.imageBoard[i][j][1]), this.imageBoard[i][j][2]);
        img[i][j][0] = max;
        img[i][j][1] = max;
        img[i][j][2] = max;
      }
    }
    ImageProcessingModel value = new PPMProcessingModel(img);
    return value;
  }

  @Override
  public ImageProcessingModel returnIntensityImage() {
    int[][][] img = new int[this.getWidth()][this.getHeight()][3];
    for(int i = 0; i < this.getWidth(); i++){
      for(int j = 0; j < this.getHeight(); j++){
        int avg = this.imageBoard[i][j][0] + this.imageBoard[i][j][1] + this.imageBoard[i][j][2];
        img[i][j][0] = avg;
        img[i][j][1] = avg;
        img[i][j][2] = avg;
      }
    }
    ImageProcessingModel intensity = new PPMProcessingModel(img);
    return intensity;
  }

  @Override
  public ImageProcessingModel returnLumaImage() {
    int[][][] img = new int[this.getWidth()][this.getHeight()][3];
    for(int i = 0; i < this.getWidth(); i++){
      for(int j = 0; j < this.getHeight(); j++){
        double lum = 0.2126 * this.imageBoard[i][j][0]
                + 0.7152 * this.imageBoard[i][j][1]
                + 0.0722 * this.imageBoard[i][j][2];
        img[i][j][0] = (int) lum;
        img[i][j][1] = (int) lum;
        img[i][j][2] = (int) lum;
      }
    }
    ImageProcessingModel luma = new PPMProcessingModel(img);
    return luma;
  }

  @Override
  public void flipImageVertically() {
    //ill finish this later
  }

  @Override
  public void flipImageHorizontally() {
    //ill finish this later
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
