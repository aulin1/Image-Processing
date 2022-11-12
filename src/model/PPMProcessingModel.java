package model;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import image.ImageClass;

import static controller.ImageUtil.readPPM;
import static controller.ImageUtil.savePPM;

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
    ImageClass model = this.memory.getOrDefault(imageName, null);
    if (model == null) {
      throw new IllegalArgumentException("The image has yet to be loaded to the program.");
    }
    savePPM(imagePath, model);
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
