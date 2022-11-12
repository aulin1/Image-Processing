package histogram;

import image.ImageClass;

/**
 * An interface which creates a histogram to be displayed.
 * */
public interface IHistogram {

  /**
   * Updates the histogram values to a new image.
   *
   * @param img the image that the new histogram uses.
   * */
  void updateHistogram(ImageClass img);

  /**
   * Gets the values for the histogram.
   *
   * @return the values for the histogram as an integer array. The first index holds the color
   * values and the second index holds the actual value for the histogram.
   * */
  int[][] getHistogram();
}
