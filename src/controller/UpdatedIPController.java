package controller;

import java.io.InputStreamReader;

import model.GaussianBlurCommand;
import model.ImageProcessingModel;
import model.ImageSharpenCommand;
import model.LumaCommand;
import model.SepiaToneCommand;
import model.UpdatedProcessingModel;

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
    super(System.out, new InputStreamReader(System.in), new UpdatedProcessingModel());
  }

  /**
   * Creates a new controller for the Image Processing Program.
   *
   * @param output the desired output of the messages
   * @param input  the desired input of the user's interaction
   * @throws IllegalArgumentException if any of the field is null
   */
  public UpdatedIPController(Appendable output, Readable input, ImageProcessingModel view)
          throws IllegalArgumentException {
    super(output, input, view);
  }

  @Override
  protected void initiateComms() {
    super.initiateComms();
    this.imgProcCommandMap.put("greyscale", s -> new LumaCommand());
    this.imgProcCommandMap.put("gaussian-blur", s -> new GaussianBlurCommand());
    this.imgProcCommandMap.put("sharpen", s -> new ImageSharpenCommand());
    this.imgProcCommandMap.put("sepia", s -> new SepiaToneCommand());
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
