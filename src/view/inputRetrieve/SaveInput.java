package view.inputRetrieve;

import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import view.ImageProcessingPanel;

/**
 * This class represents an object which handles the appropriate input for saving an image.
 */
public class SaveInput implements InputRetrieveCommand {
  private final ImageProcessingPanel menu;

  /**
   * Default constructor for save input retrieve command.
   *
   * @param menu the menu which the command is called from
   * @throws IllegalArgumentException if the menu is null
   */
  public SaveInput(ImageProcessingPanel menu) throws IllegalArgumentException {
    if (menu == null) {
      throw new IllegalArgumentException("The argument cannot be null.");
    }
    this.menu = menu;
  }


  @Override
  public String getCorrectInput(AbstractButton button) throws IllegalArgumentException, IllegalStateException {
    if (button == null) {
      throw new IllegalArgumentException("The argument cannot be null.");
    }
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Images", "jpg", "png", "bmp", "ppm");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showSaveDialog(button);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      return f.getAbsolutePath() + " " + this.menu.getImageName();
    }
    throw new IllegalStateException("Cannot retrieve filepath save.");
  }
}
