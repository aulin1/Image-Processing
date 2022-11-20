package view;

/**
 * This interface contains all methods which a message panel has to implements for an Image
 * Processing Program.
 */
public interface MessagePanel extends DisplayedPanel {
  void renderError(String text);
  void renderWarning(String text);
}
