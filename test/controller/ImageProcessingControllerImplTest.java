package controller;

import org.junit.Test;

import java.io.StringReader;

import view.ImageProcessingView;
import view.PPMProcessingView;

import static org.junit.Assert.assertEquals;

/**
 * This class represents tests for controller implementation.
 */
public class ImageProcessingControllerImplTest {
  StringReader controllerInput;
  StringBuilder controllerOutput;
  ImageProcessingView view;
  ImageProcessingController controller;
  String[] actualOutput;

  String welcomeMessage = "Welcome to Image Processing Program!\n";
  String instructions = "Supported commands in this program:\n" + "load image-path image-name: " +
          "Load the image with the given image path and refers to it with the given name\n" +
          "save image-path image-name: Save the image with the given name to the specified path " +
          "which includes the name of the file\n" + "change-name old-name new-name: change the " +
          "name of a loaded image to the new provided name\n" + "\n" + "All commands below will " +
          "be referred by the designated destination name after the command by the program:\n" +
          "Greyscale commands:\n" + "red-component image-name dest-image-name: Create a greyscale" +
          " image with the red component of the image with the given name\n" + "green-component " +
          "image-name dest-image-name: Create a greyscale image with the green component of the " +
          "image with the given name\n" + "blue-component image-name dest-image-name: Create a " +
          "greyscale image with the blue component of the image with the given name\n" + "value " +
          "image-name dest-image-name: Create a greyscale image with the maximum value of the " +
          "three component for each pixel\n" + "intensity image-name dest-image-name: Create a " +
          "greyscale image with the average of the three components for each pixel\n" + "luma " +
          "image-name dest-image-name: Create a greyscale image with the weighted sum 0.2126r + 0" +
          ".7152g + 0.0722b\n" + "\n" + "Flip image commands:\n" + "horizontal-flip image-name " +
          "dest-image-name: Flip an image horizontally to create a new image\n" + "vertical-flip " +
          "image-name dest-image-name: Flip an image vertically to create a new image\n" + "\n"
          + "Brightness command:\n" + "brighten image-name dest-image-name increment: brighten " +
          "the image by the given increment to create a new image. Positive value will brighten " +
          "the image and negative value will darken the image\n" + "\n" + "Input m to see the " +
          "supported commands. \n" + "Input q to quit the program. \n";
  String farewellMessage = "Thank you for using the program!\n";

  /**
   * Set up materials to test the controller's messages.
   */
  private void init() {
    controllerInput = new StringReader("q");
    controllerOutput = new StringBuilder();
    view = new PPMProcessingView();
    controller = new ImageProcessingControllerImpl(controllerOutput, controllerInput, view);
    controller.start();
    actualOutput = controllerOutput.toString().split("\n");
  }

  /**
   * Check if the controller throws an exception if the appendable is null.
   */
  @Test
  public void controllerThrowsExceptionAppendableNull() {
    try {
      ImageProcessingControllerImpl controller = new ImageProcessingControllerImpl(null,
              new StringReader("bool"), new PPMProcessingView());
    } catch (IllegalArgumentException e) {
      assertEquals("The fields to the controller constructor cannot be null.", e.getMessage());
    }
  }

  /**
   * Check if the controller throws an exception if the readable is null.
   */
  @Test
  public void controllerThrowsExceptionReadableNull() {
    try {
      ImageProcessingControllerImpl controller =
              new ImageProcessingControllerImpl(new StringBuilder(), null, new PPMProcessingView());
    } catch (IllegalArgumentException e) {
      assertEquals("The fields to the controller constructor cannot be null.", e.getMessage());
    }
  }

  /**
   * Check if the controller throws an exception if the view is null.
   */
  @Test
  public void controllerThrowsExceptionViewNull() {
    try {
      ImageProcessingControllerImpl controller =
              new ImageProcessingControllerImpl(new StringBuilder(), new StringReader("bool"),
                      null);
    } catch (IllegalArgumentException e) {
      assertEquals("The fields to the controller constructor cannot be null. ", e.getMessage());
    }
  }

  /**
   * Check if the controller throws an exception if it runs out of input before quiting the program.
   */
  @Test
  public void controllerThrowsExceptionOutOfInput() {
    try {
      StringReader input = new StringReader("load");
      ImageProcessingControllerImpl controller =
              new ImageProcessingControllerImpl(new StringBuilder(), input, null);
    } catch (IllegalStateException e) {
      assertEquals("Readable fails or the program ran out of inputs before " +
                      "quitting.", e.getMessage());
    }
  }

  /**
   * Check if the controller prints welcome message properly.
   */
  @Test
  public void controllerPrintsWelcome() {
    init();
    StringBuilder testWelcome = new StringBuilder();
    for (int i = 0; i < 1; i++) {
      testWelcome = testWelcome.append(actualOutput[i]).append("\n");
    }

    assertEquals(welcomeMessage, testWelcome.toString());
  }

  /**
   * Check if the controller prints instructions properly.
   */
  @Test
  public void controllerPrintsInstructions() {
    init();
    StringBuilder testInstructions = new StringBuilder();
    int size = actualOutput.length;

    for (int i = 1; i < size - 1; i++) {
      testInstructions = testInstructions.append(actualOutput[i]).append("\n");
    }

    assertEquals(this.instructions, testInstructions.toString());
  }

  /**
   * Check if the controller prints the farewell message properly.
   */
  @Test
  public void controllerPrintsFarewell() {
    init();
    StringBuilder testFarewell = new StringBuilder();
    int size = actualOutput.length;

    for (int i = size - 1; i < size; i++) {
      testFarewell = testFarewell.append(actualOutput[i]).append("\n");
    }

    assertEquals(this.farewellMessage, testFarewell.toString());
  }

  /**
   * Check if the controller throws an exception if the appendable fails when writing message.
   */
  @Test(expected = IllegalStateException.class)
  public void controllerThrowsExceptionFailAppendable() {
      ImageProcessingController controller =
              new ImageProcessingControllerImpl(new FailAppendable(), new StringReader("bool"),
                      new PPMProcessingView());
      controller.start();
  }

  /**
   * Check if the controller throws an exception if the readable fails when receiving inputs.
   */
  @Test(expected = IllegalStateException.class)
  public void controllerThrowsExceptionFailReadable() {
      ImageProcessingController controller =
              new ImageProcessingControllerImpl(new StringBuilder(), new FailReadable(),
                      new PPMProcessingView());
      controller.start();
  }

  /**
   * Check if the controller prints the correct message if a command doesn't exist.
   * */
  @Test
  public void controllerIncorrectCommand(){
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("load res/Pixel.ppm Pixel f q");
    ImageProcessingView view = new PPMProcessingView();
    ImageProcessingController test = new ImageProcessingControllerImpl(out, in, view);
    test.start();
    String[] splitString = out.toString().split("\n");
    assertEquals("Command is not supported.", splitString[splitString.length - 2]);
  }

  /**
   * Check if the controller prints the correct message if a command does exist.
   * */
  @Test
  public void controllerCorrectCommand(){
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("load res/Pixel.ppm Pixel brighten Pixel brightenImage"
            + " 100 q");
    ImageProcessingView view = new PPMProcessingView();
    ImageProcessingController test = new ImageProcessingControllerImpl(out, in, view);
    test.start();
    String[] splitString = out.toString().split("\n");
    assertEquals("Command brighten successfully processed!",
            splitString[splitString.length - 2]);
  }

  /**
   * Check if the controller prints the correct message if the image hasn't been loaded into
   * the program.
   * */
  @Test
  public void controllerNotLoad(){
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("brighten Pixel brightenImage"
            + "100 q");
    ImageProcessingView view = new PPMProcessingView();
    ImageProcessingController test = new ImageProcessingControllerImpl(out, in, view);
    test.start();
    String[] splitString = out.toString().split("\n");
    assertEquals("The image has yet loaded to the program. Please load a valid image "
            + "before processing it.", splitString[splitString.length - 2]);
  }
}