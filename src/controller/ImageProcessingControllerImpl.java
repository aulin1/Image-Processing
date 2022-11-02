package controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import command.BlueCompCommand;
import command.BrightnessCommand;
import command.ChangeNameCommand;
import command.GreenCompCommand;
import command.HorizontalFlipCommand;
import command.ImageProcessingCommand;
import command.IntensityCommand;
import command.LoadCommand;
import command.LumaCommand;
import command.RedCompCommand;
import command.SaveCommand;
import command.ValueCommand;
import command.VerticalFlipCommand;
import model.ImageProcessingModel;
import view.ImageProcessingView;
import view.PPMProcessingView;

/**
 * This class represents the implementation of the Image Processing Controller.
 */
public class ImageProcessingControllerImpl implements ImageProcessingController {
  protected final Map<String, Function<Scanner, ImageProcessingCommand>> commandMap;
  private final Appendable output;
  private final Readable input;
  private final ImageProcessingView view;


  /**
   * Default constructor for image processing controller with input from the keyboard and output
   * to the user's console.
   */
  public ImageProcessingControllerImpl() throws IllegalArgumentException {
    this(System.out, new InputStreamReader(System.in), new PPMProcessingView());
  }

  /**
   * Creates a new controller for the Image Processing Program.
   *
   * @param output the desired output of the messages
   * @param input  the desired input of the user's interaction
   * @throws IllegalArgumentException if any of the field is null
   */
  public ImageProcessingControllerImpl(Appendable output, Readable input, ImageProcessingView view)
          throws IllegalArgumentException {
    if (input == null || output == null) {
      throw new IllegalArgumentException("The fields to the controller"
              + " constructor cannot be null.");
    }
    this.output = output;
    this.input = input;
    this.commandMap = new HashMap<>();
    this.view = view;
    initiateComms();
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
   * A helper function which deals with command reading.
   *
   * @param s the string that represents the command.
   * @param sc the scanner which would read additional parameters as necessary.
   * */
  private void readComm(String s, Scanner sc){
    Function<Scanner, ImageProcessingCommand> commandFunc;
    commandFunc = commandMap.getOrDefault(s, null);
    if (commandFunc == null) {
      writeMessage("Command is not supported." + System.lineSeparator());
    } else {
      if (s.equals("load") || s.equals("save") || s.equals("change-name")) {
        ImageProcessingCommand command = commandFunc.apply(sc);
        try {
          command.execute(view);
          writeMessage("Command " + s + " successfully processed!" + System.lineSeparator());
        } catch (Exception e) {
          writeMessage(e.getMessage() + System.lineSeparator());
        }
      } else {
        String imageName = sc.next();
        String destImageName = sc.next();
        // try to find the file based on the file name
        ImageProcessingModel model = view.getModel(imageName);
        if (model == null) {
          writeMessage("The image has yet loaded to the program. Please load a valid image "
                  + "before processing it." + System.lineSeparator());
        } else {
          ImageProcessingCommand command = commandFunc.apply(sc);
          ImageProcessingModel processedModel = command.execute(model);
          // saves new model with designated name
          view.storeImage(destImageName, processedModel);
          writeMessage("Command " + s + " successfully processed!" + System.lineSeparator());
        }
      }
    }
  }

  /**
   * A helper function which initiates the commands into the map.
   */
  private void initiateComms() {
    this.commandMap.put("red-component", s -> new RedCompCommand());
    this.commandMap.put("green-component", s -> new GreenCompCommand());
    this.commandMap.put("blue-component", s -> new BlueCompCommand());
    this.commandMap.put("value", s -> new ValueCommand());
    this.commandMap.put("luma", s -> new LumaCommand());
    this.commandMap.put("intensity", s -> new IntensityCommand());
    this.commandMap.put("horizontal-flip", s -> new HorizontalFlipCommand());
    this.commandMap.put("vertical-flip", s -> new VerticalFlipCommand());
    this.commandMap.put("brighten", s -> new BrightnessCommand(s.nextInt()));
    this.commandMap.put("save", s -> new SaveCommand(s.next(), s.next()));
    this.commandMap.put("load", s -> new LoadCommand(s.next(), s.next()));
    this.commandMap.put("change-name", s -> new ChangeNameCommand(s.next(), s.next()));
  }

  /**
   * A helper function which writes a message to the appendable output.
   *
   * @param message the message to be written.
   * @throws IllegalStateException if the message cannot be appended.
   */
  private void writeMessage(String message) throws IllegalStateException {
    try {
      output.append(message);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
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
    writeMessage("load image-path image-name: Load the image with the given image path and " +
            "refers to it with the given name" + System.lineSeparator());
    writeMessage("save image-path image-name: Save the image with the given name to the " +
            "specified" + " path which includes the name of the file" + System.lineSeparator());
    writeMessage("change-name old-name new-name: change the name of a loaded image to the new " +
            "provided name" + System.lineSeparator()
            + System.lineSeparator());
    writeMessage("All commands below will be referred by the designated destination name " +
            "after the command by the program:" + System.lineSeparator());
    // grey scale commands
    writeMessage("Greyscale commands:" + System.lineSeparator());
    writeMessage("red-component image-name dest-image-name: Create a greyscale image with the " +
            "red" + " component of the image with the given name" + System.lineSeparator());
    writeMessage("green-component image-name dest-image-name: Create a greyscale image with the "
            + "green component of the image with the given name" + System.lineSeparator());
    writeMessage("blue-component image-name dest-image-name: Create a greyscale image with the "
            + "blue component of the image with the given name" + System.lineSeparator());
    writeMessage("value image-name dest-image-name: Create a greyscale image with the maximum " +
            "value of the three component for each pixel" + System.lineSeparator());
    writeMessage("intensity image-name dest-image-name: Create a greyscale image with the " +
            "average of the three components for each pixel" + System.lineSeparator());
    writeMessage("luma image-name dest-image-name: Create a greyscale image with the weighted " +
            "sum 0.2126r + 0.7152g + 0.0722b" + System.lineSeparator() + System.lineSeparator());
    // flip image commands
    writeMessage("Flip image commands:" + System.lineSeparator());
    writeMessage("horizontal-flip image-name dest-image-name: Flip an image horizontally to " +
            "create a new image" + System.lineSeparator());
    writeMessage("vertical-flip image-name dest-image-name: Flip an image vertically to " +
            "create a new image" + System.lineSeparator() + System.lineSeparator());
    writeMessage("Brightness command:" + System.lineSeparator());
    writeMessage("brighten image-name dest-image-name increment: brighten the image by the given "
            + "increment to create a new image. Positive value will brighten the image and " +
            "negative value will darken the image" + System.lineSeparator()
            + System.lineSeparator());
    // user help comms
    writeMessage("Input m to see the supported commands. " + System.lineSeparator());
    writeMessage("Input q to quit the program. " + System.lineSeparator());
  }
}
