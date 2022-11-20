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
  private MenuBar menuBar;
  // panel which renders the image that is currently being worked on
  private ImagePanelImpl imagePanel;
  // scroll pane for the image panel
  private JScrollPane imageScroll;
  // panel which displays the logistic of the image currently being processed
  private LogisticPanelImpl logPanel;
  // observable model of all images being processed
  private final ImageProcessingModelState modelState;
  // display message from the controller to the user
  private JOptionPane messagePane;
  // width of the screen
  private final int screenWidth = 800;
  // height of the screen
  private final int screenHeight = 600;

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
    setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));
    setMinimumSize(new Dimension(this.screenWidth, this.screenHeight));
    setResizable(false); //TODO: make the screen resizable with resizable components

    this.createComponents();

    pack();
    refresh();
  }

  /**
   * Create the required components for the Image Processing Program and align it appropriately
   * in a Border Layout.
   */
  private void createComponents() {
    this.imagePanel = new ImagePanelImpl(this.modelState);
    this.imageScroll = new JScrollPane(this.imagePanel);
    this.imageScroll.setPreferredSize(new Dimension(this.screenWidth / 3 * 2, this.screenHeight));
    this.add(this.imageScroll, BorderLayout.LINE_START);

    this.menuBar = new MenuBar();
    this.menuBar.setPreferredSize(new Dimension(this.screenWidth, this.screenHeight / 14));
    this.add(menuBar, BorderLayout.NORTH);

    this.logPanel = new LogisticPanelImpl();
    this.logPanel.setPreferredSize(new Dimension(this.screenWidth / 3, this.screenHeight));
    this.add(this.logPanel, BorderLayout.EAST);
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
    this.logPanel.renderLog(message);
  }

  @Override
  public void renderImage(ImageClass image) {
    this.imagePanel.setImage(image);
  }

  @Override
  public void registerFeature(IPFeature feature) {
    this.menuBar.registerFeature(feature);
    this.imagePanel.registerFeature(feature);
    this.logPanel.registerFeature(feature);
  }
}
