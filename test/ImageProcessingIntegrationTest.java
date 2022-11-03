import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import model.ImageProcessingModel;
import model.PPMProcessingModel;
import view.ImageProcessingView;
import view.PPMProcessingView;

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
    ImageProcessingView view = new PPMProcessingView();
    ImageProcessingController test = new ImageProcessingControllerImpl(out, in, view);
    assertNotNull("Failed", test);
  }

  /**
   * Tests the empty constructor for the view.
   */
  @Test
  public void testConstructorView() {
    ImageProcessingView test = new PPMProcessingView();
    assertNotNull("Failed", test);
  }

  /**
   * Tests if the other constructor for the view works.
   */
  @Test
  public void testConstructorView2() {
    Map<String, ImageProcessingModel> map = new HashMap<>();
    ImageProcessingView test = new PPMProcessingView(map);
    assertNotNull("Failed", test);
  }

  /**
   * Tests if the constructor for the view correctly throws an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorView3() {
    ImageProcessingView test = new PPMProcessingView(null);
  }

  /**
   * Tests if loading an image works with the view.
   */
  @Test
  public void testLoad() {
    Map<String, ImageProcessingModel> map = new HashMap<>();
    ImageProcessingView test = new PPMProcessingView(map);
    ImageProcessingModel model = test.loadImage(filePath, fileName);
    assertArrayEquals(pixelArr, model.getImage());
    assertArrayEquals(model.getImage(), test.getModel(fileName).getImage());
    assertArrayEquals(model.getImage(), map.get(fileName).getImage());
  }

  /**
   * Tests if saving an image works with the view.
   */
  @Test
  public void testSave() {
    Map<String, ImageProcessingModel> map = new HashMap<>();
    ImageProcessingView test = new PPMProcessingView(map);
    test.loadImage(filePath, fileName);
    test.saveImage(filePath, fileName);
    ImageProcessingModel model2 = test.loadImage(filePath, fileName);
    assertArrayEquals(test.getModel(fileName).getImage(), model2.getImage());
    assertArrayEquals(pixelArr, model2.getImage());
  }

  /**
   * Tests if storing an image works.
   */
  @Test
  public void testStoreImage() {
    int[][][] img = {{{0, 0, 0}}};
    ImageProcessingModel model = new PPMProcessingModel(img, 255);
    Map<String, ImageProcessingModel> map = new HashMap<>();
    ImageProcessingView test = new PPMProcessingView(map);
    test.storeImage("dot.ppm", model);
    assertArrayEquals(model.getImage(), test.getModel("dot.ppm").getImage());
    assertArrayEquals(model.getImage(), map.get("dot.ppm").getImage());
  }

  /**
   * Tests if changing a name works.
   **/
  @Test
  public void testChangeName() {
    int[][][] img = {{{0, 0, 0}}};
    ImageProcessingModel model = new PPMProcessingModel(img, 255);
    Map<String, ImageProcessingModel> map = new HashMap<>();
    ImageProcessingView test = new PPMProcessingView(map);
    test.storeImage("dot.ppm", model);
    test.changeName("dot.ppm", "name.ppm");
    assertArrayEquals(model.getImage(), test.getModel("name.ppm").getImage());
    assertArrayEquals(model.getImage(), map.get("name.ppm").getImage());
  }

  /**
   * Tests each command individually and makes sure that they call the correct command.
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
            + " save res/" + fileName + "_darken.ppm " + fileName + "_darken q");
    Map<String, ImageProcessingModel> map = new HashMap<>();
    ImageProcessingView view = new PPMProcessingView(map);
    ImageProcessingController test = new ImageProcessingControllerImpl(out, in, view);
    test.start();
    assertArrayEquals(map.get(fileName + "_red").getImage(),
            view.getModel(fileName).returnRedImage().getImage());
    assertArrayEquals(map.get(fileName + "_green").getImage(),
            view.getModel(fileName).returnGreenImage().getImage());
    assertArrayEquals(map.get(fileName + "_blue").getImage(),
            view.getModel(fileName).returnBlueImage().getImage());
    assertArrayEquals(map.get(fileName + "_value").getImage(),
            view.getModel(fileName).returnValueImage().getImage());
    assertArrayEquals(map.get(fileName + "_intensity").getImage(),
            view.getModel(fileName).returnIntensityImage().getImage());
    assertArrayEquals(map.get(fileName + "_luma").getImage(),
            view.getModel(fileName).returnLumaImage().getImage());
    assertArrayEquals(map.get(fileName + "_horizontal_flip").getImage(),
            view.getModel(fileName).flipImageHorizontally().getImage());
    assertArrayEquals(map.get(fileName + "_vertical_flip").getImage(),
            view.getModel(fileName).flipImageVertically().getImage());
    assertArrayEquals(map.get(fileName + "_brighten").getImage(),
            view.getModel(fileName).changeBrightness(100).getImage());
    assertArrayEquals(map.get(fileName + "_darken").getImage(),
            view.getModel(fileName).changeBrightness(-100).getImage());
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
    Map<String, ImageProcessingModel> map = new HashMap<>();
    ImageProcessingView view = new PPMProcessingView(map);
    ImageProcessingController test = new ImageProcessingControllerImpl(out, in, view);
    test.start();
    assertArrayEquals(new int[][][]{{{137, 137, 137}, {163, 163, 163}, {163, 163, 163},
        {10, 10, 10}}, {{163, 163, 163}, {215, 215, 215}, {247, 247, 247}, {163, 163, 163}},
        {{163, 163, 163}, {255, 255, 255}, {22, 22, 22}, {163, 163, 163}},
        {{255, 255, 255}, {163, 163, 163}, {163, 163, 163}, {10, 10, 10}}},
            view.getModel("edit").getImage());
  }

  /**
   * Tests if using an incorrect command does what is expected.
   */
  @Test
  public void testIncorrectCommand() {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("load " + filePath + " " + fileName + " fakeCommand "
            + "q");
    Map<String, ImageProcessingModel> map = new HashMap<>();
    ImageProcessingView view = new PPMProcessingView(map);
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
    Map<String, ImageProcessingModel> map = new HashMap<>();
    ImageProcessingView view = new PPMProcessingView(map);
    ImageProcessingController test = new ImageProcessingControllerImpl(out, in, view);
    test.start();
    assertArrayEquals(new int[][][]{{{0, 0, 0}, {153, 153, 153}, {153, 153, 153}, {127, 127, 127}},
        {{153, 153, 153}, {237, 237, 237}, {205, 205, 205}, {153, 153, 153}},
        {{153, 153, 153}, {12, 12, 12}, {255, 255, 255}, {153, 153, 153}},
        {{0, 0, 0}, {153, 153, 153}, {153, 153, 153}, {255, 255, 255}}},
            view.getModel("edit").getImage());
  }

  /**
   * Tests if it throws an IllegalStateException if not quit.
   */
  @Test(expected = IllegalStateException.class)
  public void testNoQuit() {
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("load " + filePath + " " + fileName + "red-component"
            + " horizontal-flip brighten 10 save res/PixelEdit.ppm PixelEdit.ppm");
    Map<String, ImageProcessingModel> map = new HashMap<>();
    ImageProcessingView view = new PPMProcessingView(map);
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
    Map<String, ImageProcessingModel> map = new HashMap<>();
    ImageProcessingView view = new PPMProcessingView(map);
    ImageProcessingController test = new ImageProcessingControllerImpl(out, in, view);
    test.start();
    assertArrayEquals(view.getModel(fileName).getImage(), map.get(fileName).getImage());
    assertArrayEquals(view.getModel("dot.ppm").getImage(),
            map.get("dot.ppm").getImage());
    assertArrayEquals(view.getModel("Koala.ppm").getImage(),
            map.get("Koala.ppm").getImage());
  }
}
