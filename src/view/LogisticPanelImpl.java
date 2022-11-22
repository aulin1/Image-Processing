package view;

import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;

import controller.IPFeature;
import image.ImageClass;

/**
 * This class represents a logistic panel displayed on the image processing program. It consists
 * of a histogram and log panel.
 */
public class LogisticPanelImpl extends JPanel implements LogisticPanel {
  private HistogramPanel histogramPanel;
  private JTextArea log;
  private JScrollPane logPane;
  private JButton clearLogButton;

  /**
   * Default constructor for a logistic panel.
   */
  public LogisticPanelImpl() {
    super();
    this.setBackground(Color.BLUE);
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    createComponent();
    setAlignment();
  }

  private void createComponent() {
    this.histogramPanel = new HistogramPanel();
    this.histogramPanel.setSize(this.getWidth() / 2, this.getHeight() / 2);
    this.histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram:"));
    this.histogramPanel.setMinimumSize(new Dimension(this.getWidth() / 2,
            this.getHeight() / 2));
    this.add(this.histogramPanel);

    this.log = new JTextArea("The progress of the program will be shown here", 10,
            this.getWidth());
    this.log.setLineWrap(true);
    this.log.setAutoscrolls(true);
    this.log.setEditable(false);

    this.logPane = new JScrollPane(this.log);
    this.logPane.setBorder(BorderFactory.createTitledBorder("Log:"));
    this.add(this.logPane);

    this.clearLogButton = new JButton("Clear Log");
    this.clearLogButton.addActionListener(e -> log.setText("Log cleared!"
            + System.lineSeparator()));
    this.add(this.clearLogButton);
  }

  /**
   * Align the components to the center of this panel.
   */
  private void setAlignment() {
    this.histogramPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.logPane.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.clearLogButton.setAlignmentX(Component.CENTER_ALIGNMENT);
  }

  @Override
  public void registerFeature(IPFeature feature) {
    this.histogramPanel.registerFeature(feature);
  }

  @Override
  public void renderLog(String text) {
    this.log.setText(this.log.getText() + System.lineSeparator() + text);
  }

  @Override
  public void changeLogistics(ImageClass image) {
    this.histogramPanel.setImage(image);
  }
}
