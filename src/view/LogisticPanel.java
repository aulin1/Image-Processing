package view;

/**
 * This interface contains all methods which a logistic panel is required to implement.
 */
public interface LogisticPanel extends DisplayedPanel {
  /**
   * Renders the provided log at the appropriate panel.
   *
   * @param text the text to be rendered in the log
   */
  void renderLog(String text);
}
