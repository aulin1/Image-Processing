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
    if (args.length > 0) { //designating the version of the controller to be used
      switch (args[0]) {
        case "-old":
          controller = new ImageProcessingControllerImpl();
          break;
        case "-file": //reading a text file
          if (args.length == 1) {
            throw new IllegalArgumentException("Not enough inputs!");
          }
          String text = args[1];
          String[] textArr = text.split("\\.");
          if (!textArr[1].equals("txt")) {
            throw new IllegalArgumentException("The provided file is not a txt file.");
          } else {
            try {
              FileInputStream fileInputStream = new FileInputStream(text);
              InputStreamReader streamReader = new InputStreamReader(fileInputStream);
              controller = new UpdatedIPController(System.out, streamReader,
                      new UpdatedProcessingModel());
            } catch (FileNotFoundException e) {
              throw new IllegalArgumentException("Cannot find the file.");
            }
          }
          break;
        case "-text":
          controller = new UpdatedIPController();
          break;
        default: //not an accepted input
          throw new IllegalArgumentException("Not an accepted input!");
      }
    } else { //TODO: add GUI once implemented
      //The length can't be less than 0, so this is if there are not other arguments.
      controller = new UpdatedIPController();
    }
    controller.start();
  }
}
