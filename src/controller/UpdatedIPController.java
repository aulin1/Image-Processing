package controller;

import java.io.InputStreamReader;

import command.GaussianBlurCommand;
import command.ImageSharpenCommand;
import command.LoadCommand;
import command.LumaCommand;
import command.SepiaToneCommand;
import view.UpdatedProcessingView;

/**
 * <p>This class represented an Image Processing Controller which supports all functionality from
 * ImageProcessingControllerImpl and new methods.
 * </p>
 *
 * <p>New functionalities supported by this controller: greyscale, gaussian blur, sharpen and sepia
 * tone.</p>
 */
public class UpdatedIPController extends ImageProcessingControllerImpl {

  /**
   * Create the default controller for the image processing program using the new updated
   * processing view.
   */
  public UpdatedIPController() {
    super(System.out, new InputStreamReader(System.in), new UpdatedProcessingView());
  }

  @Override
  protected void initiateComms() {
    super.initiateComms();
    this.commandMap.put("greyscale", s -> new LumaCommand());
    this.commandMap.put("gaussian-blur", s -> new GaussianBlurCommand());
    this.commandMap.put("sharpen", s -> new ImageSharpenCommand());
    this.commandMap.put("sepia", s -> new SepiaToneCommand());
    // universal save and load
    this.commandMap.put("load", s -> new LoadCommand(s.next(), s.next()));
  }

  @Override
  protected void printImageProcessingInst() {
    super.printImageProcessingInst();
    writeMessage("New supported methods!" + System.lineSeparator());
    writeMessage("gaussian-blur image-name dest-image-name: Create a gaussian-blurred image with " +
            "the image of the given name" + System.lineSeparator());
    writeMessage("greyscale image-name dest-image-name: Create a greyscale image with the image " +
            "of the given name" + System.lineSeparator());
    writeMessage("sharpen image-name dest-image-name: Create a sharpened image with the image " +
            "of the given name" + System.lineSeparator());
    writeMessage("sepia image-name dest-image-name: Create a sepia version of the image " +
            "of the given name" + System.lineSeparator() + System.lineSeparator());
  }
}