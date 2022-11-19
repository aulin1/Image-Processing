package controller;

import java.util.HashMap;
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
import view.ImageProcessingView;

/**
 * This class is an implementation of the IPFeature interface which handles inputs from the view.
 */
public class IPFeatureImpl implements IPFeature {
  private final ImageProcessingModel model;
  private final ImageProcessingView view;
  protected final Map<String, Function<Scanner, ImageProcessingCommand>> imgProcCommandMap;
  protected final Map<String, Function<Scanner, ModelCommand>> modelCommandMap;

  /**
   * Constructs a new IPFeatureImpl given the model and the view.
   * @param model the model which receives the instruction from this controller
   * @param view the view which this controller receives the input from
   * @throws IllegalArgumentException if either the model or the view is null
   */
  public IPFeatureImpl(ImageProcessingModel model, ImageProcessingView view) throws IllegalArgumentException {
    if (model == null || view == null) {
      throw new IllegalArgumentException("The arguments cannot be null.");
    }
    this.model = model;
    this.view = view;
    this.imgProcCommandMap = new HashMap<>();
    this.modelCommandMap = new HashMap<>();
    initiateComms();

    this.view.registerFeature(this);
  }

  @Override
  public void processInput(String input) {
    Scanner sc = new Scanner(input);
    while (sc.hasNext()) {
      String s = sc.next();
      readComm(s.toLowerCase(), sc);
    }
    this.view.renderMessage("Error: Insufficient args.");
  }

  //TODO: figure out current code duplication
  protected void initiateComms() {
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
    this.imgProcCommandMap.put("greyscale", s -> new LumaCommand());
    this.imgProcCommandMap.put("gaussian-blur", s -> new GaussianBlurCommand());
    this.imgProcCommandMap.put("sharpen", s -> new ImageSharpenCommand());
    this.imgProcCommandMap.put("sepia", s -> new SepiaToneCommand());
  }

  /**
   * A helper function which deals with command reading.
   *
   * @param s  the string that represents the command.
   * @param sc the scanner which would read additional parameters as necessary.
   */
  private void readComm(String s, Scanner sc) {
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
      this.view.renderMessage("Command is not supported." + System.lineSeparator());
    }
  }


  /**
   * A helper function that runs a model command.
   *
   * @param command the command to be run.
   * @param s the string that calls the command.
   * @return the Image that is currently being processed
   */
  private void modelCommandFunc(ModelCommand command, String s) {
    ImageClass image;
    try {
      image = command.execute(model);
      this.view.renderMessage("Command " + s + " successfully processed!" + System.lineSeparator());
      this.view.renderImage(image);
    } catch (Exception e) {
      this.view.renderMessage(e.getMessage() + System.lineSeparator());
    }
  }

  /**
   * A helper function that runs an image processing command.
   *
   * @param sc             the scanner to get in additional information
   * @param imgProCommFunc the map of functions for the ImageProcessingCommands.
   * @param s              the string that represents the command.
   */
  private void imgProcCommand(Scanner sc, Function<Scanner, ImageProcessingCommand> imgProCommFunc,
                              String s) {
    String imageName = sc.next();
    String destImageName = sc.next();
    // try to find the file based on the file name
    ImageClass img = this.model.getImage(imageName);
    if (img == null) {
      this.view.renderMessage("The image has yet loaded to the program. Please load a valid image "
              + "before processing it." + System.lineSeparator());
    } else {
      ImageProcessingCommand command = imgProCommFunc.apply(sc);
      ImageClass processedModel = command.execute(img);
      // saves new model with designated name
      model.storeImage(destImageName, processedModel);
      this.view.renderMessage("Command " + s + " successfully processed!" + System.lineSeparator());
      this.view.renderImage(processedModel);
    }
  }
}
