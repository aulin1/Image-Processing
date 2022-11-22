package view;

/**
 * This interface contains all methods required for testing a view which is implemented by a view
 * of the Image Processing Program.
 */
public interface ViewTesting {
  /**
   * Test if the controller receives the correct darken command input from the view.
   */
  void testViewOutput(String actionCommand);
}
