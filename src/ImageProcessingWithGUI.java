import controller.IPFeatureImpl;
import model.ImageProcessingModel;
import model.UpdatedProcessingModel;
import view.ImageProcessingGUI;
import view.ImageProcessingView;

/**
 * Start the Image Processing Program with the supported GUI.
 */
public class ImageProcessingWithGUI {
  /**
   * Starts the image processing program.
   *
   * @param args the command line inputs
   */
  public static void main(String[] args) {
    ImageProcessingModel model = new UpdatedProcessingModel();
    ImageProcessingView view = new ImageProcessingGUI(model);
    IPFeatureImpl controller = new IPFeatureImpl(model, view);
    view.makeVisible();
  }
}
