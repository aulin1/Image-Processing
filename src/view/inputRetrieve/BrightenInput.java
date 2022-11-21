package view.inputRetrieve;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.swing.*;

import view.ImageProcessingPanel;

/**
 * This class represents an object which generate appropriate to trigger brighten from the
 * controller based on the user interaction.
 */
public class BrightenInput extends AbstractInputRetComm {
  /**
   * Default constructor for an input retrieve command.
   *
   * @param menu the menu which the command was called from
   * @throws IllegalArgumentException if the menu is null
   */
  public BrightenInput(ImageProcessingPanel menu) throws IllegalArgumentException {
    super(menu);
  }

  //TODO: figure out what to do with non int values
  @Override
  public String getCorrectInput(AbstractButton button) throws IllegalArgumentException {
    String oldName = this.menu.getImageName();
    String newName = this.menu.getImageName() + button.getActionCommand();
    this.menu.setImageName(newName);

    int[] intOptions = IntStream.range(-100, 100).toArray();
    List<String> stringOptions = new ArrayList<>();
    for (int intOption : intOptions) {
      stringOptions.add(String.valueOf(intOption));
    }

    String userInput = (String)JOptionPane.showInputDialog(
            button,
            "Input increment of " +
                    "brightness here:" + System.lineSeparator() + "Negative value will result in darken " +
                    "and vice versa. 0 will result in the same image",
            "Brightness increment",
            JOptionPane.PLAIN_MESSAGE,
            null,
            stringOptions.toArray(),
            "10");
    return oldName + " " + newName + " " + userInput;
  }
}
