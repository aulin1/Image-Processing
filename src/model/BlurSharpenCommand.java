package model;

/**
 * A class that represents a way to blur or sharpen an image.
 * */
abstract class BlurSharpenCommand extends FilterCommand {

  /**
   * Returns the matrix by which you should weight the values. The matrix must have odd dimensions.
   *
   * @return the matrix of values for the correct colors.
   * */
  abstract double[][] getWeightMatrix();

  @Override
  protected int[] getCorrectValues(int[][][] img, int row, int col){
    double[] vals = new double[3];
    double[][] weights = this.getWeightMatrix();
    int midX = weights[0].length / 2;
    int midY = weights.length / 2;
    for(int i = 0; i < weights.length; i++){ //height
      for(int j = 0; j < weights[0].length; j++){ //width
        int x = col + (j - midX);
        int y = row + (i - midY);
        if(!(x < 0 || y < 0 || x >= img[0].length || y >= img.length)){
          vals[0] = vals[0] + (img[y][x][0] * weights[i][j]);
          vals[1] = vals[1] + (img[y][x][1] * weights[i][j]);
          vals[2] = vals[2] + (img[y][x][2] * weights[i][j]);
        }
      }
    }
    return new int[]{this.checkLimits((int) Math.round(vals[0])),
            this.checkLimits((int) Math.round(vals[1])),
            this.checkLimits((int) Math.round(vals[2]))};
  }
}
