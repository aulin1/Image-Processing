package model;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import image.ImageClass;

import static controller.ImageUtil.readPPM;

/**
 * A class that represents a model for PPM images. A model is mainly responsible for managing the
 * saving and loading of images to and from the program.
 */
public class PPMProcessingModel implements ImageProcessingModel {
  private final Map<String, ImageClass> memory;

  /**
   * A constructor for a PPMProcessingModel.
   */
  public PPMProcessingModel() {
    this.memory = new HashMap<>();
  }

  /**
   * A constructor for a PPMProcessingModel that takes in a HashMap.
   *
   * @param map the hashmap for memory.
   * @throws IllegalArgumentException if the map is null.
   */
  public PPMProcessingModel(Map<String, ImageClass> map) {
    if (map == null) {
      throw new IllegalArgumentException("Map cannot be null.");
    }
    this.memory = map;
  }

  @Override
  public ImageClass loadImage(String imagePath, String imageName)
          throws IllegalArgumentException {
    ImageClass model = readPPM(imagePath);
    this.storeImage(imageName, model);
    return model;
  }

  @Override
  public void saveImage(String imagePath, String imageName) throws IllegalArgumentException,
          IllegalStateException {
    if (imagePath == null || imageName == null) {
      throw new IllegalArgumentException("The arguments cannot be null.");
    }

    String[] imagePathParsed = imagePath.split("\\.");
    if (!imagePathParsed[imagePathParsed.length - 1].equals("ppm")) {
      throw new IllegalArgumentException("The provided filepath indicate the export of a ppm file" +
              ".");
    }

    ImageClass model = this.memory.getOrDefault(imageName, null);
    if (model == null) {
      throw new IllegalArgumentException("The image has yet to be loaded to the program.");
    }

    try {
      PrintWriter outfile = new PrintWriter(imagePath);
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
  public void storeImage(String imageName, ImageClass model) {
    memory.put(imageName, model);
  }

  @Override
  public void changeName(String oldName, String newName) throws IllegalArgumentException {
    if (oldName == null || newName == null) {
      throw new IllegalArgumentException("Name cannot be null.");
    }
    if (this.memory.containsKey(oldName)) {
      ImageClass model = this.memory.get(oldName);
      this.memory.put(newName, model);
    } else {
      throw new IllegalArgumentException("There is no existing file with the provided name in the"
              + " program. ");
    }
  }

  @Override
  public ImageClass getImage(String imageName) {
    return this.memory.getOrDefault(imageName, null);
  }
}
