package controller;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import image.ImageClass;
import model.BlueCompCommand;
import model.BrightnessCommand;
import model.GaussianBlurCommand;
import model.GreenCompCommand;
import model.HorizontalFlipCommand;
import model.ImageProcessingCommand;
import model.ImageProcessingModel;
import model.ImageSharpenCommand;
import model.IntensityCommand;
import model.LumaCommand;
import model.RedCompCommand;
import model.SepiaToneCommand;
import model.ValueCommand;
import model.VerticalFlipCommand;

/**
 * An abstract class that deals the command utilities.
 * */
abstract class ControllerCommandUtil {
  protected Map<String, Function<Scanner, ImageProcessingCommand>> imgProcCommandMap;
  protected Map<String, Function<Scanner, ModelCommand>> modelCommandMap;
  protected ImageProcessingModel model;

  /**
   * A helper function which deals with command reading.
   *
   * @param s  the string that represents the command.
   * @param sc the scanner which would read additional parameters as necessary.
   */
  protected void readComm(String s, Scanner sc) {
    Function<Scanner, ModelCommand> modelCommFunc =
            this.modelCommandMap.getOrDefault(s, null);
    Function<Scanner, ImageProcessingCommand> imgProCommFunc =
            this.imgProcCommandMap.getOrDefault(s, null);

    if (modelCommFunc != null) {
      ModelCommand command = modelCommFunc.apply(sc);
      modelCommandFunc(command, s);
    }

    if (imgProCommFunc != null) {
      imgProcCommand(sc, imgProCommFunc, s);
    }

    if (modelCommFunc == null && imgProCommFunc == null) {
      writeMessage("Command is not supported." + System.lineSeparator());
    }
  }

  /**
   * A helper function which writes a message to the user.
   *
   * @param message the message to be written.
   */
  abstract void writeMessage(String message);

  /**
   * A helper function that runs a model command.
   *
   * @param command the command to be run.
   * @String s the string that calls the command.
   */
  protected void modelCommandFunc(ModelCommand command, String s) {
    ImageClass img;
    try {
      img = command.execute(model);
      writeMessage("Command " + s + " successfully processed!" + System.lineSeparator());
      renderImage(img);
    } catch (Exception e) {
      //TODO: figure out what's erroring
      writeMessage(e.getMessage() + System.lineSeparator());
    }
  }

  /**
   * A helper function that runs an image processing command.
   *
   * @param sc             the scanner to get in additional information
   * @param imgProCommFunc the map of functions for the ImageProcessingCommands.
   * @param s              the string that represents the command.
   */
  protected void imgProcCommand(Scanner sc, Function<Scanner,
          ImageProcessingCommand> imgProCommFunc, String s) {
    String imageName = sc.next();
    String destImageName = sc.next();
    // try to find the file based on the file name
    ImageClass img = this.model.getImage(imageName);
    if (img == null) {
      writeMessage("The image has yet loaded to the program. Please load a valid image "
              + "before processing it." + System.lineSeparator());
    } else {
      ImageProcessingCommand command = imgProCommFunc.apply(sc);
      ImageClass processedModel = command.execute(img);
      // saves new model with designated name
      model.storeImage(destImageName, processedModel);
      writeMessage("Command " + s + " successfully processed!" + System.lineSeparator());
      renderImage(processedModel);
    }
  }

  /**
   * A helper function which initiates the commands into the map.
   */
  protected void initiateCommsOld() {
    this.imgProcCommandMap.put("red-component", s -> new RedCompCommand());
    this.imgProcCommandMap.put("green-component", s -> new GreenCompCommand());
    this.imgProcCommandMap.put("blue-component", s -> new BlueCompCommand());
    this.imgProcCommandMap.put("value", s -> new ValueCommand());
    this.imgProcCommandMap.put("luma", s -> new LumaCommand());
    this.imgProcCommandMap.put("intensity", s -> new IntensityCommand());
    this.imgProcCommandMap.put("horizontal-flip", s -> new HorizontalFlipCommand());
    this.imgProcCommandMap.put("vertical-flip", s -> new VerticalFlipCommand());
    this.imgProcCommandMap.put("brighten", s -> new BrightnessCommand(s.nextInt()));
    this.modelCommandMap.put("save", s -> new SaveCommand(s.next(), s.next()));
    this.modelCommandMap.put("load", s -> new LoadCommand(s.next(), s.next()));
    this.modelCommandMap.put("change-name", s -> new ChangeNameCommand(s.next(), s.next()));
  }

  /**
   * A helper function which initiates all of the commands into the map.
   * */
  protected void initiateComms(){
    initiateCommsOld();
    this.imgProcCommandMap.put("greyscale", s -> new LumaCommand());
    this.imgProcCommandMap.put("gaussian-blur", s -> new GaussianBlurCommand());
    this.imgProcCommandMap.put("sharpen", s -> new ImageSharpenCommand());
    this.imgProcCommandMap.put("sepia", s -> new SepiaToneCommand());
  }

  /**
   * A helper function which renders an image.
   *
   * @param img the image to be rendered.
   * */
  abstract void renderImage(ImageClass img);

}
