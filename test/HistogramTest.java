import org.junit.Before;
import org.junit.Test;

import histogram.IHistogram;
import histogram.SimpleHistogram;
import image.ImageClass;
import image.ImageClassImpl;

import static org.junit.Assert.assertArrayEquals;

/**
 * Tests for the histogram class.
 */
public class HistogramTest {

  ImageClass img1;
  ImageClass img2;
  int[][] result1;
  int[][] result2;
  int[][][] graph1;
  int[][][] graph2;

  @Before
  public void createImageClasses() {
    int[][][] i = new int[][][]{{{0, 1, 2}, {0, 0, 0}, {1, 1, 2}}, {{5, 5, 5},
            {10, 10, 1}, {0, 1, 2}}, {{0, 1, 2}, {6, 10, 10}, {9, 9, 9}}};
    int[][][] j = new int[][][]{{{1, 1, 1}, {1, 2, 3}, {1, 3, 3}}, {{1, 1, 1}, {5, 5, 5},
            {6, 4, 6}}, {{6, 6, 6}, {1, 3, 5}, {6, 6, 6}}};
    img1 = new ImageClassImpl(i, 10);
    img2 = new ImageClassImpl(j, 7);
    result1 = new int[][]{{4, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1},
        {1, 4, 0, 0, 0, 1, 0, 0, 0, 1, 2}, {1, 1, 4, 0, 0, 1, 0, 0, 0, 1, 1},
        {1, 4, 0, 0, 0, 1, 0, 1, 0, 2, 0}};
    result2 = new int[][]{{0, 5, 0, 0, 0, 1, 3, 0}, {0, 2, 1, 2, 1, 1, 2, 0},
        {0, 2, 0, 2, 0, 2, 3, 0}, {0, 2, 2, 1, 0, 2, 2, 0}};
    graph1 = new int[][][]
        {{{200, 0, 0}, {0, 190, 0}, {0, 0, 200}, {255, 255, 255}, {255, 255, 255}, {255, 255, 255},
        {255, 255, 255}, {255, 255, 255}, {255, 255, 255}, {255, 255, 255}, {255, 255, 255}},
        {{200, 0, 0}, {0, 190, 0}, {0, 0, 200}, {255, 255, 255}, {255, 255, 255},
        {255, 255, 255}, {255, 255, 255}, {255, 255, 255},
        {255, 255, 255}, {255, 255, 255}, {255, 255, 255}},
        {{200, 0, 0}, {0, 190, 0}, {0, 0, 200}, {255, 255, 255}, {255, 255, 255}, {255, 255, 255},
        {255, 255, 255}, {255, 255, 255}, {255, 255, 255}, {100, 100, 100}, {0, 200, 0}},
        {{190, 190, 190}, {190, 190, 190}, {0, 0, 200}, {255, 255, 255},
        {255, 255, 255}, {190, 190, 190}, {200, 0, 0}, {100, 100, 100}, {255, 255, 255},
        {190, 190, 190}, {200, 200, 200}}};
    graph2 = new int[][][]
        {{{255, 255, 255}, {200, 0, 0}, {255, 255, 255}, {255, 255, 255}, {255, 255, 255},
        {255, 255, 255}, {255, 255, 255}, {255, 255, 255}},
        {{255, 255, 255}, {200, 0, 0}, {255, 255, 255}, {255, 255, 255}, {255, 255, 255},
        {255, 255, 255}, {255, 255, 255}, {255, 255, 255}},
        {{255, 255, 255}, {200, 0, 0}, {255, 255, 255}, {255, 255, 255},
        {255, 255, 255}, {255, 255, 255}, {200, 0, 200}, {255, 255, 255}},
        {{255, 255, 255}, {190, 190, 190}, {100, 100, 100}, {0, 200, 200},
        {255, 255, 255}, {0, 0, 190}, {190, 190, 190}, {255, 255, 255}},
        {{255, 255, 255}, {190, 190, 190}, {0, 190, 0}, {0, 190, 190},
        {0, 200, 0}, {190, 190, 190}, {190, 190, 190}, {255, 255, 255}}};
  }

  /**
   * Tests if the histogram constructor throws an exception if the ImageClass is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullConstructor() {
    IHistogram test = new SimpleHistogram(null);
  }

  /**
   * Tests if it gives the correct histogram and graph given a ImageClass.
   */
  @Test
  public void testHistogram() {
    IHistogram test = new SimpleHistogram(img1);
    IHistogram test2 = new SimpleHistogram(img2);
    assertArrayEquals(result1, test.getHistogram());
    assertArrayEquals(graph1, test.getGraph().getImage());
    assertArrayEquals(result2, test2.getHistogram());
    assertArrayEquals(graph2, test2.getGraph().getImage());
  }

  /**
   * Tests if updateHistogram updates correctly.
   */
  @Test
  public void testUpdateHistogram() {
    IHistogram test = new SimpleHistogram(img1);
    test.updateHistogram(img2);
    assertArrayEquals(result2, test.getHistogram());
    assertArrayEquals(graph2, test.getGraph().getImage());
    test.updateHistogram(img1);
    assertArrayEquals(result1, test.getHistogram());
    assertArrayEquals(graph1, test.getGraph().getImage());
  }

  /**
   * Tests if getHistogram doesn't allow the user to edit it.
   */
  @Test
  public void testEditHistogram() {
    IHistogram test = new SimpleHistogram(img1);
    int[][] arr = test.getHistogram();
    for (int i = 0; i < arr[0].length; i++) {
      arr[0][i] = 0;
      arr[1][i] = 1;
      arr[2][i] = 2;
    }
    assertArrayEquals(result1, test.getHistogram());
  }
}
