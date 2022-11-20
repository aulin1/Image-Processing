package controller;

import java.io.InputStreamReader;

import model.ImageProcessingModel;
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
    initiateComms();
  }

  /**
   * Creates a new controller for the Image Processing Program.
   *
   * @param output the desired output of the messages
   * @param input  the desired input of the user's interaction
   * @param model  the desired model to handle the storage of images
   * @throws IllegalArgumentException if any of the field is null
   */
  public UpdatedIPController(Appendable output, Readable input, ImageProcessingModel model)
          throws IllegalArgumentException {
    super(output, input, model);
    initiateComms();
  }

  @Override
  protected void printImageProcessingInst() {
    super.printImageProcessingInst();
    writeMessage("New supported methods!" + System.lineSeparator());
    writeMessage("gaussian-blur image-name dest-image-name: Create a gaussian-blurred image with "
            + "the image of the given name" + System.lineSeparator());
    writeMessage("greyscale image-name dest-image-name: Create a greyscale image with the image "
            + "of the given name" + System.lineSeparator());
    writeMessage("sharpen image-name dest-image-name: Create a sharpened image with the image "
            + "of the given name" + System.lineSeparator());
    writeMessage("sepia image-name dest-image-name: Create a sepia version of the image "
            + "of the given name" + System.lineSeparator() + System.lineSeparator());
  }
}
