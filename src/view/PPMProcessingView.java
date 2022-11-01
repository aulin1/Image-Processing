package view;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import model.ImageProcessingModel;
import model.PPMProcessingModel;

import static controller.ImageUtil.readPPM;

/**
 * A class that represents a view for PPM images. A view is mainly responsible for managing the
 * saving and loading of images to and from the program.
 * */
public class PPMProcessingView implements ImageProcessingView{
  private final Map<String, ImageProcessingModel> memory;

  /**
   * A constructor for a PPMProcessingView.
   */
  public PPMProcessingView(){
    this.memory = new HashMap<>();
  }

  /**
   * A constructor for a PPMProcessingView that takes in a HashMap.
   *
   * @param map the hashmap for memory.
   * @throws IllegalArgumentException if the map is null.
   * */
  public PPMProcessingView(Map<String, ImageProcessingModel> map){
    if(map == null){
      throw new IllegalArgumentException("Map cannot be null.");
    }
    this.memory = map;
  }

  @Override
  public ImageProcessingModel loadImage(String imagePath, String imageName)
          throws IllegalArgumentException {
    ImageProcessingModel model = readPPM(imagePath);
    model.changeName(imageName);
    this.storeImage(imageName, model);
    return model;
  }

  @Override
  public void saveImage(String imagePath, String imageName) throws IllegalArgumentException,
          IllegalStateException {
    ImageProcessingModel model = this.memory.getOrDefault(imageName, null);
    if (model == null) {
      throw new IllegalArgumentException("The image has yet to be loaded to the program.");
    }

    try {
      PrintWriter outfile = new PrintWriter(imagePath);
      System.out.println("Writing out to file: " + imageName + ".ppm" + System.lineSeparator());
      outfile.println("P3");
      outfile.println("# Image created by Trang Do and Audrey Lin's program");
      outfile.println(model.getWidth() + " " + model.getHeight());
      outfile.println(255);

      int[][][] imageBoard = model.getImage();
      for (int r = 0; r < model.getHeight(); r++) {
        for (int c = 0; c < model.getWidth(); c++) {
          outfile.println(imageBoard[r][c][0]); // print red value
          outfile.println(imageBoard[r][c][1]); // print green value
          outfile.println(imageBoard[r][c][2]); // print blue value
        }
      }
      outfile.close();
    } catch (Exception e) {
      throw new IllegalStateException("Unable to save file to destination. ");
    }
  }

  @Override
  public void storeImage(String imageName, ImageProcessingModel model) {
    memory.put(imageName, model);
  }

  @Override
  public void changeName(String oldName, String newName) throws IllegalArgumentException {
    if(oldName == null || newName == null) {
      throw new IllegalArgumentException("Name cannot be null.");
    }
    if (this.memory.containsKey(oldName)) {
      ImageProcessingModel model = this.memory.get(oldName);
      this.memory.put(newName, model);
    } else {
      throw new IllegalArgumentException("There is no existing file with the provided name in the" +
              " program. ");
    }
  }


  @Override
  public ImageProcessingModel getModel(String imageName) {
    return this.memory.getOrDefault(imageName, null);
  }
}
