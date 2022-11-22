package view.inputretrieve;

import java.io.File;

import javax.swing.AbstractButton;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;

import view.ImageProcessingPanel;

/**
 * This class represents a function object which manages appropriate input for an instance of
 * image load.
 */
public class LoadInput extends AbstractInputRetComm {

  /**
   * Default constructor for an input retrieve command.
   *
   * @param menu the menu which the command was called from
   * @throws IllegalArgumentException if the menu is null
   */
  public LoadInput(ImageProcessingPanel menu) throws IllegalArgumentException {
    super(menu);
  }

  @Override
  public String getCorrectInput(AbstractButton button) throws IllegalArgumentException {
    if (button == null) {
      throw new IllegalArgumentException("The argument cannot be null.");
    }
    String filePath = "";
    String fileName = "";

    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Images", "jpg", "png", "bmp", "ppm");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(button);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      filePath = f.getAbsolutePath();
      String[] nameString = f.getName().split("\\.");
      fileName = nameString[0];
    }
    this.menu.setImageName(fileName);
    return filePath + " " + fileName;
  }
}
