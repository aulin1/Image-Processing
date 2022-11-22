package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.AbstractButton;

import controller.IPFeature;
import view.inputretrieve.BrightenInput;
import view.inputretrieve.ImageTransformInput;
import view.inputretrieve.InputRetrieveCommand;
import view.inputretrieve.LoadInput;
import view.inputretrieve.SaveInput;

/**
 * This class represents a menu displayed on the GUI of the image processing program. The menu
 * panel contains all buttons which direct to its respective image processing methods.
 */
public class MenuBar extends JMenuBar implements ImageProcessingPanel, ViewTesting {
  private final Map<String, Function<ImageProcessingPanel, InputRetrieveCommand>> inputRetrieveMap =
          new HashMap<>();
  private String imageName; // name of the image that is currently being processed
  private IPFeature feature;
  private final List<JMenuItem> ipMenuItem = new ArrayList<>();
  // contains all items in the image processing menu

  /**
   * Default constructor for menu bar.
   */
  public MenuBar() {
    this.setLayout(new FlowLayout(FlowLayout.LEFT));
    this.setBackground(Color.orange);

    addFileMenu();
    addIPMenu();
    initInputRetMap();
  }

  /**
   * Initialize the input retrieve map with all the needed function objects.
   */
  private void initInputRetMap() {
    this.inputRetrieveMap.put("load", LoadInput::new);
    this.inputRetrieveMap.put("save", SaveInput::new);
    this.inputRetrieveMap.put("brighten", BrightenInput::new);
  }

  /**
   * Add the file menu to this menu bar.
   */
  private void addFileMenu() {
    JMenu currentMenu = new JMenu("File");
    this.add(currentMenu);

    String[] fileMenuItem = new String[]{"Save file", "Load file"};
    String[] fileComm = new String[]{"save", "load"};
    setUpMenuItem(currentMenu, fileMenuItem, fileComm);
  }

  /**
   * Add the image processing menu to this menu bar.
   */
  private void addIPMenu() {
    JMenu ipMenu = new JMenu("Image Processing");
    this.add(ipMenu);

    String[] ipMenuItem = new String[]{"Color transform", "Flip", "Filter", "Blur", "Sharpen"};
    String[] ipComm = new String[]{"gaussian-blur", "sharpen"};
    for (int i = 0; i < ipMenuItem.length; i++) {
      JMenuItem menuItem;
      if (i < 3) {
        menuItem = new JMenu(ipMenuItem[i]);
        this.ipMenuItem.add(menuItem);
        setSubItem(ipMenuItem[i], i);
      } else {
        menuItem = new JMenuItem(ipMenuItem[i]);
        menuItem.setActionCommand(ipComm[i - 3]);
        menuItem.addActionListener(new MenuItemClicked(menuItem));
        this.ipMenuItem.add(menuItem);
      }
      ipMenu.add(menuItem);
    }
  }

  /**
   * Set up the sub items based on the menu provided.
   *
   * @param menu  the menu to be set up
   * @param order the order of the menu in the upper list
   */
  private void setSubItem(String menu, int order) {
    JMenuItem currentMenu = this.ipMenuItem.get(order);
    switch (menu) {
      case "Color transform":
        String[] colorMenu = new String[]{"Red", "Blue", "Green", "Sepia"};
        String[] colorComm = new String[]{"red-component", "blue-component", "green-component",
            "sepia"};
        setUpMenuItem(currentMenu, colorMenu, colorComm);
        break;
      case "Flip":
        String[] flipMenu = new String[]{"Horizontal flip", "Vertical flip"};
        String[] flipComms = new String[]{"horizontal-flip", "vertical-flip"};
        setUpMenuItem(currentMenu, flipMenu, flipComms);
        break;
      case "Filter":
        String[] filterMenu = new String[]{"Intensity", "Value", "Luma", "Brightness"};
        String[] filterComm = new String[]{"intensity", "value", "luma", "brighten"};
        setUpMenuItem(currentMenu, filterMenu, filterComm);
        break;
      default:
        throw new IllegalArgumentException("The provided menu is not supported.");
    }
  }

  /**
   * A helper for setting up submenu/menu items in a menu.
   *
   * @param currentMenu   the menu which these submenus are being added to
   * @param menuItemNames the names of the submenus to be seen on the GUI (in order)
   * @param menuItemComms the action commands of the submenus (in order)
   * @throws IllegalArgumentException if any of the argumenst are null
   */
  private void setUpMenuItem(JMenuItem currentMenu, String[] menuItemNames, String[] menuItemComms)
          throws IllegalArgumentException {
    if (currentMenu == null || menuItemNames == null || menuItemComms == null) {
      throw new IllegalArgumentException("The arguments cannot be null.");
    }
    for (int i = 0; i < menuItemNames.length; i++) {
      JMenuItem menuItem = new JMenuItem(menuItemNames[i]);
      menuItem.setActionCommand(menuItemComms[i]);
      menuItem.addActionListener(new MenuItemClicked(menuItem));
      currentMenu.add(menuItem);
      this.ipMenuItem.add(menuItem);
    }
  }

  /**
   * Get the correct input for the controller based on the button provided.
   *
   * @param button the button which an action had performed on
   * @return the correct String input from the user interaction
   * @throws IllegalStateException if the command/button is not supported
   */
  private String getCorrectInput(AbstractButton button) throws IllegalStateException {
    String actionComm = button.getActionCommand();
    Function<ImageProcessingPanel, InputRetrieveCommand> commFunc =
            this.inputRetrieveMap.getOrDefault(actionComm, null);
    if (commFunc == null) {
      InputRetrieveCommand defaultComm = new ImageTransformInput(this);
      return defaultComm.getCorrectInput(button);
    } else {
      InputRetrieveCommand comm = commFunc.apply(this);
      return comm.getCorrectInput(button);
    }
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

  @Override
  public String getImageName() {
    return this.imageName;
  }

  /**
   * Find the correct menu item that has such command as its action command.
   *
   * @param command the command expected from a menu item in the list
   * @param list the list of the menu items that expected to have the menu item in finding
   * @return the menu item with the provided action command
   * @throws IllegalArgumentException if the command is not supported by any menu item
   */
  private JMenuItem getCorrectItem(String command, List<JMenuItem> list) throws IllegalArgumentException {
    for (JMenuItem menuItem : list) {
      if (menuItem.getActionCommand().equals(command)) {
        return menuItem;
      }
    }
    throw new IllegalArgumentException("No menu item found supported the inputted command.");
  }

  @Override
  public void testViewOutput(String actionCommand) {
    JMenuItem menuItem = getCorrectItem(actionCommand, this.ipMenuItem);
    menuItem.doClick();
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
