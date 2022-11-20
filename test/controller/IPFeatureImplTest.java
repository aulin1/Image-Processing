package controller;

import org.junit.Test;

import model.ImageProcessingModel;
import model.UpdatedProcessingModel;
import view.ImageProcessingView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * A class which tests the IPFeatureImpl.
 * */
public class IPFeatureImplTest {

  /**
   * A test that the constructor works.
   * */
  @Test
  public void testConstructor(){
    ImageProcessingModel model = new UpdatedProcessingModel();
    StringBuffer log = new StringBuffer();
    ImageProcessingView view = new MockGUIView(log);
    IPFeature test = new IPFeatureImpl(model, view);
    assertNotNull(test);
  }

  /**
   * A test that the constructor correctly throws an IllegalArgumentException for a null model.
   * */
  @Test(expected = IllegalArgumentException.class)
  public void testNullModel(){
    StringBuffer log = new StringBuffer();
    ImageProcessingView view = new MockGUIView(log);
    IPFeature test = new IPFeatureImpl(null, view);
  }

  /**
   * A test that the constructor correctly throws an IllegalArgumentException for a null view.
   * */
  @Test(expected = IllegalArgumentException.class)
  public void testNullView(){
    ImageProcessingModel model = new UpdatedProcessingModel();
    IPFeature test = new IPFeatureImpl(model, null);
  }

  /**
   * Tests processInput gives the correct error message.
   * */
  @Test
  public void testProcessInput(){
    ImageProcessingModel model = new UpdatedProcessingModel();
    StringBuffer log = new StringBuffer();
    ImageProcessingView view = new MockGUIView(log);
    IPFeature test = new IPFeatureImpl(model, view);
    test.processInput("");
    assertEquals("Called renderMessage(): Error: Insufficient args.\n", log.toString());
  }

  /**
   * Tests processInput calls upon readComms correctly with an incorrect command.
   * */
  @Test
  public void testReadComms(){
    ImageProcessingModel model = new UpdatedProcessingModel();
    StringBuffer log = new StringBuffer();
    ImageProcessingView view = new MockGUIView(log);
    IPFeature test = new IPFeatureImpl(model, view);
    test.processInput("incorrect comm");
    assertEquals("Called renderMessage(): Command is not supported.\n", log.toString());
  }
}