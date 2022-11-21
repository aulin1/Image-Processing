package histogram;

import image.ImageClass;
import image.ImageClassImpl;

/**
 * A class that creates a simple (uneditable) histogram.
 * */
public class SimpleHistogram implements IHistogram{
  private int[][] values;
  private int max;
  private ImageClass img;
  private int[][][] image;

  /**
   * A constructor that creates a histogram from an Image.
   *
   * @param img the image to create the histogram from.
   * @throws IllegalArgumentException if the img is null.
   * */
  public SimpleHistogram(ImageClass img) throws IllegalArgumentException {
    if(img == null){
      throw new IllegalArgumentException("Cannot be null!");
    }
    this.max = img.getMax();
    this.img = img;
    fillValues(img.getImage(), this.max);
    fillGraph();
  }

  /**
   * A helper function which fills out the values array given a 3D array.
   *
   * @param img the 3D array to create the histogram for.
   *
   * */
  private void fillValues(int[][][] img, int max){
    if(max != this.max || this.values == null){
      this.values = new int[4][max + 1];
      this.max = max + 1;
    }

    for(int i = 0; i < img.length; i++){
      for(int j = 0; j < img[0].length; j++){
        for(int k = 0; k < 3; k++){
          this.values[k][img[i][j][k]]++; //Add one to the correct slot.
        }
        int avg = (int) Math.round((img[i][j][0] + img[i][j][1]
                + img[i][j][2]) / 3.0);
        this.values[3][avg]++;
      }
    }
  }

  @Override
  public void updateHistogram(ImageClass img) {
    this.img = img;
    fillValues(img.getImage(), img.getMax());
    fillGraph();
  }

  @Override
  public int[][] getHistogram() {
    int[][] arr = new int[values.length][values[0].length];
    for(int i = 0; i < values.length; i++){
      for(int j = 0; j < values[0].length; j++){
        arr[i][j] = values[i][j];
      }
    }
    return arr;
  }

  /**
   * Fills a column with the correct values for the histogram.
   *
   * @param col the column to be filled
   * @param red whether red counts for this pixel
   * @param green whether green counts for this pixel
   * @param blue whether blue counts for this pixel
   * @param intensity the amount of pixels with that intensity for that column.
   * */
  private void fillPixel(int row, int col, boolean red, boolean green,
                         boolean blue, boolean intensity){
    if(!red && !green && !blue && !intensity){
      image[row][col] = new int[]{255, 255, 255};
    } else if(!red && !green && !blue && intensity){
      image[row][col] = new int[]{100, 100, 100};
    } else {
      if (red) {
        image[row][col][0] = 200;
        if(intensity){
          image[row][col][0] = image[row][col][0] - 10;
        }
      }
      if (green) {
        image[row][col][1] = 200;
        if(intensity){
          image[row][col][1] = image[row][col][1] - 10;
        }
      }
      if(blue) {
        image[row][col][2] = 200;
        if(intensity){
          image[row][col][2] = image[row][col][2] - 10;
        }
      }
    }
  }

  /**
   * A helper function which creates an image of the histogram graph.
   * */
  private void fillGraph(){
    int num = 0;
    for(int i = 0; i < max; i++){
      for(int j = 0; j < values.length; j++){
        num = Math.max(num, values[j][i]);
      }
    }
    image = new int[num][max][3];
    for(int i = 0; i < values[0].length; i++){ //each column for the histogram
      for(int j = 0; j < image.length; j++){//the rows for the histogram
        int col = num - j;
        fillPixel(j, i, values[0][i] >= col, values[1][i] >= col,
                values[2][i] >= col, values[3][i] >= col);
      }
    }
  }

  @Override
  public ImageClass getGraph(){
    ImageClass returnImage = new ImageClassImpl(image, 255);
    return returnImage;
  }
}
