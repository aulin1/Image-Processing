package view.inputretrieve;

import javax.swing.AbstractButton;

/**
 * This interface contains all methods which an input retrieve command must implement.
 */
public interface InputRetrieveCommand {
  /**
   * Gets the correct input based on the action command of the button that would be feed to the
   * controller.
   *
   * @param button the button which required an input retrieve
   * @return the String of appropriate input for the controller
   * @throws IllegalArgumentException if the button is null
   */
  String getCorrectInput(AbstractButton button) throws IllegalArgumentException;
}
