package view;

import java.awt.*;

import javax.swing.*;

import controller.IPFeature;

/**
 * This class represents a menu displayed on the GUI of the image processing program. The menu
 * panel contains all buttons which direct to its respective image processing methods.
 */
public class MenuPanel extends JPanel implements DisplayedPanel {
  private IPFeature feature;
  // a buncho JButtons

  public MenuPanel() {
    this.setBackground(Color.orange);
  }

  @Override
  public void registerFeature(IPFeature feature) {
    this.feature = feature;
  }
}
