package command;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * This interface represents the methods which an image processing program command should have.
 */
public interface ImageProcessingCommand {
  /**
   * Execute the command on the provided model.
   *
   * @param model the model to execute this command on
   * @return the processed image according the command
   */
  ImageProcessingModel execute(ImageProcessingModel model);

  /**
   * Execute the command on the provided view.
   * @param view the view to execute this command on
   */
  void execute(ImageProcessingView view);
}
