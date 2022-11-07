package command;

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
    int[] vals = new int[3];
    double[][] weights = this.getWeightMatrix();
    int midX = weights[0].length / 2;
    int midY = weights.length / 2;
    for(int i = 0; i < weights[0].length; i++){
      for(int j = 0; j < weights.length; j++){
        int x = row + (i - midX);
        int y = col + (j - midY);
        if(!(x < 0 || y < 0 || x >= img[0].length || y >= img.length)){
          vals[0] = vals[0] + (int) (img[x][y][0] * weights[j][i]);
          vals[1] = vals[1] + (int) (img[x][y][1] * weights[j][i]);
          vals[2] = vals[2] + (int) (img[x][y][2] * weights[j][i]);
        }
      }
    }
    return vals;
  }
}
