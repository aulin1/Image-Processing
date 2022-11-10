import org.junit.Test;

import java.io.StringReader;

import controller.ImageProcessingControllerImpl;
import model.ImageProcessingModel;

import static org.junit.Assert.assertEquals;

/**
 * This class contains tests for image processing controller impl.
 */
public class MockModelTest {
  ImageProcessingModel view;
  ImageProcessingControllerImpl controller;
  StringBuilder receivedInput;
  StringBuilder controllerOutput;
  StringReader controllerInput;

  /**
   * Set up the necessary items for testing.
   */
  private void init() {
    receivedInput = new StringBuilder();
    controllerOutput = new StringBuilder();
    view = new MockModel(receivedInput);
  }

  /**
   * Check if the view receives the correct input for saveImage().
   */
  @Test
  public void mockSaveImage() {
    init();
    controllerInput = new StringReader("save res/Koala.ppm koala q");
    controller = new ImageProcessingControllerImpl(controllerOutput, controllerInput, view);
    controller.start();
    assertEquals("res/Koala.ppm koala", receivedInput.toString());
  }

  /**
   * Check if the view receives the correct input for loadImage().
   */
  @Test
  public void mockLoadImage() {
    init();
    controllerInput = new StringReader("load res/Koala.ppm koala q");
    controller = new ImageProcessingControllerImpl(controllerOutput, controllerInput, view);
    controller.start();
    assertEquals("res/Koala.ppm koala", receivedInput.toString());
  }

  /**
   * Check if the view receives the correct input for changeName().
   */
  @Test
  public void mockChangeName() {
    init();
    controllerInput = new StringReader("change-name koala fancyKoala q");
    controller = new ImageProcessingControllerImpl(controllerOutput, controllerInput, view);
    controller.start();
    assertEquals("koala fancyKoala", receivedInput.toString());
  }
}