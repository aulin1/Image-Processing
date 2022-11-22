package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

import controller.IPFeature;
import controller.ImageUtil;
import image.ImageClass;

/**
 * This class represents an implementation of the ImageProcessingView interface using Swing.
 */
public class ImageProcessingGUI extends JFrame implements ImageProcessingView {
  // panel contains the menu and its selections
  private MenuBar menuBar;
  // panel which renders the image that is currently being worked on
  private JLabel imagePanel;
  // scroll pane for the image panel
  private JScrollPane imageScroll;
  // panel which displays the logistic of the image currently being processed
  private LogisticPanelImpl logPanel;
  // width of the screen
  private final int screenWidth = 800;
  // height of the screen
  private final int screenHeight = 600;

  /**
   * Constructs the default ImageProcessingGUI with the provided model state.
   *
   * @throws IllegalArgumentException if the model state provided is null
   */
  public ImageProcessingGUI() throws IllegalArgumentException {
    super("Image Processing Program");

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));
    setMinimumSize(new Dimension(this.screenWidth, this.screenHeight));
    setResizable(false);

    this.createComponents();

    pack();
    refresh();
  }

  /**
   * Constructs a GUI for testing view outputs/communication with the controller.
   */
  public ImageProcessingGUI(MenuBar menuBar) throws IllegalArgumentException {
    if (menuBar == null) {
      throw new IllegalArgumentException("The argument cannot be null.");
    }
    this.menuBar = menuBar;
    this.logPanel = new LogisticPanelImpl();
  }

  /**
   * Create the required components for the Image Processing Program and align it appropriately
   * in a Border Layout.
   */
  private void createComponents() {
    this.imagePanel = new JLabel();
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
    refresh();
  }

  @Override
  public void renderImage(ImageClass image) {
    Image buffImage = ImageUtil.getBuffImage(image);
    if (image.getWidth() < this.imagePanel.getWidth()
            || image.getHeight() < this.imagePanel.getHeight()) {
      buffImage = buffImage.getScaledInstance(this.imageScroll.getWidth(),
              this.imageScroll.getHeight(), Image.SCALE_DEFAULT);
    }
    ImageIcon imageDisplayed = new ImageIcon(buffImage);
    this.imagePanel.setIcon(imageDisplayed);
    this.logPanel.changeLogistics(image);
    refresh();
  }

  @Override
  public void registerFeature(IPFeature feature) {
    this.menuBar.registerFeature(feature);
    this.logPanel.registerFeature(feature);
  }
}
