package view;

import java.awt.*;
import java.util.Objects;

import javax.swing.*;

import controller.IPFeature;
import image.ImageClass;
import model.ImageProcessingModelState;

/**
 * This class represents an implementation of the ImageProcessingView interface using Swing.
 */
public class ImageProcessingGUI extends JFrame implements ImageProcessingView {
  // panel contains the menu and its selections
  private final MenuPanel menuPanel;
  // panel which renders the image that is currently being worked on
  private final ImagePanelImpl imagePanel;
  // scroll pane for the image panel
  private final JScrollPane imageScroll;
  // observable model of all images being processed
  private final ImageProcessingModelState modelState;
  // displays the log of current activity by the program
  private JLabel log;
  // scroll pane for the log
  private JScrollPane logScroll;

  /**
   * Constructs the default ImageProcessingGUI with the provided model state.
   *
   * @param state the state of the images which are being processed by the program
   * @throws IllegalArgumentException if the model state provided is null
   */
  public ImageProcessingGUI(ImageProcessingModelState state) throws IllegalArgumentException {
    super("Image Processing Program");
    this.modelState = Objects.requireNonNull(state);

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    setSize(new Dimension(750, 500));
    setPreferredSize(new Dimension(750, 500));
    setMinimumSize(new Dimension(750, 500));

    // initialize the image panel with the model state
    this.imagePanel = new ImagePanelImpl(this.modelState);
    this.imageScroll = new JScrollPane(this.imagePanel);
    this.imagePanel.setPreferredSize(new Dimension(this.getWidth() * (2 / 3),
            this.getHeight() / 2));
    this.add(this.imageScroll, BorderLayout.LINE_START);

    // initialize the menu panel
    this.menuPanel = new MenuPanel();
    this.menuPanel.setPreferredSize((new Dimension(this.getWidth(), this.getHeight() / 14)));
    this.add(menuPanel, BorderLayout.NORTH);

    pack();
    refresh();
  }

  private void setImagePanel() {
    this.imagePanel.setPreferredSize(new Dimension(this.getWidth() * (2 / 3),
            this.getHeight() / 2));
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void renderMessage(String message) {
    this.log.setText(this.log.getText() + "\n" + message);
  }

  @Override
  public void renderImage(ImageClass image) {
    this.imagePanel.setImage(image);
  }

  @Override
  public void registerFeature(IPFeature feature) {
    this.menuPanel.registerFeature(feature);
    this.imagePanel.registerFeature(feature);
  }
}
