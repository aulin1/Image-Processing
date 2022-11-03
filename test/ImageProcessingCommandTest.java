import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import command.BlueCompCommand;
import command.BrightnessCommand;
import command.ChangeNameCommand;
import command.GreenCompCommand;
import command.HorizontalFlipCommand;
import command.ImageProcessingCommand;
import command.IntensityCommand;
import command.LoadCommand;
import command.LumaCommand;
import command.RedCompCommand;
import command.SaveCommand;
import command.ValueCommand;
import command.VerticalFlipCommand;
import model.ImageProcessingModel;
import model.PPMProcessingModel;
import view.ImageProcessingView;
import view.PPMProcessingView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class represents tests for all classes which implements the interface
 * ImageProcessingCommand.
 */
public class ImageProcessingCommandTest {
  List<Function<String[], ImageProcessingCommand>> listCommsTwoArgs = // support execute(view)
          new ArrayList<>(Arrays.asList((String[] l) -> new SaveCommand(l[0], l[1]),
              (String[] l) -> new LoadCommand(l[0], l[1]),
              (String[] l) -> new ChangeNameCommand(l[0], l[1])));
  List<ImageProcessingCommand> listCommsSupportModel =
          // does not have BrightnessComm, test separately
          new ArrayList<>(Arrays.asList(new BlueCompCommand(), new GreenCompCommand(),
              new RedCompCommand(), new IntensityCommand(), new LumaCommand(),
              new ValueCommand(), new HorizontalFlipCommand(), new VerticalFlipCommand()));

  /**
   * Test commands with two String fields do not take null in the first argument.
   */
  @Test
  public void testCommandThrowExceptionNullFirstArg() {
    String[] arr = new String[]{null, "bool"};

    for (Function<String[], ImageProcessingCommand> commandFunction : listCommsTwoArgs) {
      try {
        ImageProcessingCommand command = commandFunction.apply(arr);
        fail("The command should not construct with first arg null");
      } catch (IllegalArgumentException e) {
        assertEquals("The arguments cannot be null. ", e.getMessage());
      }
    }
  }

  /**
   * Test commands with two String fields do not take null in the second argument.
   */
  @Test
  public void testCommandThrowExceptionNullSecondArg() {
    String[] arr = new String[]{"bool", null};

    for (Function<String[], ImageProcessingCommand> commandFunction : listCommsTwoArgs) {
      try {
        ImageProcessingCommand command = commandFunction.apply(arr);
        fail("The command should not construct with second arg null");
      } catch (IllegalArgumentException e) {
        assertEquals("The arguments cannot be null. ", e.getMessage());
      }
    }
  }

  /**
   * Test null model when using execute(model).
   */
  @Test
  public void CommThrowExceptionNullExeModel() {
    ImageProcessingModel model = null;
    for (ImageProcessingCommand command : listCommsSupportModel) {
      try {
        command.execute(model);
        fail("The command should not execute with null model");
      } catch (IllegalArgumentException e) {
        assertEquals("The model cannot be null", e.getMessage());
      }
    }
  }

  /**
   * Test null model when using execute(model) on BrightnessCommand.
   */
  @Test
  public void BrightCommThrowExceptionNullExeModel() {
    ImageProcessingModel model = null;
    try {
      ImageProcessingCommand command = new BrightnessCommand(10);
      command.execute(model);
      fail("Brightness command should not execute with null model.");
    } catch (IllegalArgumentException e) {
      assertEquals("The model cannot be null", e.getMessage());
    }
  }

  /**
   * Test commands that does not support execute(view) throw exception.
   */
  @Test
  public void CommThrowExceptionNotSupportedExeView() {
    ImageProcessingView view = new PPMProcessingView();

    for (ImageProcessingCommand command : listCommsSupportModel) {
      try {
        command.execute(view);
        fail("The command should not execute unsupported method");
      } catch (UnsupportedOperationException e) {
        assertEquals("This method is not supported by this command object.", e.getMessage());
      }
    }
  }

  /**
   * Test BrightnessCommand does not support execute(view) and throw exception.
   */
  @Test
  public void BrightCommThrowExceptionUnsupportedExeView() {
    ImageProcessingView view = new PPMProcessingView();

    try {
      ImageProcessingCommand command = new BrightnessCommand(10);
      command.execute(view);
      fail("Brightness command should not support execute(view)");
    } catch (UnsupportedOperationException e) {
      assertEquals("This method is not supported by this command object.", e.getMessage());
    }
  }

  /**
   * Test null view.
   */
  @Test
  public void CommThrowExceptionNullExeView() {
    String[] arr = new String[]{"bool", "foo"};
    ImageProcessingView view = null;

    for (Function<String[], ImageProcessingCommand> commandFunction : listCommsTwoArgs) {
      try {
        ImageProcessingCommand command = commandFunction.apply(arr);
        command.execute(view);
        fail("The command should not execute with null view");
      } catch (IllegalArgumentException e) {
        assertEquals("The view cannot be null", e.getMessage());
      }
    }
  }

  /**
   * Test commands that does not support execute(model) throw exception.
   */
  @Test
  public void CommThrowExceptionNotSupportedExeModel() {
    String[] arr = new String[]{"bool", "foo"};
    ImageProcessingModel model = new PPMProcessingModel(new int[120][100][3], 255);

    for (Function<String[], ImageProcessingCommand> commandFunction : listCommsTwoArgs) {
      try {
        ImageProcessingCommand command = commandFunction.apply(arr);
        command.execute(model);
        fail("The command should not execute unsupported method.");
      } catch (UnsupportedOperationException e) {
        assertEquals("This method is not supported by this command object.", e.getMessage());
      }
    }
  }
}