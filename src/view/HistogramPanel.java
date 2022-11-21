package view;

import java.awt.*;

import javax.swing.*;

import controller.IPFeature;
import histogram.IHistogram;
import histogram.SimpleHistogram;
import image.ImageClass;

import static java.awt.GridBagConstraints.PAGE_END;

/**
 * This class represents the panel which draws the histogram in the Image Processing Program.
 */
public class HistogramPanel extends JPanel implements ImagePanel {
  private IPFeature feature;
  private ImageClass image;
  private IHistogram histogram;
  private JTextArea xAxis;
  private JTextArea yAxis;

  /**
   * Default constructor for a histogram panel.
   */
  public HistogramPanel() {
    super();
    this.setLayout(new GridBagLayout());
    setBackground(Color.white);
    setUp();
  }

  private void setUp(){
    this.xAxis = new JTextArea("X-Axis");
    this.xAxis.setEditable(false);
    this.xAxis.setVisible(true);
    GridBagConstraints xConstraints = new GridBagConstraints();
    xConstraints.fill = GridBagConstraints.HORIZONTAL;
    xConstraints.anchor = GridBagConstraints.PAGE_END;
    this.add(xAxis, xConstraints);

    this.yAxis = new JTextArea("N/A");
    this.yAxis.setEditable(false);
    this.yAxis.setVisible(true);
    this.add(yAxis);
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
   * A helper method to find the maximum value for the histogram.
   *
   * @return the max value.
   * */
  private int getMax(){
    if(this.histogram == null){
      return 0;
    }
    int[][] h = this.histogram.getHistogram();
    int max = 0;
    for(int i = 0; i < h[0].length; i++){
      max = Math.max(Math.max(Math.max(max, h[0][i]), h[1][i]), h[2][i]);
    }
    return max;
  }

  /**
   * Set up the new histogram based on the currently working image.
   */
  private void setHistogram() {
    this.histogram = new SimpleHistogram(this.image);
    //TODO: make setHistogram work here
    this.xAxis.setText(String.format("Value from 0-%d",this.image.getMax()));
    this.yAxis.setText(String.format("Number from 0-%d", this.getMax()));
  }
}