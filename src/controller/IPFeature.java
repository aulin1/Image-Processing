package controller;

/**
 * This interface contains all methods which an Image Processing controller that supports GUI are
 * required to have.
 */
public interface IPFeature {
  /**
   * Parse the provided string of input into digestible commands and process them.
   * @param input input received from the view.
   */
  void processInput(String input);
}
