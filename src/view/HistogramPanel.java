package view;

import java.awt.*;

import javax.swing.*;

import controller.IPFeature;
import histogram.IHistogram;
import histogram.SimpleHistogram;
import image.ImageClass;

/**
 * This class represents the panel which draws the histogram in the Image Processing Program.
 */
public class HistogramPanel extends JPanel implements ImagePanel {
  private IPFeature feature;
  private ImageClass image;
  private IHistogram histogram;

  /**
   * Default constructor for a histogram panel.
   */
  public HistogramPanel() {
    super();
    setBackground(Color.white);
  }
  @Override
  public void registerFeature(IPFeature feature) throws IllegalArgumentException {
    if (feature == null) {
      throw new IllegalArgumentException("The argument cannot be null.");
    }
    this.feature = feature;
  }

  @Override
  public void setImage(ImageClass image) {
    if (image == null) {
      throw new IllegalArgumentException("The image cannot be null.");
    }
    this.image = image;
    setHistogram();
  }

  /**
   * Set up the new histogram based on the currently working image.
   */
  private void setHistogram() {
    this.histogram = new SimpleHistogram(this.image);
    //TODO: make setHistogram work here

  }
}