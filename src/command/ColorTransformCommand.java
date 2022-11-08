package command;

/**
 * A command that gets a color changed image from another image.
 * */
abstract class ColorTransformCommand extends FilterCommand{

  /**
   * Returns the matrix by which you should multiply the values to get the correct colors. The
   * matrix must be a 3 x 3 matrix.
   *
   * @return the matrix of values for the correct colors.
   * */
  abstract double[][] getColorMatrix();

  @Override
  protected int[] getCorrectValues(int[][][] image, int row, int col){
    double[][] vals = this.getColorMatrix();
    int[] colors =  new int[3];
    colors[0] = (int) Math.round(vals[0][0] * image[row][col][0] + vals[0][1] * image[row][col][1]
            + vals[0][2] * image[row][col][2]);
    colors[1] = (int) Math.round(vals[1][0] * image[row][col][0] + vals[1][1] * image[row][col][1]
            + vals[1][2] * image[row][col][2]);
    colors[2] = (int) Math.round(vals[2][0] * image[row][col][0] + vals[2][1] * image[row][col][1]
            + vals[2][2] * image[row][col][2]);
    colors[0] = this.checkLimits(colors[0]);
    colors[1] = this.checkLimits(colors[1]);
    colors[2] = this.checkLimits(colors[2]);
    return colors;
  }

}
