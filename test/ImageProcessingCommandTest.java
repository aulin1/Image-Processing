import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import command.BlueCompCommand;
import command.BrightnessCommand;
import command.ChangeNameCommand;
import command.GaussianBlurCommand;
import command.GreenCompCommand;
import command.HorizontalFlipCommand;
import command.ImageProcessingCommand;
import command.ImageSharpenCommand;
import command.IntensityCommand;
import command.LoadPPMCommand;
import command.LumaCommand;
import command.RedCompCommand;
import command.SavePPMCommand;
import command.SepiaToneCommand;
import command.ValueCommand;
import command.VerticalFlipCommand;
import model.ImageProcessingModel;
import model.PPMProcessingModel;
import view.ImageProcessingView;
import view.PPMProcessingView;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class represents tests for all classes which implements the interface
 * ImageProcessingCommand.
 */
public class ImageProcessingCommandTest {

  private int[][][] img = new int[][][]
          {{{0, 0, 0}, {153, 217, 234}, {153, 217, 234}, {127, 127, 127}},
                  {{153, 217, 234}, {237, 28, 36}, {205, 85, 207}, {153, 217, 234}},
                  {{153, 217, 234}, {12, 102, 36}, {255, 242, 0}, {153, 217, 234}},
                  {{0, 162, 232}, {153, 217, 234}, {153, 217, 234}, {255, 255, 255}}};

  List<Function<String[], ImageProcessingCommand>> listCommsTwoArgs = // support execute(view)
          new ArrayList<>(Arrays.asList((String[] l) -> new SavePPMCommand(l[0], l[1]),
              (String[] l) -> new LoadPPMCommand(l[0], l[1]),
              (String[] l) -> new ChangeNameCommand(l[0], l[1])));
  List<ImageProcessingCommand> listCommsSupportModel =
          // does not have BrightnessComm, test separately
          new ArrayList<>(Arrays.asList(new BlueCompCommand(), new GreenCompCommand(),
              new RedCompCommand(), new IntensityCommand(), new LumaCommand(),
              new ValueCommand(), new HorizontalFlipCommand(), new VerticalFlipCommand(),
                  new GaussianBlurCommand(), new SepiaToneCommand(), new ImageSharpenCommand()));

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

  /**
   * Test RedCompCommand.
   * */
  @Test
  public void testRedCompCommand(){
    ImageProcessingCommand command = new RedCompCommand();
    ImageProcessingModel test = new PPMProcessingModel(img, 255);
    ImageProcessingModel result = command.execute(test);
    assertArrayEquals(new int[][][]{{{0, 0, 0}, {153, 153, 153}, {153, 153, 153}, {127, 127, 127}},
            {{153, 153, 153}, {237, 237, 237}, {205, 205, 205}, {153, 153, 153}},
            {{153, 153, 153}, {12, 12, 12}, {255, 255, 255}, {153, 153, 153}},
            {{0, 0, 0}, {153, 153, 153}, {153, 153, 153}, {255, 255, 255}}}, result.getImage());
  }

  /**
   * Test GreenCompCommand.
   * */
  @Test
  public void testGreenCompCommand(){
    ImageProcessingCommand command = new GreenCompCommand();
    ImageProcessingModel test = new PPMProcessingModel(img, 255);
    ImageProcessingModel result = command.execute(test);
    assertArrayEquals(new int[][][]{{{0, 0, 0}, {217, 217, 217}, {217, 217, 217}, {127, 127, 127}},
                    {{217, 217, 217}, {28, 28, 28}, {85, 85, 85}, {217, 217, 217}},
                    {{217, 217, 217}, {102, 102, 102}, {242, 242, 242}, {217, 217, 217}},
                    {{162, 162, 162}, {217, 217, 217}, {217, 217, 217}, {255, 255, 255}}},
            result.getImage());
  }

  /**
   * Test BlueCompCommand.
   * */
  @Test
  public void testBlueCompCommand(){
    ImageProcessingCommand command = new BlueCompCommand();
    ImageProcessingModel test = new PPMProcessingModel(img, 255);
    ImageProcessingModel result = command.execute(test);
    assertArrayEquals(new int[][][]{{{0, 0, 0}, {234, 234, 234}, {234, 234, 234}, {127, 127, 127}},
                    {{234, 234, 234}, {36, 36, 36}, {207, 207, 207}, {234, 234, 234}},
                    {{234, 234, 234}, {36, 36, 36}, {0, 0, 0}, {234, 234, 234}},
                    {{232, 232, 232}, {234, 234, 234}, {234, 234, 234}, {255, 255, 255}}},
            result.getImage());
  }

  /**
   * Test BrightnessCommand (positive and negative).
   * */
  @Test
  public void testBrightnessCommand(){
    ImageProcessingCommand command = new BrightnessCommand(100);
    ImageProcessingCommand command2 = new BrightnessCommand(-100);
    ImageProcessingModel test = new PPMProcessingModel(img, 255);
    ImageProcessingModel result = command.execute(test);
    ImageProcessingModel result2 = command2.execute(test);
    assertArrayEquals(new int[][][]
                    {{{100, 100, 100}, {253, 255, 255}, {253, 255, 255}, {227, 227, 227}},
                    {{253, 255, 255}, {255, 128, 136}, {255, 185, 255}, {253, 255, 255}},
                    {{253, 255, 255}, {112, 202, 136}, {255, 255, 100}, {253, 255, 255}},
                    {{100, 255, 255}, {253, 255, 255}, {253, 255, 255}, {255, 255, 255}}},
            result.getImage());
    assertArrayEquals(new int[][][]
                    {{{0, 0, 0}, {53, 117, 134}, {53, 117, 134}, {27, 27, 27}},
                    {{53, 117, 134}, {137, 0, 0}, {105, 0, 107}, {53, 117, 134}},
                    {{53, 117, 134}, {0, 2, 0}, {155, 142, 0}, {53, 117, 134}},
                    {{0, 62, 132}, {53, 117, 134}, {53, 117, 134}, {155, 155, 155}}},
            result2.getImage());
  }

  /**
   * Test HorizontalFlipCommand.
   * */
  @Test
  public void testHorizontalFlipCommand(){
    ImageProcessingCommand command = new HorizontalFlipCommand();
    ImageProcessingModel test = new PPMProcessingModel(img, 255);
    ImageProcessingModel result = command.execute(test);
    assertArrayEquals(new int[][][]{{{127, 127, 127}, {153, 217, 234}, {153, 217, 234}, {0, 0, 0}},
            {{153, 217, 234}, {205, 85, 207}, {237, 28, 36}, {153, 217, 234}},
            {{153, 217, 234}, {255, 242, 0}, {12, 102, 36}, {153, 217, 234}},
            {{255, 255, 255}, {153, 217, 234}, {153, 217, 234}, {0, 162, 232}}}, result.getImage());
  }

  /**
   * Test VerticalFlipCommand.
   * */
  @Test
  public void testVerticalFlipCommand(){
    ImageProcessingCommand command = new VerticalFlipCommand();
    ImageProcessingModel test = new PPMProcessingModel(img, 255);
    ImageProcessingModel result = command.execute(test);
    assertArrayEquals(new int[][][]
            {{{0, 162, 232}, {153, 217, 234}, {153, 217, 234}, {255, 255, 255}},
            {{153, 217, 234}, {12, 102, 36}, {255, 242, 0}, {153, 217, 234}},
            {{153, 217, 234}, {237, 28, 36}, {205, 85, 207}, {153, 217, 234}},
            {{0, 0, 0}, {153, 217, 234}, {153, 217, 234}, {127, 127, 127}}}, result.getImage());
  }

  /**
   * Test IntensityCommand.
   * */
  @Test
  public void testIntensityCommand(){
    ImageProcessingCommand command = new IntensityCommand();
    ImageProcessingModel test = new PPMProcessingModel(img, 255);
    ImageProcessingModel result = command.execute(test);
    assertArrayEquals(new int[][][]{{{0, 0, 0}, {201, 201, 201}, {201, 201, 201}, {127, 127, 127}},
            {{201, 201, 201}, {100, 100, 100}, {166, 166, 166}, {201, 201, 201}},
            {{201, 201, 201}, {50, 50, 50}, {166, 166, 166}, {201, 201, 201}},
            {{131, 131, 131}, {201, 201, 201}, {201, 201, 201}, {255, 255, 255}}}, result.getImage());
  }

  /**
   * Test LumaCommand.
   * */
  @Test
  public void testLumaCommand(){
    ImageProcessingCommand command = new LumaCommand();
    ImageProcessingModel test = new PPMProcessingModel(img, 255);
    ImageProcessingModel result = command.execute(test);
    assertArrayEquals(new int[][][]{{{0, 0, 0}, {205, 205, 205}, {205, 205, 205}, {127, 127, 127}},
            {{205, 205, 205}, {73, 73, 73}, {119, 119, 119}, {205, 205, 205}},
            {{205, 205, 205}, {78, 78, 78}, {227, 227, 227}, {205, 205, 205}},
            {{133, 133, 133}, {205, 205, 205}, {205, 205, 205}, {255, 255, 255}}}, result.getImage());
  }

  /**
   * Test ValueCommand.
   * */
  @Test
  public void testValueCommand(){
    ImageProcessingCommand command = new ValueCommand();
    ImageProcessingModel test = new PPMProcessingModel(img, 255);
    ImageProcessingModel result = command.execute(test);
    assertArrayEquals(new int[][][]{{{0, 0, 0}, {234, 234, 234}, {234, 234, 234}, {127, 127, 127}},
            {{234, 234, 234}, {237, 237, 237}, {207, 207, 207}, {234, 234, 234}},
            {{234, 234, 234}, {102, 102, 102}, {255, 255, 255}, {234, 234, 234}},
            {{232, 232, 232}, {234, 234, 234}, {234, 234, 234}, {255, 255, 255}}}, result.getImage());
  }

  /**
   * Test SepiaToneCommand.
   * */
  @Test
  public void testSepiaToneCommand(){
    ImageProcessingCommand command = new SepiaToneCommand();
    ImageProcessingModel test = new PPMProcessingModel(img, 255);
    ImageProcessingModel result = command.execute(test);
    assertArrayEquals(new int[][][]{{{0, 0, 0}, {255, 242, 188}, {255, 242, 188}, {172, 153, 119}},
            {{255, 242, 188}, {121, 108, 84}, {185, 165, 128}, {255, 242, 188}},
            {{255, 242, 188}, {90, 80, 62}, {255, 255, 199}, {255, 242, 188}},
            {{168, 150, 117}, {255, 242, 188}, {255, 242, 188}, {255, 255, 239}}},
            result.getImage());
  }

  /**
   * Test ImageSharpenCommand.
   * */
  @Test
  public void testImageSharpenCommand(){
    ImageProcessingCommand command = new ImageSharpenCommand();
    ImageProcessingModel test = new PPMProcessingModel(img, 300);
    ImageProcessingModel result = command.execute(test);
    assertArrayEquals(new int[][][]
            {{{39, 8, 37}, {233, 214, 300}, {281, 261, 300}, {154, 156, 228}},
            {{177, 216, 226}, {300, 176, 137}, {300, 266, 300}, {256, 300, 300}},
            {{177, 276, 300}, {215, 292, 224}, {300, 300, 206}, {300, 300, 300}},
            {{0, 197, 269}, {152, 300, 268}, {247, 300, 277}, {300, 300, 279}}}, result.getImage());
  }

  /**
   * Test GaussianBlurCommand.
   * */
  @Test
  public void testGaussianBlurCommand(){
    ImageProcessingCommand command = new GaussianBlurCommand();
    ImageProcessingModel test = new PPMProcessingModel(img, 300);
    ImageProcessingModel result = command.execute(test);
    assertArrayEquals(new int[][][]
                    {{{53, 56, 61}, {109, 104, 120}, {123, 123, 146}, {83, 91, 103}},
                    {{97, 105, 109}, {160, 127, 127}, {179, 151, 154}, {124, 137, 144}},
                    {{83, 130, 138}, {135, 156, 129}, {179, 183, 136}, {144, 162, 147}},
                    {{39, 101, 119}, {84, 143, 136}, {131, 163, 137}, {118, 133, 122}}},
            result.getImage());
  }

  /**
   * Test ChangeNameCommand.
   * */
  @Test
  public void testChangeName(){
    int[][][] img = {{{0, 0, 0}}};
    ImageProcessingModel model = new PPMProcessingModel(img, 255);
    ImageProcessingView test = new PPMProcessingView();
    test.storeImage("dot.ppm", model);
    ImageProcessingCommand command =
            new ChangeNameCommand("dot.ppm", "changeName.ppm");
    command.execute(test);
    assertArrayEquals(model.getImage(), test.getModel("changeName.ppm").getImage());
  }

  //Testing loading and saving relies a lot more on other classes and thus is only tested in
  //Integration tests.
}