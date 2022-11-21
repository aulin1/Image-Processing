package view.inputRetrieve;

import org.junit.Test;

import javax.swing.*;

import view.ImageProcessingPanel;
import view.MenuBar;

import static org.junit.Assert.assertEquals;

/**
 * This class represents tests for objects of InputRetrieveCommand.
 */
public class InputRetrieveCommTest {
  /**
   * Test if loadInput object loads the correctly formatted input given the file exists.
   */

  /**
   * Test if a saveInput saves the correctly formatted input.
   */

  /**
   * Test if brightenComm returns the correct string.
   */

  /**
   * Test an arbitrary input retrieve comm for its correct composition.
   */
  @Test
  public void inputRetrieveCommWorks() {
    ImageProcessingPanel menu = new MenuBar();
    menu.setImageName("koala");
    InputRetrieveCommand comm = new ImageTransformInput(menu);
    AbstractButton button = new JButton();
    button.setActionCommand("test");

    assertEquals("koala koalatest", comm.getCorrectInput(button));
  }

}