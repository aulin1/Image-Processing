package view.inputRetrieve;

import javax.swing.*;

import view.ImageProcessingPanel;

/**
 * This class represents an object which handles color transformation commands.
 */
public class ImageTransformInput extends AbstractInputRetComm {
  /**
   * Default constructor for an input retrieve command.
   *
   * @param menu the menu which the command was called from
   * @throws IllegalArgumentException if the menu is null
   */
  public ImageTransformInput(ImageProcessingPanel menu) throws IllegalArgumentException {
    super(menu);
  }
}
