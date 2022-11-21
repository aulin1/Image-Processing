import org.junit.Test;

import model.ImageProcessingModelState;
import model.UpdatedProcessingModel;
import view.HistogramPanel;
import view.ImageProcessingGUI;
import view.LogisticPanelImpl;
import view.MenuBar;

import static org.junit.Assert.assertNotNull;

/**
 * A class for testing the GUI view.
 * */
public class ViewTest {

  /**
   * Tests if the menu bar constructor works.
   * */
  @Test
  public void testMenuBarConstructor(){
    MenuBar test = new MenuBar();
    assertNotNull(test);
  }

  /**
   * Tests if the LogisticPanelImpl constructor works.
   * */
  @Test
  public void testLogisticPanelImplConstructor(){
    LogisticPanelImpl test = new LogisticPanelImpl();
    assertNotNull(test);
  }

  /**
   * Tests if the histogram panel constructor works.
   * */
  @Test
  public void testHistogramConstructor(){
    HistogramPanel test = new HistogramPanel();
    assertNotNull(test);
  }

  /**
   * Tests if the ImageProcessingGUI constructor works.
   * */
  @Test
  public void testImageProcessingGUI(){
    ImageProcessingModelState model = new UpdatedProcessingModel();
    ImageProcessingGUI test = new ImageProcessingGUI(model);
    assertNotNull(test);
  }

  /**
   * Tests if the ImageProcessingGUI constructor throws an IllegalArgumentException if the
   * ModelState is null.
   * */
  @Test(expected = Exception.class)
  public void testImageProcessingGUI2(){
    ImageProcessingGUI test = new ImageProcessingGUI(null);
  }
}
