package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

import controller.ImageUtil;
import image.ImageClass;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * This class represents a view for the program which handles the saving and loading of images to
 * the program. It supports all functionality of the previous view version but also able to save,
 * load and store images of all supported format besides PPM, such as JPEG, PNG,....
 */
public class UpdatedProcessingModel extends PPMProcessingModel {

  /**
   * Basic constructor for UpdatedProcessingView.
   * */
  public UpdatedProcessingModel(){
    super();
  }

  /**
   * Another constructor for the updated view.
   * */
  public UpdatedProcessingModel(Map<String, ImageClass> map){
    super(map);
  }

  @Override
  public void saveImage(String imagePath, String imageName)
          throws IllegalArgumentException, IllegalStateException {
    if (imagePath == null || imageName == null) {
      throw new IllegalArgumentException("The fields cannot be null.");
    }

    String[] imagePathParsed = imagePath.split("\\.");
    String formatName = imagePathParsed[imagePathParsed.length - 1];
    if (formatName.equals("ppm")) {
      super.saveImage(imagePath, imageName);
    }

    // buffered image then ImageIO write
    ImageClass model = this.getImage(imageName);
    int[][][] imageBoard = model.getImage();
    BufferedImage buffImg = new BufferedImage(model.getWidth(), model.getHeight(), TYPE_INT_RGB);

    // transfer rgb by pixels from model to the buffered img
    for (int row = 0; row < model.getHeight(); row++) {
      for (int col = 0; col < model.getWidth(); col++) {
        int r = imageBoard[row][col][0]; // print red value
        int rg = (r << 8) + imageBoard[row][col][1]; // print green value
        int rgb = (rg << 8) + imageBoard[row][col][2]; // print blue value
        buffImg.setRGB(col, row, rgb); //pos-x, pos-y, 8 bit rep of rgb
      }
    }

    try {
      ImageOutputStream imgOutStream = new FileImageOutputStream(new File(imagePath));
      ImageIO.write(buffImg, formatName, imgOutStream);
    } catch (IOException e) {
      throw new IllegalStateException("Unable to save file to destination. ");
    }
  }

  @Override
  public ImageClass loadImage(String imagePath, String imageName)
          throws IllegalArgumentException {
    ImageClass model = ImageUtil.readIMG(imagePath);
    this.storeImage(imageName, model);
    return model;
  }
}
