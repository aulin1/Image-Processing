package view.inputretrieve;

import org.junit.Test;

import javax.swing.AbstractButton;
import javax.swing.JButton;

import view.ImageProcessingPanel;
import view.MenuBar;

import static org.junit.Assert.assertEquals;

/**
 * This class represents tests for objects of InputRetrieveCommand.
 */
public class InputRetrieveCommTest {
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