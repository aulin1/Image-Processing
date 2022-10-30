import controller.ImageProcessingControllerImpl;

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
    ImageProcessingControllerImpl controller = new ImageProcessingControllerImpl();
    controller.start();
  }
}
