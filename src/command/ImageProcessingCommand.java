package command;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * This interface represents the methods which an image processing command should have.
 */
public interface ImageProcessingCommand {
  /**
   * Execute the command on the provided model.
   *
   * @param model the model to execute the command on
   * @return the processed image according the command
   * @throws IllegalArgumentException if the model is null
   */
  ImageProcessingModel execute(ImageProcessingModel model) throws IllegalArgumentException;

  /**
   * Executes the command on the provided view.
   *
   * @param view the view to apply the command on
   * @throws IllegalArgumentException if the view is null
   */
  void execute(ImageProcessingView view) throws IllegalArgumentException;
}
