package controller;

import model.ImageProcessingModel;

/**
 * An interface which represents a command that acts upon the model.
 * */
public interface ModelCommand {
  /**
   * Executes the command on the provided view.
   *
   * @param view the view to apply the command on
   */
  void execute(ImageProcessingModel view);
}
