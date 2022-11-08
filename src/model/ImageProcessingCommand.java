package model;

import image.ImageClass;

/**
 * This interface represents the methods which an image processing command should have.
 */
public interface ImageProcessingCommand {
  /**
   * Execute the command on the provided model.
   *
   * @param model the model to execute the command on
   * @return the processed image according the command
   */
  ImageClass execute(ImageClass model);

}