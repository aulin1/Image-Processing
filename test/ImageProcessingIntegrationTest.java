//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.Reader;
//import java.io.StringReader;
//import java.util.HashMap;
//import java.util.Map;
//
//import controller.ImageProcessingController;
//import controller.ImageProcessingControllerImpl;
//import model.ImageProcessingModel;
//import model.PPMProcessingModel;
//import view.ImageProcessingView;
//import view.PPMProcessingView;
//
//import static org.junit.Assert.*;
//
///**
// * Tests for integrating the model, view, and controller. Includes tests for the view.
// * */
//public class ImageProcessingIntegrationTest {
//
//  private String filePath;
//  private String fileName;
//  private int[][][] pixelArr;
//
//  @Before
//  public void getImageFile() {
//    filePath = "res/Pixel.ppm";
//    fileName = "Pixel.ppm";
//    pixelArr = new int[][][]
//            {{{0, 0, 0}, {153, 217, 234}, {153, 217, 234}, {127, 127, 127}},
//                    {{153, 217, 234}, {237, 28, 36}, {205,85,207}, {153, 217, 234}},
//                    {{153, 217, 234}, {12,102,36}, {255,242,0}, {153, 217, 234}},
//                    {{0,162,232}, {153, 217, 234}, {153, 217, 234}, {255,255,255}}};
//  }
//
//  /**
//   * Tests the constructor of ImageProcessingControllerImpl.
//   * */
//  @Test
//  public void testConstructorController(){
//    StringBuffer out = new StringBuffer();
//    Reader in = new StringReader("");
//    ImageProcessingView view = new PPMProcessingView();
//    ImageProcessingController test = new ImageProcessingControllerImpl(out, in, view);
//    assertNotNull("Failed", test);
//  }
//
//  /**
//   * Tests the empty constructor for the view.
//   * */
//  @Test
//  public void testConstructorView(){
//    ImageProcessingView test = new PPMProcessingView();
//    assertNotNull("Failed", test);
//  }
//
//  /**
//   * Tests if the other constructor for the view works.
//   * */
//  @Test
//  public void testConstructorView2(){
//    Map<String, ImageProcessingModel> map = new HashMap<>();
//    ImageProcessingView test = new PPMProcessingView(map);
//    assertNotNull("Failed", test);
//  }
//
//  /**
//   * Tests if the constructor for the view correctly throws an IllegalArgumentException.
//   * */
//  @Test(expected = IllegalArgumentException.class)
//  public void testConstructorView3(){
//    ImageProcessingView test = new PPMProcessingView(null);
//  }
//
//  /**
//   * Tests if loading an image works with the view.
//   * */
//  @Test
//  public void testLoad(){
//    Map<String, ImageProcessingModel> map = new HashMap<>();
//    ImageProcessingView test = new PPMProcessingView(map);
//    ImageProcessingModel model = test.loadImage(filePath, fileName);
//    assertArrayEquals(pixelArr, model.getImage());
//    assertArrayEquals(model.getImage(), test.getModel(fileName).getImage());
//    assertArrayEquals(model.getImage(), map.get(fileName).getImage());
//  }
//
//  /**
//   * Tests if saving an image works with the view.
//   * */
//  @Test
//  public void testSave(){
//    Map<String, ImageProcessingModel> map = new HashMap<>;
//    ImageProcessingView test = new PPMProcessingView(map);
//    test.saveImage(filePath, fileName);
//    ImageProcessingModel model2 = test.loadImage(filePath, fileName);
//    assertArrayEquals(test.getModel(fileName).getImage(),model2.getImage());
//    assertArrayEquals(pixelArr, model2.getImage());
//  }
//
//  /**
//   * Tests if storing an image works.
//   * */
//  @Test
//  public void testStoreImage(){
//    int[][][] img = {{{0, 0, 0}}};
//    ImageProcessingModel model = new PPMProcessingModel(img, 255);
//    Map<String, ImageProcessingModel> map = new HashMap<>;
//    ImageProcessingView test = new PPMProcessingView(map);
//    test.storeImage("dot.ppm", model);
//    assertArrayEquals(model.getImage(), test.getModel("dot.ppm").getImage());
//    assertArrayEquals(model.getImage(), map.get("dot.ppm").getImage());
//  }
//
//  /**
//   * Tests if changing a name works.
//   **/
//  @Test
//  public void testChangeName(){
//    int[][][] img = {{{0, 0, 0}}};
//    ImageProcessingModel model = new PPMProcessingModel(img, 255);
//    Map<String, ImageProcessingModel> map = new HashMap<>;
//    ImageProcessingView test = new PPMProcessingView(map);
//    test.storeImage("dot.ppm", model);
//    test.changeName("dot.ppm", "name.ppm");
//    assertArrayEquals(model.getImage(), test.getModel("name.ppm").getImage());
//    assertArrayEquals(model.getImage(), map.get("name.ppm").getImage());
//    assertNull(map.get("dot.ppm"));
//  }
//
//  /**
//   * Tests each command individually.
//   * */
//  @Test
//  public void testAllCommands(){
//
//  }
//
//
//  /**
//   * Tests if loading, using multiple correct commands works, and saving works.
//   * */
//  @Test
//  public void testCommands(){
//    StringBuffer out = new StringBuffer();
//    StringReader in = new StringReader("load " + filePath + " " + fileName + "red-component"
//            + " horizontal-flip brighten 10 save res/PixelEdit.ppm PixelEdit.ppm q");
//    Map<String, ImageProcessingModel> map = new HashMap<>;
//    ImageProcessingView view = new PPMProcessingView(map);
//    ImageProcessingController test = new ImageProcessingControllerImpl(out, in, view);
//    test.start();
//    assertArrayEquals(new int[][][] {{{137, 137, 137}, {163, 163, 163}, {163, 163, 163}, {10, 10, 10}},
//            {{163, 163, 163}, {215, 215, 215}, {247,247,247}, {163, 163, 163}},
//            {{163, 163, 163}, {255,255,255}, {22,22,22}, {163, 163, 163}},
//            {{255,255,255}, {163, 163, 163}, {163, 163, 163}, {10,10,10}}},
//            view.getModel("PixelEdit.ppm").getImage());
//    //TODO: Fix once you know how the controller saves things.
//  }
//
//  /**
//   * Tests if using an incorrect command does what is expected.
//   * */
//  @Test
//  public void testIncorrectCommand(){
//    StringBuffer out = new StringBuffer();
//    StringReader in = new StringReader("load " + filePath + " " + fileName + "fakeCommand"
//            + "q");
//    ImageProcessingController test = new ImageProcessingControllerImpl(out, in);
//    test.start();
//    ImageProcessingView view =
//            new PPMProcessingView("res/PixelEdit.ppm", "PixelEdit.ppm");
//    ImageProcessingView view2 = new PPMProcessingView(this.filePath, this.fileName);
//    assertArrayEquals(view.loadImage().getImage(), view2.loadImage().getImage());
//  }
//
//  /**
//   * Tests if using an incorrect, then correct command works.
//   * */
//  @Test
//  public void testIncorrectCorrectCommand(){
//    StringBuffer out = new StringBuffer();
//    StringReader in = new StringReader("load " + filePath + " " + fileName + "fakeCommand"
//            + "red-component"
//            + " horizontal-flip brighten 10 save res/PixelEdit.ppm PixelEdit.ppm" + "q");
//    ImageProcessingController test = new ImageProcessingControllerImpl(out, in);
//    test.start();
//    ImageProcessingView view =
//            new PPMProcessingView("res/PixelEdit.ppm", "PixelEdit.ppm");
//    assertArrayEquals(new int[][][] {{{137, 137, 137}, {163, 163, 163}, {163, 163, 163}, {10, 10, 10}},
//            {{163, 163, 163}, {215, 215, 215}, {247,247,247}, {163, 163, 163}},
//            {{163, 163, 163}, {255,255,255}, {22,22,22}, {163, 163, 163}},
//            {{255,255,255}, {163, 163, 163}, {163, 163, 163}, {10,10,10}}},
//            view.loadImage().getImage());
//  }
//
//  /**
//   * Tests if it throws an IllegalStateException if not quit.
//   * */
//  @Test(expected = IllegalStateException.class)
//  public void testNoQuit(){
//    StringBuffer out = new StringBuffer();
//    StringReader in = new StringReader("load " + filePath + " " + fileName + "red-component"
//            + " horizontal-flip brighten 10 save res/PixelEdit.ppm PixelEdit.ppm");
//    ImageProcessingController test = new ImageProcessingControllerImpl(out, in);
//    test.start();
//  }
//
//  /**
//   * Tests if loading in multiple images works.
//   * */
//  @Test
//  public void testLoadMultiple(){
//    StringBuffer out = new StringBuffer();
//    StringReader in = new StringReader("load " + filePath + " " + fileName + "load"
//            + "res/img.ppm img.ppm load res/Koala.ppm Koala.ppm");
//    ImageProcessingController test = new ImageProcessingControllerImpl(out, in);
//    test.start();
//    //TODO: add asserts once you know what's happening with controller
//  }
//}
