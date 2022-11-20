package controller;

import image.ImageClass;
import view.ImageProcessingView;

/**
 * A mock of the GUIView for testing IPFeatureImpl.
 * */
public class MockGUIView implements ImageProcessingView {
  private StringBuffer log;

  public MockGUIView(StringBuffer log){
    this.log = log;
  }

  @Override
  public void makeVisible() {
    log.append("Called makeVisible()\n");
  }

  @Override
  public void refresh() {
    log.append("Called refresh()\n");
  }

  @Override
  public void renderMessage(String message) {
    log.append("Called renderMessage(): ");
    log.append(message);
    log.append("\n");
  }

  @Override
  public void renderImage(ImageClass image) {
    log.append("Called renderImage()\n");
  }

  @Override
  public void registerFeature(IPFeature feature) {
    log.append("Called registerFeature()\n");
  }
}
