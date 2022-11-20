package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.IPFeature;

/**
 * This class represents a menu displayed on the GUI of the image processing program. The menu
 * panel contains all buttons which direct to its respective image processing methods.
 */
public class MenuBar extends JMenuBar implements ImageProcessingPanel {
  private String imageName; // name of the image that is currently being processed
  private IPFeature feature;
  private JMenu fileMenu;
  private List<JMenuItem> fileMenuItem = new ArrayList<>(); // contains all items in the file menu
  private JMenu ipMenu;
  private List<JMenuItem> ipMenuItem = new ArrayList<>();
  // contains all items in the image processing menu

  /**
   * Default constructor for menu bar.
   */
  public MenuBar() {
    this.setLayout(new FlowLayout(FlowLayout.LEFT));
    this.setBackground(Color.orange);

    addComponent();
  }

  /**
   * Add these components to this panel.
   */
  private void addComponent() {
    addFileMenu();
    addIPMenu();
  }

  /**
   * Add the file menu to this menu bar.
   */
  private void addFileMenu() {
    this.fileMenu = new JMenu("File");
    this.add(this.fileMenu);

    //TODO: support open recent
    String[] fileMenuItem = new String[]{"Save file", "Load file", "Change file name"};
    String[] fileComm = new String[]{"save ", "load ", "change-name "};
    for (int i = 0; i < fileMenuItem.length; i++) {
      JMenuItem menuItem = new JMenuItem(fileMenuItem[i]);
      menuItem.setActionCommand(fileComm[i]);
      menuItem.addActionListener(new MenuItemClicked(menuItem));
      this.fileMenuItem.add(menuItem);
      this.fileMenu.add(menuItem);
    }
  }

  /**
   * Add the image processing menu to this menu bar.
   */
  private void addIPMenu() {
    this.ipMenu = new JMenu("Image Processing");
    this.add(this.ipMenu);

    String[] ipMenuItem = new String[]{"Color transform", "Flip", "Filter", "Blur", "Sharpen"};
    for (int i = 0; i < ipMenuItem.length; i++) {
      JMenuItem menuItem;
      if (i < 3) {
        menuItem = new JMenu(ipMenuItem[i]);
        this.ipMenuItem.add(menuItem);
        setSubItem(ipMenuItem[i], i);
      } else {
        menuItem = new JMenuItem(ipMenuItem[i]);
        this.ipMenuItem.add(menuItem);
      }
      this.ipMenu.add(menuItem);
    }
  }

  /**
   * Set up the sub items based on the menu provided.
   *
   * @param menu the menu to be set up
   * @param order the order of the menu in the upper list
   */
  private void setSubItem(String menu, int order) {
    JMenuItem currentMenu = this.ipMenuItem.get(order);
    switch(menu) {
      case "Color transform" :
        String[] colorMenu = new String[]{"Red", "Blue", "Green", "Sepia", "Luma"};
        for (String s : colorMenu) {
          JMenuItem menuItem = new JMenuItem(s);
          currentMenu.add(menuItem);
        }
        break;
      case "Flip" :
        JMenuItem menuItem = new JMenuItem("Horizontal flip");
        this.ipMenuItem.get(order).add(menuItem);
        menuItem = new JMenuItem("Vertical flip");
        currentMenu.add(menuItem);
        break;
      case "Filter" :
        String[] filterMenu = new String[]{"Intensity", "Value", "Brightness"};
        for (String s : filterMenu) {
          menuItem = new JMenuItem(s);
          currentMenu.add(menuItem);
        }
        break;
      default :
        throw new IllegalArgumentException("The provided menu is not supported.");
    }
  }

  private String getCorrectInput(AbstractButton button) {
    switch(button.getActionCommand()) {
      case "load " :
        return openFileChooser(button);
      default:
        throw new IllegalArgumentException("The button is not supported by this method.");
    }
  }

  /**
   * Display a panel for choosing file and return the filepath of the file to be opened.
   *
   * @param component the component which triggers this method when an action is performed
   * @return the filepath of the file to be opened
   * @throws IllegalArgumentException if the component is null
   */
  private String openFileChooser(Component component) throws IllegalArgumentException {
    if (component == null) {
      throw new IllegalArgumentException("The argument cannot be null.");
    }
    String filePath = "";
    String fileName = "";

    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Images", "jpg", "png", "bpm", "ppm");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(component);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      filePath = f.getAbsolutePath();
      fileName = f.getName();
    }
    return filePath + " " + fileName;
  }

  @Override
  public void registerFeature(IPFeature feature) throws IllegalArgumentException {
    if (feature == null) {
      throw new IllegalArgumentException("The argument cannot be null.");
    }
    this.feature = feature;
  }

  @Override
  public void setImageName(String imageName) {
    if (imageName == null) {
      throw new IllegalArgumentException("The argument cannot be null.");
    }
    this.imageName = imageName;
  }

  /**
   * This class emits message when the menu item was clicked.
   */
  private class MenuItemClicked implements ActionListener {
    private final AbstractButton button;

    private MenuItemClicked(AbstractButton button) {
      this.button = Objects.requireNonNull(button);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
      feature.processInput(e.getActionCommand() + " " + getCorrectInput(button));
    }
  }
}
