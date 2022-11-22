package view;

import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import controller.IPFeature;
import controller.ImageUtil;
import histogram.IHistogram;
import histogram.SimpleHistogram;
import image.ImageClass;

/**
 * This class represents the panel which draws the histogram in the Image Processing Program.
 */
public class HistogramPanel extends JPanel implements ImagePanel {
  private ImageClass image; // image used to calculate graph
  private Image graph; // graph to be displayed

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
  }

  @Override
  public void setImage(ImageClass image) {
    if (image == null) {
      throw new IllegalArgumentException("The image cannot be null.");
    }
    this.image = image;
    setHistogram();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (this.graph != null) {
      int x = (this.getWidth() - this.graph.getWidth(null)) / 2;
      int y = (this.getHeight() - this.graph.getHeight(null)) / 2;
      g.drawImage(this.graph, x, y, this);
    }
  }

  /**
   * Set up the new histogram based on the currently working image.
   */
  private void setHistogram() {
    //here
    IHistogram histogram = new SimpleHistogram(this.image);
    this.graph = ImageUtil.getBuffImage(histogram.getGraph());
    this.graph = this.graph.getScaledInstance(this.getWidth() - 20,
            this.getHeight() - 20, Image.SCALE_DEFAULT);
  }
}