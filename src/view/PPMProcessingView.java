package view;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import model.ImageProcessingModel;

import static controller.ImageUtil.readPPM;

/**
 * A class that represents a view for PPM images.
 * */
public class PPMProcessingView implements ImageProcessingView{

  private String imagePath;
  private String imageName;

  /**
   * A constructor for a PPMProcessingView.
   *
   * @param imagePath the path for the image.
   * @param imageName the name of the image.
   * @throws IllegalArgumentException if either of them are null.
   * */
  public PPMProcessingView(String imagePath, String imageName){
    if(imagePath == null || imageName == null){
      throw new IllegalArgumentException("Path and name must not be null.");
    }
    this.imagePath = imagePath;
    this.imageName = imageName;
  }

  @Override
  public ImageProcessingModel loadImage() {
    ImageProcessingModel model = readPPM(this.imagePath);
    model.changeName(this.imageName);
    return model;
  }

  @Override
  public void saveImage(ImageProcessingModel img) throws IllegalArgumentException,
          IllegalStateException {
    if(img == null){
      throw new IllegalArgumentException("Image cannot be null.");
    }
    try {
      PrintWriter outfile = new PrintWriter(imagePath);
      outfile.println("# Image created by Trang Do and Audrey Lin's program");
      outfile.println(img.getWidth() + " " + img.getHeight());
      outfile.println(255);

      int[][][] imageBoard = img.getImage();
      for (int r = 0; r < img.getHeight(); r++) {
        for (int c = 0; c < img.getWidth(); c++) {
          outfile.println(imageBoard[r][c][0]); // print red value
          outfile.println(imageBoard[r][c][1]); // print green value
          outfile.println(imageBoard[r][c][2]); // print blue value
        }
      }
      outfile.close();
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("The file cannot be exported.");
    }
  }

  @Override
  public void changePath(String imagePath) throws IllegalArgumentException {
    if(imagePath == null){
      throw new IllegalArgumentException("Path cannot be null.");
    }
    this.imagePath = imagePath;
  }

  @Override
  public void changeName(String imageName) throws IllegalArgumentException {
    if(imageName == null){
      throw new IllegalArgumentException("Name cannot be null.");
    }
    this.imageName = imageName;
  }

  @Override
  public String getName() {
    return this.imageName;
  }

  @Override
  public String getPath(){
    return this.imagePath;
  }
}
