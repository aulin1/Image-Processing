import controller.IPFeatureImpl;
import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import controller.UpdatedIPController;
import model.ImageProcessingModel;
import model.UpdatedProcessingModel;
import view.ImageProcessingGUI;
import view.ImageProcessingView;

import static controller.ImageUtil.parseTXT;

/**
 * This class represents the main access to the image processing program.
 */
public final class ImageProcessing {
  /**
   * Starts the image processing program.
   *
   * @param args the command line inputs
   */
  public static void main(String[] args) {
    if (args.length > 0) { //designating the version of the controller to be used
      ImageProcessingController controller;
      switch (args[0]) {
        case "-old":
          controller = new ImageProcessingControllerImpl();
          break;
        case "-file": //reading a text file
          if (args.length == 1) {
            throw new IllegalArgumentException("Not enough inputs!");
          }
          String text = args[1];
          Readable commands = parseTXT(text);
          controller = new UpdatedIPController(System.out, commands,
                  new UpdatedProcessingModel());
          break;
        case "-text":
          controller = new UpdatedIPController();
          break;
        default: //not an accepted input
          throw new IllegalArgumentException("Not an accepted input!");
      }
      controller.start();
    } else {
      //The length can't be less than 0, so this is if there are not other arguments.
      ImageProcessingModel model = new UpdatedProcessingModel();
      ImageProcessingView view = new ImageProcessingGUI(model);
      IPFeatureImpl controller = new IPFeatureImpl(model, view);
      view.makeVisible();
    }

  }
}
