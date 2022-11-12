import org.junit.Before;
import org.junit.Test;

import histogram.IHistogram;
import histogram.SimpleHistogram;
import image.ImageClass;
import image.ImageClassImpl;

import static org.junit.Assert.assertArrayEquals;

/**
 * Tests for the histogram class.
 * */
public class HistogramTest {

  ImageClass img1;
  ImageClass img2;
  ImageClass img3;
  int[][] result1;
  int[][] result2;
  int[][] result3;

  @Before
  public void createImageClasses(){
    int[][][] i = new int[][][]{{{0, 1, 2}, {0, 0, 0}, {1, 1, 2}}, {{5, 5, 5},
            {10, 10, 2}, {0, 1, 2}}, {{0, 1, 2}, {6, 10, 10}, {9, 9, 9}}};
    int[][][] j = new int[][][]{{{1, 1, 1}, {1, 2, 3}, {1, 3, 3}}, {{1, 1, 1}, {5, 5, 5},
            {6, 4, 6}}, {{6, 6, 6}, {1, 3, 5}, {6, 6, 6}}};
    int[][][] k = new int[][][] {{{5, 5}, {1, 0}}, {{5, 2}, {2, 3}}};
    img1 = new ImageClassImpl(i, 10);
    img2 = new ImageClassImpl(j, 7);
    img3 = new ImageClassImpl(k, 5);
    result1 = new int[][]{{4, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1},
            {1, 4, 0, 0, 0, 1, 0, 0, 0, 1, 2}, {1, 0, 5, 0, 0, 1, 0, 0, 0, 1, 1}};
    result2 = new int[][]{{0, 5, 0, 0, 0, 1, 3, 0}, {0, 2, 1, 2, 1, 1, 2, 0},
            {0, 2, 0, 2, 0, 2, 3, 0}};
    result3 = new int[][]{{0, 1, 1, 0, 0, 2}, {1, 0, 1, 1, 0, 1}};
  }

  /**
   * Tests if the histogram constructor throws an exception if the ImageClass is null.
   * */
  @Test(expected = IllegalArgumentException.class)
  public void testNullConstructor(){
    IHistogram test = new SimpleHistogram(null);
  }

  /**
   * Tests if it gives the correct histogram given a ImageClass.
   * */
  @Test
  public void testHistogram(){
    IHistogram test = new SimpleHistogram(img1);
    IHistogram test2 = new SimpleHistogram(img2);
    IHistogram test3 = new SimpleHistogram(img3);
    int[][] vals = test.getHistogram();
    assertArrayEquals(result1, vals);
    assertArrayEquals(result2, test2.getHistogram());
    assertArrayEquals(result3, test3.getHistogram());
  }

  /**
   * Tests if updateHistogram updates correctly.
   * */
  @Test
  public void testUpdateHistogram(){
    IHistogram test = new SimpleHistogram(img1);
    test.updateHistogram(img2);
    assertArrayEquals(result2, test.getHistogram());
    test.updateHistogram(img3);
    assertArrayEquals(result3, test.getHistogram());
    test.updateHistogram(img1);
    assertArrayEquals(result1, test.getHistogram());
  }

  /**
   * Tests if getHistogram doesn't allow the user to edit it.
   * */
  @Test
  public void testEditHistogram(){
    IHistogram test = new SimpleHistogram(img1);
    int[][] arr = test.getHistogram();
    for(int i = 0; i < arr[0].length; i++){
      arr[0][i] = 0;
      arr[1][i] = 1;
      arr[2][i] = 2;
    }
    assertArrayEquals(result1, test.getHistogram());
  }
}
