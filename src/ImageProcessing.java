import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import controller.UpdatedIPController;

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
    ImageProcessingController controller;
    if (args.length > 0) {
      switch (args[0]) {
        case "old" :
          controller = new ImageProcessingControllerImpl();
          break;
        case "new" :
          controller = new UpdatedIPController();
          break;
        default:
          throw new IllegalArgumentException("Inputted argument not supported.");
      }
      controller.start();
    }
  }
}
