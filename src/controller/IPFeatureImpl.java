package controller;

import java.util.HashMap;
import java.util.Scanner;

import image.ImageClass;
import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * This class is an implementation of the IPFeature interface which handles inputs from the view.
 */
public class IPFeatureImpl extends ControllerCommandUtil implements IPFeature {
  private final ImageProcessingView view;

  /**
   * Constructs a new IPFeatureImpl given the model and the view.
   * @param model the model which receives the instruction from this controller
   * @param view the view which this controller receives the input from
   * @throws IllegalArgumentException if either the model or the view is null
   */
  public IPFeatureImpl(ImageProcessingModel model, ImageProcessingView view) throws IllegalArgumentException {
    if (model == null || view == null) {
      throw new IllegalArgumentException("The arguments cannot be null.");
    }
    this.model = model;
    this.view = view;
    this.imgProcCommandMap = new HashMap<>();
    this.modelCommandMap = new HashMap<>();
    initiateComms();

    this.view.registerFeature(this);
  }

  @Override
  public void processInput(String input) {
    Scanner sc = new Scanner(input);
    while (sc.hasNext()) {
      String s = sc.next();
      readComm(s.toLowerCase(), sc);
    }
  }

  @Override
  protected void writeMessage(String message){
    this.view.renderMessage(message);
  }

  @Override
  protected void renderImage(ImageClass img){
    this.view.renderImage(img);
  }
}
