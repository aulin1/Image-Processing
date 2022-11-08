package controller;

import model.ImageProcessingModel;

public interface ModelCommand {
  /**
   * Executes the command on the provided view.
   *
   * @param view the view to apply the command on
   */
  void execute(ImageProcessingModel view);
}
