package view;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import controller.IPFeature;
import controller.ImageUtil;
import image.ImageClass;
import model.ImageProcessingModelState;

/**
 * This class represents a panel that displays the image that is currently being processed by the
 * image processing program.
 */
public class ImagePanelImpl extends JPanel implements ImagePanel {
  private IPFeature feature;
  private Image image; // image to be displayed
  private final ImageProcessingModelState modelState;

  /**
   * Constructs a new Image Panel with the provided modelState.
   * @param modelState the model state which the image panel receives the images from
   * @throws IllegalArgumentException if the model state is null
   */
  public ImagePanelImpl(ImageProcessingModelState modelState) throws IllegalArgumentException {
    super();
    if (modelState == null) {
      throw new IllegalArgumentException("The argument cannot be null.");
    }
    this.modelState = modelState;

    this.setBackground(Color.PINK);
  }

  @Override
  public void registerFeature(IPFeature feature) {
    this.feature = feature;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (this.image != null) {
      g.drawImage(this.image, 0, 0, this);
    }
  }

  @Override
  public void setImage(ImageClass image) {
    BufferedImage buffImage = ImageUtil.getBuffImage(image);
    if (buffImage.getWidth() < this.getWidth() || buffImage.getHeight() < this.getHeight()) {
      this.image = buffImage.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
    } else {
      this.image = buffImage;
      this.setSize(new Dimension(buffImage.getWidth(), buffImage.getHeight()));
      //TODO: the image panel is the correct size, get it to be scrollable on the GUI
    }
  }
}
