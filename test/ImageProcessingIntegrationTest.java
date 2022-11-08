import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import model.BlueCompCommand;
import model.BrightnessCommand;
import model.GaussianBlurCommand;
import model.GreenCompCommand;
import model.HorizontalFlipCommand;
import model.ImageProcessingCommand;
import model.ImageSharpenCommand;
import model.IntensityCommand;
import model.LumaCommand;
import model.RedCompCommand;
import model.SepiaToneCommand;
import model.ValueCommand;
import model.VerticalFlipCommand;
import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import controller.UpdatedIPController;
import image.ImageClass;
import image.ImageClassImpl;
import model.ImageProcessingModel;
import model.PPMProcessingModel;
import model.UpdatedProcessingModel;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests for integrating the model, view, and controller. Includes tests for the view.
 */
public class ImageProcessingIntegrationTest {

  private String filePath;
  private String fileName;
  private int[][][] pixelArr;

  @Before
  public void getImageFile() {
    filePath = "res/Pixel.ppm";
    fileName = "Pixel";
    pixelArr = new int[][][]
        {{{0, 0, 0}, {153, 217, 234}, {153, 217, 234}, {127, 127, 127}},
         {{153, 217, 234}, {237, 28, 36}, {205, 85, 207}, {153, 217, 234}},
         {{153, 217, 234}, {12, 102, 36}, {255, 242, 0}, {153, 217, 234}},
         {{0, 162, 232}, {153, 217, 234}, {153, 217, 234}, {255, 255, 255}}};
  }

  /**
   * Tests the constructor of ImageProcessingControllerImpl.
   */
  @Test
  public void testConstructorController() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("");
    ImageProcessingModel view = new PPMProcessingModel();
    ImageProcessingController test = new ImageProcessingControllerImpl(out, in, view);
    assertNotNull("Failed", test);
  }

  /**
   * Tests the empty constructor for the view.
   */
  @Test
  public void testConstructorView() {
    ImageProcessingModel test = new PPMProcessingModel();
    assertNotNull("Failed", test);
  }

  /**
   * Tests the empty constructor for the new view.
   */
  @Test
  public void testConstructorView1() {
    ImageProcessingModel test = new UpdatedProcessingModel();
    assertNotNull("Failed", test);
  }

  /**
   * Tests if the other constructor for the view works.
   */
  @Test
  public void testConstructorView2() {
    Map<String, ImageClass> map = new HashMap<>();
    ImageProcessingModel test = new PPMProcessingModel(map);
    assertNotNull("Failed", test);
  }

  /**
   * Tests if the other constructor for the new view works.
   */
  @Test
  public void testConstructorView3() {
    Map<String, ImageClass> map = new HashMap<>();
    ImageProcessingModel test = new UpdatedProcessingModel(map);
    assertNotNull("Failed", test);
  }

  /**
   * Tests if the constructor for the view correctly throws an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorView4() {
    ImageProcessingModel test = new PPMProcessingModel(null);
  }

  /**
   * Tests if the constructor for the new view correctly throws an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorView5() {
    ImageProcessingModel test = new UpdatedProcessingModel(null);
  }

  /**
   * Tests if loading an image works with the view.
   */
  @Test
  public void testLoad() {
    Map<String, ImageClass> map = new HashMap<>();
    ImageProcessingModel test = new PPMProcessingModel(map);
    ImageClass model = test.loadImage(filePath, fileName);
    assertArrayEquals(pixelArr, model.getImage());
    assertArrayEquals(model.getImage(), test.getImage(fileName).getImage());
    assertArrayEquals(model.getImage(), map.get(fileName).getImage());
  }

  /**
   * Tests if saving an image works with the view.
   */
  @Test
  public void testSave() {
    Map<String, ImageClass> map = new HashMap<>();
    ImageProcessingModel test = new PPMProcessingModel(map);
    test.loadImage(filePath, fileName);
    test.saveImage(filePath, fileName);
    ImageClass model2 = test.loadImage(filePath, fileName);
    assertArrayEquals(test.getImage(fileName).getImage(), model2.getImage());
    assertArrayEquals(pixelArr, model2.getImage());
  }

  /**
   * Tests if storing an image works.
   */
  @Test
  public void testStoreImage() {
    int[][][] img = {{{0, 0, 0}}};
    ImageClass model = new ImageClassImpl(img, 255);
    Map<String, ImageClass> map = new HashMap<>();
    ImageProcessingModel test = new PPMProcessingModel(map);
    test.storeImage("dot.ppm", model);
    assertArrayEquals(model.getImage(), test.getImage("dot.ppm").getImage());
    assertArrayEquals(model.getImage(), map.get("dot.ppm").getImage());
  }

  /**
   * Tests if changing a name works.
   **/
  @Test
  public void testChangeName() {
    int[][][] img = {{{0, 0, 0}}};
    ImageClass model = new ImageClassImpl(img, 255);
    Map<String, ImageClass> map = new HashMap<>();
    ImageProcessingModel test = new PPMProcessingModel(map);
    test.storeImage("dot.ppm", model);
    test.changeName("dot.ppm", "name.ppm");
    assertArrayEquals(model.getImage(), test.getImage("name.ppm").getImage());
    assertArrayEquals(model.getImage(), map.get("name.ppm").getImage());
  }

  /**
   * Tests if loading, using multiple correct commands works.
   */
  @Test
  public void testCommands() {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("load " + filePath + " " + fileName + " red-component "
            + fileName + " edit horizontal-flip edit edit brighten edit edit 10 "
            + "save res/edit.ppm edit q");
    Map<String, ImageClass> map = new HashMap<>();
    ImageProcessingModel view = new PPMProcessingModel(map);
    ImageProcessingController test = new ImageProcessingControllerImpl(out, in, view);
    test.start();
    assertArrayEquals(new int[][][]{{{137, 137, 137}, {163, 163, 163}, {163, 163, 163},
        {10, 10, 10}}, {{163, 163, 163}, {215, 215, 215}, {247, 247, 247}, {163, 163, 163}},
        {{163, 163, 163}, {255, 255, 255}, {22, 22, 22}, {163, 163, 163}},
        {{255, 255, 255}, {163, 163, 163}, {163, 163, 163}, {10, 10, 10}}},
            view.getImage("edit").getImage());
  }

  /**
   * Tests if using an incorrect command does what is expected.
   */
  @Test
  public void testIncorrectCommand() {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("load " + filePath + " " + fileName + " fakeCommand "
            + "q");
    Map<String, ImageClass> map = new HashMap<>();
    ImageProcessingModel view = new PPMProcessingModel(map);
    ImageProcessingController test = new ImageProcessingControllerImpl(out, in, view);
    test.start();
    assertNotNull(map.get(fileName));
    String[] splitString = out.toString().split("\n");
    assertEquals("Command is not supported.", splitString[splitString.length - 2]);
  }

  /**
   * Tests if using an incorrect, then correct command works.
   */
  @Test
  public void testIncorrectCorrectCommand() {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("load " + filePath + " " + fileName + " fakeCommand "
            + "red-component " + fileName + " edit " + "q");
    Map<String, ImageClass> map = new HashMap<>();
    ImageProcessingModel view = new PPMProcessingModel(map);
    ImageProcessingController test = new ImageProcessingControllerImpl(out, in, view);
    test.start();
    assertArrayEquals(new int[][][]{{{0, 0, 0}, {153, 153, 153}, {153, 153, 153}, {127, 127, 127}},
        {{153, 153, 153}, {237, 237, 237}, {205, 205, 205}, {153, 153, 153}},
        {{153, 153, 153}, {12, 12, 12}, {255, 255, 255}, {153, 153, 153}},
        {{0, 0, 0}, {153, 153, 153}, {153, 153, 153}, {255, 255, 255}}},
            view.getImage("edit").getImage());
  }

  /**
   * Tests if it throws an IllegalStateException if not quit.
   */
  @Test(expected = IllegalStateException.class)
  public void testNoQuit() {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("load " + filePath + " " + fileName + "red-component"
            + " horizontal-flip brighten 10 save res/PixelEdit.ppm PixelEdit.ppm");
    Map<String, ImageClass> map = new HashMap<>();
    ImageProcessingModel view = new PPMProcessingModel(map);
    ImageProcessingController test = new ImageProcessingControllerImpl(out, in, view);
    test.start();
  }

  /**
   * Tests if loading in multiple images works.
   */
  @Test
  public void testLoadMultiple() {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("load " + filePath + " " + fileName + " load"
            + " res/dot.ppm dot.ppm load res/Koala.ppm Koala.ppm q");
    Map<String, ImageClass> map = new HashMap<>();
    ImageProcessingModel view = new PPMProcessingModel(map);
    ImageProcessingController test = new ImageProcessingControllerImpl(out, in, view);
    test.start();
    assertArrayEquals(view.getImage(fileName).getImage(), map.get(fileName).getImage());
    assertArrayEquals(view.getImage("dot.ppm").getImage(),
            map.get("dot.ppm").getImage());
    assertArrayEquals(view.getImage("Koala.ppm").getImage(),
            map.get("Koala.ppm").getImage());
  }

  /**
   * Tests each command individually and makes sure that they do the correct operation.
   */
  @Test
  public void testAllCommandsOld() {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("load " + filePath + " " + fileName
            + " red-component " + fileName + " " + fileName + "_red"
            + " save res/" + fileName + "_red.ppm " + fileName + "_red"
            + " green-component " + fileName + " " + fileName + "_green"
            + " save res/" + fileName + "_green.ppm " + fileName + "_green"
            + " blue-component " + fileName + " " + fileName + "_blue"
            + " save res/" + fileName + "_blue.ppm " + fileName + "_blue"
            + " value " + fileName + " " + fileName + "_value"
            + " save res/" + fileName + "_value.ppm " + fileName + "_value"
            + " intensity " + fileName + " " + fileName + "_intensity"
            + " save res/" + fileName + "_intensity.ppm " + fileName + "_intensity"
            + " luma " + fileName + " " + fileName + "_luma"
            + " save res/" + fileName + "_luma.ppm " + fileName + "_luma"
            + " horizontal-flip " + fileName + " " + fileName + "_horizontal_flip"
            + " save res/" + fileName + "_horizontal_flip.ppm " + fileName + "_horizontal_flip"
            + " vertical-flip " + fileName + " " + fileName + "_vertical_flip"
            + " save res/" + fileName + "_vertical_flip.ppm " + fileName + "_vertical_flip"
            + " brighten " + fileName + " " + fileName + "_brighten 100"
            + " save res/" + fileName + "_brighten.ppm " + fileName + "_brighten"
            + " brighten " + fileName + " " + fileName + "_darken -100"
            + " save res/" + fileName + "_darken.ppm " + fileName + "_darken q");
    Map<String, ImageClass> map = new HashMap<>();
    ImageProcessingModel view = new PPMProcessingModel(map);
    ImageProcessingController test = new ImageProcessingControllerImpl(out, in, view);
    test.start();
    ImageProcessingCommand command = new RedCompCommand();
    assertArrayEquals(map.get(fileName + "_red").getImage(),
            command.execute(view.getImage(fileName)).getImage());
    command = new GreenCompCommand();
    assertArrayEquals(map.get(fileName + "_green").getImage(),
            command.execute(view.getImage(fileName)).getImage());
    command = new BlueCompCommand();
    assertArrayEquals(map.get(fileName + "_blue").getImage(),
            command.execute(view.getImage(fileName)).getImage());
    command = new ValueCommand();
    assertArrayEquals(map.get(fileName + "_value").getImage(),
            command.execute(view.getImage(fileName)).getImage());
    command = new IntensityCommand();
    assertArrayEquals(map.get(fileName + "_intensity").getImage(),
            command.execute(view.getImage(fileName)).getImage());
    command = new LumaCommand();
    assertArrayEquals(map.get(fileName + "_luma").getImage(),
            command.execute(view.getImage(fileName)).getImage());
    command = new HorizontalFlipCommand();
    assertArrayEquals(map.get(fileName + "_horizontal_flip").getImage(),
            command.execute(view.getImage(fileName)).getImage());
    command = new VerticalFlipCommand();
    assertArrayEquals(map.get(fileName + "_vertical_flip").getImage(),
            command.execute(view.getImage(fileName)).getImage());
    command = new BrightnessCommand(100);
    assertArrayEquals(map.get(fileName + "_brighten").getImage(),
            command.execute(view.getImage(fileName)).getImage());
    command = new BrightnessCommand(-100);
    assertArrayEquals(map.get(fileName + "_darken").getImage(),
            command.execute(view.getImage(fileName)).getImage());
  }

  /**
   * Tests each command individually and makes sure that they do the correct operation.
   */
  @Test
  public void testAllCommands() {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("load " + filePath + " " + fileName
            + " red-component " + fileName + " " + fileName + "_red"
            + " save res/" + fileName + "_red.ppm " + fileName + "_red"
            + " green-component " + fileName + " " + fileName + "_green"
            + " save res/" + fileName + "_green.ppm " + fileName + "_green"
            + " blue-component " + fileName + " " + fileName + "_blue"
            + " save res/" + fileName + "_blue.ppm " + fileName + "_blue"
            + " value " + fileName + " " + fileName + "_value"
            + " save res/" + fileName + "_value.ppm " + fileName + "_value"
            + " intensity " + fileName + " " + fileName + "_intensity"
            + " save res/" + fileName + "_intensity.ppm " + fileName + "_intensity"
            + " luma " + fileName + " " + fileName + "_luma"
            + " save res/" + fileName + "_luma.ppm " + fileName + "_luma"
            + " horizontal-flip " + fileName + " " + fileName + "_horizontal_flip"
            + " save res/" + fileName + "_horizontal_flip.ppm " + fileName + "_horizontal_flip"
            + " vertical-flip " + fileName + " " + fileName + "_vertical_flip"
            + " save res/" + fileName + "_vertical_flip.ppm " + fileName + "_vertical_flip"
            + " brighten " + fileName + " " + fileName + "_brighten 100"
            + " save res/" + fileName + "_brighten.ppm " + fileName + "_brighten"
            + " brighten " + fileName + " " + fileName + "_darken -100"
            + " save res/" + fileName + "_darken.ppm " + fileName + "_darken"
            + " gaussian-blur " + fileName + " " + fileName + "_gaussian_blur"
            + " save res/" + fileName + "_gaussian_blur.ppm " + fileName + "_gaussian_blur"
            + " greyscale " + fileName + " " + fileName + "_greyscale"
            + " save res/" + fileName + "_greyscale.ppm " + fileName + "_greyscale"
            + " sharpen " + fileName + " " + fileName + "_sharpen"
            + " save res/" + fileName + "_sharpen.ppm " + fileName + "_sharpen"
            + " sepia " + fileName + " " + fileName + "_sepia"
            + " save res/" + fileName + "_sepia.ppm " + fileName + "_sepia q");
    Map<String, ImageClass> map = new HashMap<>();
    ImageProcessingModel view = new PPMProcessingModel(map);
    ImageProcessingController test = new UpdatedIPController(out, in, view);
    test.start();
    ImageProcessingCommand command = new RedCompCommand();
    assertArrayEquals(map.get(fileName + "_red").getImage(),
            command.execute(view.getImage(fileName)).getImage());
    command = new GreenCompCommand();
    assertArrayEquals(map.get(fileName + "_green").getImage(),
            command.execute(view.getImage(fileName)).getImage());
    command = new BlueCompCommand();
    assertArrayEquals(map.get(fileName + "_blue").getImage(),
            command.execute(view.getImage(fileName)).getImage());
    command = new ValueCommand();
    assertArrayEquals(map.get(fileName + "_value").getImage(),
            command.execute(view.getImage(fileName)).getImage());
    command = new IntensityCommand();
    assertArrayEquals(map.get(fileName + "_intensity").getImage(),
            command.execute(view.getImage(fileName)).getImage());
    command = new LumaCommand();
    assertArrayEquals(map.get(fileName + "_luma").getImage(),
            command.execute(view.getImage(fileName)).getImage());
    command = new HorizontalFlipCommand();
    assertArrayEquals(map.get(fileName + "_horizontal_flip").getImage(),
            command.execute(view.getImage(fileName)).getImage());
    command = new VerticalFlipCommand();
    assertArrayEquals(map.get(fileName + "_vertical_flip").getImage(),
            command.execute(view.getImage(fileName)).getImage());
    command = new BrightnessCommand(100);
    assertArrayEquals(map.get(fileName + "_brighten").getImage(),
            command.execute(view.getImage(fileName)).getImage());
    command = new BrightnessCommand(-100);
    assertArrayEquals(map.get(fileName + "_darken").getImage(),
            command.execute(view.getImage(fileName)).getImage());
    command = new GaussianBlurCommand();
    assertArrayEquals(map.get(fileName + "_gaussian_blur").getImage(),
            command.execute(view.getImage(fileName)).getImage());
    command = new LumaCommand();
    assertArrayEquals(map.get(fileName + "_greyscale").getImage(),
            command.execute(view.getImage(fileName)).getImage());
    command = new ImageSharpenCommand();
    assertArrayEquals(map.get(fileName + "_sharpen").getImage(),
            command.execute(view.getImage(fileName)).getImage());
    command = new SepiaToneCommand();
    assertArrayEquals(map.get(fileName + "_sepia").getImage(),
            command.execute(view.getImage(fileName)).getImage());
  }
  //As long as you test that loading and saving give the correct model, then it can be assumed that
  //Since the operations are on the model in general, the operations are correct.
  /**
   * Tests loading in a png, running a command on it, and saving it using controller.
   * */
  @Test
  public void testSaveLoadPNG() {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("load res/Pixel.png Pixel vertical-flip Pixel edit "
            + "save res/PixelEdit.png edit q");
    Map<String, ImageClass> map = new HashMap<>();
    ImageProcessingModel view = new UpdatedProcessingModel(map);
    ImageProcessingController test = new UpdatedIPController(out, in, view);
    test.start();
    ImageClass model2 = view.loadImage("res/PixelEdit.png", "2");
    //Test loading
    assertArrayEquals(new int[][][]
                    {{{0, 162, 232}, {153, 217, 234}, {153, 217, 234}, {255, 255, 255}},
                    {{153, 217, 234}, {12, 102, 36}, {255, 242, 0}, {153, 217, 234}},
                    {{153, 217, 234}, {237, 28, 36}, {205, 85, 207}, {153, 217, 234}},
                    {{0, 0, 0}, {153, 217, 234}, {153, 217, 234}, {127, 127, 127}}},
            view.getImage("edit").getImage());
    //Test saving
    assertArrayEquals(new int[][][]
            {{{0, 162, 232}, {153, 217, 234}, {153, 217, 234}, {255, 255, 255}},
                    {{153, 217, 234}, {12, 102, 36}, {255, 242, 0}, {153, 217, 234}},
                    {{153, 217, 234}, {237, 28, 36}, {205, 85, 207}, {153, 217, 234}},
                    {{0, 0, 0}, {153, 217, 234}, {153, 217, 234}, {127, 127, 127}}},
            model2.getImage());
  }

  /**
   * Tests loading in a jpg, running a command on it, and saving it.
   * */
  @Test
  public void testSaveLoadJPG() {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("load res/Pixel.jpg Pixel vertical-flip Pixel edit "
            + "save res/PixelEdit.jpg edit q");
    Map<String, ImageClass> map = new HashMap<>();
    ImageProcessingModel view = new UpdatedProcessingModel(map);
    ImageProcessingController test = new UpdatedIPController(out, in, view);
    test.start();
    ImageClass model2 = view.loadImage("res/PixelEdit.jpg", "2");
    //Test loading
    assertArrayEquals(new int[][][]
                    {{{0, 169, 238}, {153, 222, 237}, {160, 219, 233}, {255, 247, 248}},
                            {{157, 217, 218}, {15, 95, 44}, {255, 240, 0}, {169, 215, 238}},
                            {{150, 219, 234}, {234, 28, 28}, {205, 90, 217}, {155, 209, 247}},
                            {{7, 0, 8}, {153, 221, 230}, {145, 218, 227}, {126, 131, 124}}},
            view.getImage("edit").getImage());
    //Test saving - JPG files compress strangely when the image is small, so we just make sure
    //that the image actually exists.
    assertNotNull(model2.getImage());
  }

  /**
   * Tests loading in a bmp, running a command on it, and saving it.
   * */
  @Test
  public void testSaveLoadBMP() {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("load res/Pixel.bmp Pixel vertical-flip Pixel edit "
            + "save res/PixelEdit.bmp edit q");
    Map<String, ImageClass> map = new HashMap<>();
    ImageProcessingModel view = new UpdatedProcessingModel(map);
    ImageProcessingController test = new UpdatedIPController(out, in, view);
    test.start();
    ImageClass model2 = view.loadImage("res/PixelEdit.bmp", "2");
    //Test loading
    assertArrayEquals(new int[][][]
                    {{{0, 162, 232}, {153, 217, 234}, {153, 217, 234}, {255, 255, 255}},
                            {{153, 217, 234}, {12, 102, 36}, {255, 242, 0}, {153, 217, 234}},
                            {{153, 217, 234}, {237, 28, 36}, {205, 85, 207}, {153, 217, 234}},
                            {{0, 0, 0}, {153, 217, 234}, {153, 217, 234}, {127, 127, 127}}},
            view.getImage("edit").getImage());
    //Test saving
    assertArrayEquals(new int[][][]
                    {{{0, 162, 232}, {153, 217, 234}, {153, 217, 234}, {255, 255, 255}},
                            {{153, 217, 234}, {12, 102, 36}, {255, 242, 0}, {153, 217, 234}},
                            {{153, 217, 234}, {237, 28, 36}, {205, 85, 207}, {153, 217, 234}},
                            {{0, 0, 0}, {153, 217, 234}, {153, 217, 234}, {127, 127, 127}}},
            view.getImage("2").getImage());
  }
}
