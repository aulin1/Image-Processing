package histogram;

import java.util.Arrays;

import image.ImageClass;

/**
 * A class that creates a simple (uneditable) histogram.
 * */
public class SimpleHistogram implements IHistogram{
  int[][] values;
  int max;

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
    fillValues(img.getImage(), this.max);
  }

  /**
   * A helper function which fills out the values array given a 3D array.
   *
   * @param img the 3D array to create the histogram for.
   * */
  private void fillValues(int[][][] img, int max){
    int colors = img[0][0].length; //the number of colors there are
    if(max != this.max || this.values == null || colors != this.values.length){
      this.values = new int[colors][max + 1];
    }
    for(int i = 0; i < img.length; i++){
      for(int j = 0; j < img[0].length; j++){
        for(int k = 0; k < colors; k++){
          this.values[k][img[i][j][k]]++; //Add one to the correct slot.
        }
      }
    }
  }

  @Override
  public void updateHistogram(ImageClass img) {
    fillValues(img.getImage(), img.getMax());
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
}
