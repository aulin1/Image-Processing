import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import model.ImageProcessingModel;
import model.PPMProcessingModel;
import view.ImageProcessingView;
import view.PPMProcessingView;

import static org.junit.Assert.*;

/**
 * Tests for integrating the model, view, and controller. Includes tests for the view.
 * */
public class ImageProcessingIntegrationTest {

  private String filePath;
  private String fileName;
  private int[][][] pixelArr;

  @Before
  public void getImageFile() {
    filePath = "res/Pixel.ppm";
    fileName = "Pixel.ppm";
    pixelArr = new int[][][]
            {{{0, 0, 0}, {153, 217, 234}, {153, 217, 234}, {127, 127, 127}},
                    {{153, 217, 234}, {237, 28, 36}, {205,85,207}, {153, 217, 234}},
                    {{153, 217, 234}, {12,102,36}, {255,242,0}, {153, 217, 234}},
                    {{0,162,232}, {153, 217, 234}, {153, 217, 234}, {255,255,255}}};
  }

  /**
   * Tests the constructor of ImageProcessingControllerImpl.
   * */
  @Test
  public void testConstructorController(){
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("");
    ImageProcessingView view = new PPMProcessingView();
    ImageProcessingController test = new ImageProcessingControllerImpl(out, in, view);
    assertNotNull("Failed", test);
  }

  /**
   * Tests the empty constructor for the view.
   * */
  @Test
  public void testConstructorView(){
    ImageProcessingView test = new PPMProcessingView();
    assertNotNull("Failed", test);
  }



  /**
   * Tests if the constructor for the view correctly throws an IllegalArgumentException if
   * the path is null.
   * */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorView2(){
    ImageProcessingView test = new PPMProcessingView(null, this.fileName);
  }
  /**
   * Tests if the constructor for the view correctly throws an IllegalArgumentException if the
   * name is null.
   * */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorView3(){
    ImageProcessingView test = new PPMProcessingView(this.filePath, null);
  }

  /**
   * Tests if changing the name works.
   * */
  @Test
  public void testViewChangeName(){
    ImageProcessingView test = new PPMProcessingView(this.filePath, this.fileName);
    test.changeName("newName.ppm");
    assertEquals("newName.ppm", test.getName());
  }

  /**
   * Tests if changing the path works.
   * */
  @Test
  public void testViewChangePath(){
    ImageProcessingView test = new PPMProcessingView(this.filePath, this.fileName);
    test.changePath("res/newName.ppm");
    assertEquals("res/newName.ppm", test.getPath());
  }

  /**
   * Tests if loading an image works with the view.
   * */
  @Test
  public void testLoad(){
    ImageProcessingView test = new PPMProcessingView(this.filePath, this.fileName);
    ImageProcessingModel model = test.loadImage();
    assertArrayEquals(model.getImage(), pixelArr);
  }

  /**
   * Tests if saving an image works with the view.
   * */
  @Test
  public void testSave(){
    int[][][] img = {{{0, 0, 0}}};
    ImageProcessingModel model = new PPMProcessingModel(img, 255,"dot.ppm");
    ImageProcessingView test = new PPMProcessingView("res/dot.ppm", "dot.ppm");
    test.saveImage(model);
    ImageProcessingModel model2 = test.loadImage();
    assertArrayEquals(model.getImage(),model2.getImage());
  }

  /**
   * Tests if loading, using multiple correct commands works, and saving works.
   * */
  @Test
  public void testCommands(){
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("load " + filePath + " " + fileName + "red-component"
            + " horizontal-flip brighten 10 save res/PixelEdit.ppm PixelEdit.ppm q");
    ImageProcessingController test = new ImageProcessingControllerImpl(out, in);
    test.start();
    ImageProcessingView view =
            new PPMProcessingView("res/PixelEdit.ppm", "PixelEdit.ppm");
    assertArrayEquals(new int[][][] {{{137, 137, 137}, {163, 163, 163}, {163, 163, 163}, {10, 10, 10}},
            {{163, 163, 163}, {215, 215, 215}, {247,247,247}, {163, 163, 163}},
            {{163, 163, 163}, {255,255,255}, {22,22,22}, {163, 163, 163}},
            {{255,255,255}, {163, 163, 163}, {163, 163, 163}, {10,10,10}}},
            view.loadImage().getImage());
  }

  /**
   * Tests if using an incorrect command does what is expected.
   * */
  @Test
  public void testIncorrectCommand(){
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("load " + filePath + " " + fileName + "fakeCommand"
            + "q");
    ImageProcessingController test = new ImageProcessingControllerImpl(out, in);
    test.start();
    ImageProcessingView view =
            new PPMProcessingView("res/PixelEdit.ppm", "PixelEdit.ppm");
    ImageProcessingView view2 = new PPMProcessingView(this.filePath, this.fileName);
    assertArrayEquals(view.loadImage().getImage(), view2.loadImage().getImage());
  }

  /**
   * Tests if using an incorrect, then correct command works.
   * */
  @Test
  public void testIncorrectCorrectCommand(){
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("load " + filePath + " " + fileName + "fakeCommand"
            + "red-component"
            + " horizontal-flip brighten 10 save res/PixelEdit.ppm PixelEdit.ppm" + "q");
    ImageProcessingController test = new ImageProcessingControllerImpl(out, in);
    test.start();
    ImageProcessingView view =
            new PPMProcessingView("res/PixelEdit.ppm", "PixelEdit.ppm");
    assertArrayEquals(new int[][][] {{{137, 137, 137}, {163, 163, 163}, {163, 163, 163}, {10, 10, 10}},
            {{163, 163, 163}, {215, 215, 215}, {247,247,247}, {163, 163, 163}},
            {{163, 163, 163}, {255,255,255}, {22,22,22}, {163, 163, 163}},
            {{255,255,255}, {163, 163, 163}, {163, 163, 163}, {10,10,10}}},
            view.loadImage().getImage());
  }

  /**
   * Tests if it throws an IllegalStateException if not quit.
   * */
  @Test(expected = IllegalStateException.class)
  public void testNoQuit(){
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("load " + filePath + " " + fileName + "red-component"
            + " horizontal-flip brighten 10 save res/PixelEdit.ppm PixelEdit.ppm");
    ImageProcessingController test = new ImageProcessingControllerImpl(out, in);
    test.start();
  }

  /**
   * Tests if loading in multiple images works.
   * */
  @Test
  public void testLoadMultiple(){
    StringBuffer out = new StringBuffer();
    StringReader in = new StringReader("load " + filePath + " " + fileName + "load"
            + "res/img.ppm img.ppm load res/Koala.ppm Koala.ppm");
    ImageProcessingController test = new ImageProcessingControllerImpl(out, in);
    test.start();
    //add asserts once you know what's happening with controller
  }
}
