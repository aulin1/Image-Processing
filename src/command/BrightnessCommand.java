package command;

/**
 * This class represents a command that brightens or darkens an image.
 */
public class BrightnessCommand extends FilterCommand {
  private final int factor;

  /**
   * Constructs a new brightness command.
   *
   * @param factor the brightness factor to process the image
   */
  public BrightnessCommand(int factor) {
    this.factor = factor;
  }

  @Override
  protected int[] getCorrectValues(int[][][] img, int row, int col) {
    int[] vals = new int[3];
    vals[0] = this.checkLimits(img[row][col][0] + factor);
    vals[1] = this.checkLimits(img[row][col][1] + factor);
    vals[2] = this.checkLimits(img[row][col][2] + factor);
    return vals;
  }
}