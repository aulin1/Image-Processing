package controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import command.ImageProcessingCommand;
import command.RedCompCommand;
import model.ImageProcessingModel;

import static controller.ImageUtil.readPPM;

/**
 * This class represents the implementation of the Image Processing Controller.
 */
public class ImageProcessingControllerImpl implements ImageProcessingController {
  protected final Map<String, Function<Scanner, ImageProcessingCommand>> commandMap;
  private final Appendable output;
  private final Readable input;
  private final Map<String, ImageProcessingModel> memory = new HashMap<>();


  /**
   * Default constructor for image processing controller with input from the keyboard and output
   * to the user's console.
   */
  public ImageProcessingControllerImpl() throws IllegalArgumentException {
    this(System.out, new InputStreamReader(System.in));
  }

  /**
   * Creates a new controller for the Image Processing Program.
   *
   * @param output the desired output of the messages
   * @param input  the desired input of the user's interaction
   * @throws IllegalArgumentException if any of the field is null
   */
  public ImageProcessingControllerImpl(Appendable output, Readable input) throws IllegalArgumentException {
    if (input == null || output == null) {
      throw new IllegalArgumentException("The fields to the controller constructor cannot be " +
              "null" + ". ");
    } else {
      this.output = output;
      this.input = input;
      this.commandMap = new HashMap<>();
      initiateComms();
    }
  }

  @Override
  public void start() { //FIXME: transmit exceptions to the user instead of force quitting the
                        // program.
    Scanner sc = new Scanner(input);

    this.welcomeMessage();
    while (sc.hasNext()) {
      String s = sc.next();

      switch (s) {
        case "load":
          loadImage(sc.next(), sc.next());
          break;
        case "save":
          saveImage(sc.next(), sc.next());
          break;
        case "q":
          writeMessage("Thank you for using the program!");
          return;
        default:
          Function<Scanner, ImageProcessingCommand> commandFunc;
          commandFunc = commandMap.getOrDefault(s, null);
          String imageName = sc.next();
          String destImageName = sc.next();
          // try to find the command
          if (commandFunc == null) {
            writeMessage("Command is not supported. ");
          } else {
            // try to find the file based on the file name
            ImageProcessingModel model = memory.getOrDefault(imageName, null);
            if (model == null) {
              writeMessage("The image has yet loaded to the program. Please load a valid image "
                      + "before processing it. ");
            } else {
              ImageProcessingCommand command = commandFunc.apply(sc);
              ImageProcessingModel processedModel = command.execute(model);
              writeMessage("command executed. ");
              // saves new model with designated name
              memory.put(destImageName, processedModel);
              writeMessage("Command successfully processed!");
            }
          }
      }
    }
    throw new IllegalStateException("The program ran out of arguments even though the program "
            + "has not quit. ");
  }

  // initiates the commands into the command map
  private void initiateComms() {
    this.commandMap.put("red-component", s -> new RedCompCommand());
  }

  // loads an image from the designated image path and put it in the memory map with the designated
  // name
  private void loadImage(String imagePath, String imageName) {
    try {
      ImageProcessingModel model = readPPM(imagePath);
      memory.put(imageName, model);
      writeMessage("Load image " + imageName + " successful!");
    } catch (IllegalArgumentException e) {
      writeMessage(e.getMessage());
    }
  }

  // saves an image with the given name to the specified path which should include the name of
  // the file
  //TODO: CHECK SAVING IMAGE FOR WINDOWS
  private void saveImage(String imagePath, String imageName) {
    ImageProcessingModel model = memory.getOrDefault(imageName, null);
    if (model == null) {
      writeMessage(imageName + "have yet loaded to the program. ");
      return;
    }

    try {
      PrintWriter outfile = new PrintWriter(imagePath + "/" + imageName + ".ppm");
      System.out.println("Writing out to file: " + imageName + ".ppm");
      outfile.println("P3");
      outfile.println("# Image created by Trang Do and Audrey Lin's program");
      outfile.println(model.getWidth() + " " + model.getHeight());
      outfile.println(255); //FIXME: use the max value returned from the model instead

      int[][][] imageBoard = model.getImage();
      for (int r = 0; r < model.getHeight(); r++) {
        for (int c = 0; c < model.getWidth(); c++) {
          outfile.println(imageBoard[c][r][0]); // print red value
          outfile.println(imageBoard[c][r][1]); // print green value
          outfile.println(imageBoard[c][r][2]); // print blue value
        }
      }
      outfile.close();
      writeMessage("Save image " + imageName + " successful!");
    } catch (Exception e) {
      writeMessage("Unable to save file to destination. ");
    }
  }

  // writes the message to the designated output
  private void writeMessage(String message) throws IllegalStateException {
    try {
      output.append(message);

    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  private void welcomeMessage() throws IllegalStateException {
    writeMessage("Welcome to Image Processing Program!" + System.lineSeparator());
    printInstructions();
  }

  private void printInstructions() throws IllegalStateException {
    writeMessage("Supported commands in this program:" + System.lineSeparator());
    writeMessage("save image-path image-name: Save the image with the given name to the " +
            "specified" + " path which includes the name of the file" + System.lineSeparator()
            + System.lineSeparator());
    writeMessage("All commands below will be referred by the designated destination name " +
            "after the command by the program:" + System.lineSeparator());
    writeMessage("Greyscale commands:" + System.lineSeparator());
    writeMessage("red-component image-name dest-image-name: Create a greyscale image with the " +
            "red" + " component of the image with the given name" + System.lineSeparator());
    writeMessage("green-component image-name dest-image-name: Create a greyscale image with the "
            + "green component of the image with the given name" + System.lineSeparator());
    writeMessage("blue-component image-name dest-image-name: Create a greyscale image with the "
            + "blue component of the image with the given name" + System.lineSeparator()
            + System.lineSeparator());
    writeMessage("Flip image commands:" + System.lineSeparator());
    writeMessage("horizontal-flip image-name dest-image-name: Flip an image horizontally to " +
            "create a new image" + System.lineSeparator());
    writeMessage("vertical-flip image-name dest-image-name: Flip an image vertically to " +
            "create a new image" + System.lineSeparator() + System.lineSeparator());
    writeMessage("Brightness command:" + System.lineSeparator());
    writeMessage("brighten increment image-name dest-image-name: brighten the image by the given "
            + "increment to create a new image. Positive value will brighten the image and " +
            "negative value will darken the image" + System.lineSeparator()
            + System.lineSeparator());
    writeMessage("Input m to see the supported commands. " + System.lineSeparator());
    writeMessage("Input q to quit the program. " + System.lineSeparator());
  }
}
