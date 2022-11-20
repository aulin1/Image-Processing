package controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

import image.ImageClass;
import model.ImageProcessingModel;
import model.PPMProcessingModel;

/**
 * This class represents the implementation of the Image Processing Controller.
 */
public class ImageProcessingControllerImpl extends ControllerCommandUtil
        implements ImageProcessingController{
  private final Appendable output;
  private final Readable input;

  /**
   * Default constructor for image processing controller with input from the keyboard and output
   * to the user's console.
   */
  public ImageProcessingControllerImpl() throws IllegalArgumentException {
    this(System.out, new InputStreamReader(System.in), new PPMProcessingModel());
  }

  /**
   * Creates a new controller for the Image Processing Program.
   *
   * @param output the desired output of the messages
   * @param input  the desired input of the user's interaction
   * @throws IllegalArgumentException if any of the field is null
   */
  public ImageProcessingControllerImpl(Appendable output, Readable input,
                                       ImageProcessingModel model)
          throws IllegalArgumentException {
    if (input == null || output == null) {
      throw new IllegalArgumentException("The fields to the controller"
              + " constructor cannot be null.");
    }
    this.output = output;
    this.input = input;
    this.imgProcCommandMap = new HashMap<>();
    this.modelCommandMap = new HashMap<>();
    this.model = model;
    initiateCommsOld();
  }

  @Override
  public void start() {
    Scanner sc = new Scanner(input);

    this.welcomeMessage();
    while (sc.hasNext()) {
      String s = sc.next();

      switch (s.toLowerCase()) { // make the controller case-insensitive
        case "q":
          writeMessage("Thank you for using the program!");
          return;
        case "m":
          printInstructions();
          break;
        default:
          readComm(s.toLowerCase(), sc);
      }
    }
    // program has not quit but ran out of inputs
    throw new IllegalStateException("The program ran out of arguments even though the program "
            + "has not quit. ");
  }


  /**
   * A helper function which writes a message to the appendable output.
   *
   * @param message the message to be written.
   * @throws IllegalStateException if the message cannot be appended.
   */
  @Override
  protected void writeMessage(String message) throws IllegalStateException {
    try {
      output.append(message);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  /**
   * Does not render an image, as there is nowhere to render an image to.
   * */
  @Override
  protected void renderImage(ImageClass img) {
    //nothing happens.
    writeMessage("");
  }

  /**
   * Writes the welcome message for the program.
   *
   * @throws IllegalStateException if the message cannot be printed.
   */
  private void welcomeMessage() throws IllegalStateException {
    writeMessage("Welcome to Image Processing Program!" + System.lineSeparator());
    printInstructions();
  }

  /**
   * Prints the instructions for the program.
   *
   * @throws IllegalStateException if the messages cannot be printed.
   */
  private void printInstructions() throws IllegalStateException {
    writeMessage("Supported commands in this program:" + System.lineSeparator());

    printSaveLoadInst();

    writeMessage("All commands below will be referred by the designated destination name "
            + "after the command by the program:" + System.lineSeparator());

    printImageProcessingInst();

    // user help comms
    writeMessage("Input m to see the supported commands. " + System.lineSeparator());
    writeMessage("Input q to quit the program. " + System.lineSeparator());
  }

  /**
   * Prints the instructions for regarding the reference of the file in the program.
   *
   * @throws IllegalStateException if the messages cannot be printed.
   */
  protected void printSaveLoadInst() throws IllegalStateException {
    writeMessage("load image-path image-name: Load the image with the given image path and "
            + "refers to it with the given name" + System.lineSeparator());
    writeMessage("save image-path image-name: Save the image with the given name to the "
            + "specified" + " path which includes the name of the file" + System.lineSeparator());
    writeMessage("change-name old-name new-name: change the name of a loaded image to the new "
            + "provided name" + System.lineSeparator()
            + System.lineSeparator());
  }

  /**
   * Prints the instructions for the image processing methods in the program.
   *
   * @throws IllegalStateException if the messages cannot be printed.
   */
  protected void printImageProcessingInst() throws IllegalStateException {
    writeMessage("Greyscale commands:" + System.lineSeparator());
    writeMessage("red-component image-name dest-image-name: Create a greyscale image with the "
            + "red" + " component of the image with the given name" + System.lineSeparator());
    writeMessage("green-component image-name dest-image-name: Create a greyscale image with the "
            + "green component of the image with the given name" + System.lineSeparator());
    writeMessage("blue-component image-name dest-image-name: Create a greyscale image with the "
            + "blue component of the image with the given name" + System.lineSeparator());
    writeMessage("value image-name dest-image-name: Create a greyscale image with the maximum "
            + "value of the three component for each pixel" + System.lineSeparator());
    writeMessage("intensity image-name dest-image-name: Create a greyscale image with the "
            + "average of the three components for each pixel" + System.lineSeparator());
    writeMessage("luma image-name dest-image-name: Create a greyscale image with the weighted "
            + "sum 0.2126r + 0.7152g + 0.0722b" + System.lineSeparator() + System.lineSeparator());
    writeMessage("Flip image commands:" + System.lineSeparator());
    writeMessage("horizontal-flip image-name dest-image-name: Flip an image horizontally to "
            + "create a new image" + System.lineSeparator());
    writeMessage("vertical-flip image-name dest-image-name: Flip an image vertically to "
            + "create a new image" + System.lineSeparator() + System.lineSeparator());
    writeMessage("Brightness command:" + System.lineSeparator());
    writeMessage("brighten image-name dest-image-name increment: brighten the image by the given "
            + "increment to create a new image. Positive value will brighten the image and "
            + "negative value will darken the image" + System.lineSeparator()
            + System.lineSeparator());
  }
}
