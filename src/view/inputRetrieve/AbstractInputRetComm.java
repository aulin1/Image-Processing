package view.inputRetrieve;

import javax.swing.*;

import view.ImageProcessingPanel;

/**
 * This class represents a generic input retrieve command.
 */
public abstract class AbstractInputRetComm implements InputRetrieveCommand {
  protected final ImageProcessingPanel menu;

  /**
   * Default constructor for an input retrieve command.
   *
   * @param menu the menu which the command was called from
   * @throws IllegalArgumentException if the menu is null
   */
  public AbstractInputRetComm(ImageProcessingPanel menu) throws IllegalArgumentException {
    if (menu == null) {
      throw new IllegalArgumentException("The argument cannot be null.");
    }
    this.menu = menu;
  }

  @Override
  public String getCorrectInput(AbstractButton button) {
    String oldName = this.menu.getImageName();
    String newName = this.menu.getImageName() + button.getActionCommand();
    this.menu.setImageName(newName);
    return oldName + " " + newName;
  }
}
