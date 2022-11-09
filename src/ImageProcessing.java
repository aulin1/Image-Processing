import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import controller.UpdatedIPController;
import model.UpdatedProcessingModel;

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
    if (args.length == 0) { // default controller is the new version
      controller = new UpdatedIPController();
      controller.start();
    }

    if (args.length > 0) { //designating the version of the controller to be used
      switch (args[0]) {
        case "old" :
          controller = new ImageProcessingControllerImpl();
          break;
        default: // reading a script txt
          String text = args[0];
          String[] textArr = text.split("\\.");
          if (!textArr[1].equals("txt")) {
            throw new IllegalArgumentException("The provided file is not a txt file.");
          } else {
            try {
              FileInputStream fileInputStream = new FileInputStream(text);
              InputStreamReader streamReader = new InputStreamReader(fileInputStream);
              controller = new UpdatedIPController(System.out, streamReader, new UpdatedProcessingModel());
            } catch (FileNotFoundException e) {
              throw new IllegalArgumentException("Cannot find the file.");
            }
          }
      }
      controller.start();
    }
  }
}
