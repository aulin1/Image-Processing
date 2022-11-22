import org.junit.Test;

import view.ImageProcessingGUI;
import view.ImageProcessingView;
import view.MenuBar;

import static org.junit.Assert.assertEquals;

/**
 * This class contains test for inputs provided to the controller from the view.
 */
public class MockControllerTest {
  ImageProcessingView gui;
  MenuBar menuBar;
  MockController mockController;
  StringBuilder receivedInput;

  public void init() {
    receivedInput = new StringBuilder();
    menuBar = new MenuBar();
    gui = new ImageProcessingGUI(menuBar);
    mockController = new MockController(receivedInput, gui);
  }

  /**
   * Test if the controller receives the correct input for red-comp from the view.
   */
  @Test
  public void testRedComp() {
    init();
    menuBar.setImageName("koala");
    menuBar.testViewOutput("red-component");
    assertEquals("red-component koala koalared-component", receivedInput.toString());
  }

  /**
   * Test if the controller receives the correct input for green-comp from the view.
   */
  @Test
  public void testGreenComp() {
    init();
    menuBar.setImageName("koala");
    menuBar.testViewOutput("green-component");
    assertEquals("green-component koala koalagreen-component", receivedInput.toString());
  }

  /**
   * Test if the controller receives the correct input for blue-comp from the view.
   */
  @Test
  public void testBlueComp() {
    init();
    menuBar.setImageName("koala");
    menuBar.testViewOutput("blue-component");
    assertEquals("blue-component koala koalablue-component", receivedInput.toString());
  }

  /**
   * Test if the controller receives the correct input for blur from the view.
   */
  @Test
  public void testBlur() {
    init();
    menuBar.setImageName("koala");
    menuBar.testViewOutput("gaussian-blur");
    assertEquals("gaussian-blur koala koalagaussian-blur", receivedInput.toString());
  }

  /**
   * Test if the controller receives the correct input for sharpen from the view.
   */
  @Test
  public void testSharpen() {
    init();
    menuBar.setImageName("koala");
    menuBar.testViewOutput("sharpen");
    assertEquals("sharpen koala koalasharpen", receivedInput.toString());
  }

  /**
   * Test if the controller receives the correct input for luma from the view.
   */
  @Test
  public void testLuma() {
    init();
    menuBar.setImageName("koala");
    menuBar.testViewOutput("luma");
    assertEquals("luma koala koalaluma", receivedInput.toString());
  }

  /**
   * Test if the controller receives the correct input for intensity from the view.
   */
  @Test
  public void testIntensity() {
    init();
    menuBar.setImageName("koala");
    menuBar.testViewOutput("intensity");
    assertEquals("intensity koala koalaintensity", receivedInput.toString());
  }

  /**
   * Test if the controller receives the correct input for value from the view.
   */
  @Test
  public void testValue() {
    init();
    menuBar.setImageName("koala");
    menuBar.testViewOutput("value");
    assertEquals("value koala koalavalue", receivedInput.toString());
  }

  /**
   * Test if the controller receives the correct input for sepia from the view.
   */
  @Test
  public void testSepia() {
    init();
    menuBar.setImageName("koala");
    menuBar.testViewOutput("sepia");
    assertEquals("sepia koala koalasepia", receivedInput.toString());
  }

  /**
   * Test if the controller receives the correct input for horizontal flip from the view.
   */
  @Test
  public void testHorizontalFlip() {
    init();
    menuBar.setImageName("koala");
    menuBar.testViewOutput("horizontal-flip");
    assertEquals("horizontal-flip koala koalahorizontal-flip", receivedInput.toString());
  }

  /**
   * Test if the controller receives the correct input for vertical flip from the view.
   */
  @Test
  public void testVerticalFlip() {
    init();
    menuBar.setImageName("koala");
    menuBar.testViewOutput("vertical-flip");
    assertEquals("vertical-flip koala koalavertical-flip", receivedInput.toString());
  }
}
