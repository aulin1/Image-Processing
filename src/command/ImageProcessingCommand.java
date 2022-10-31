package command;

import model.ImageProcessingModel;

/**
 * This interface represents the methods which an image processing command should have.
 */
public interface ImageProcessingCommand {
  /**
   * Execute the command.
   *
   * @return the processed image according the command
   */
  ImageProcessingModel execute(ImageProcessingModel model);
}
