# Image-Processing

### Overview of the code

<ol>
<li>Interface <strong>ImageProcessingCommand</strong> : a representation of a command in the 
Controller; contains method execute() which all commands which 
implements this interface includes.
<ol>
<li><strong>RedCompCommand/GreenCompCommand/BlueCompCommand</strong>: a function object which 
create a 
greyscale 
image with its red/green/blue component. </li>
<li><strong>ValueCommand</strong>: a function object which create a greyscale image 
with the maximum value of three components for each pixel.</li>
<li><strong>LumaCommand</strong>: a function object which create a greyscale image 
with the weighted sum 0.2126r + 0.7152g + 0.0722b.</li>
<li><strong>IntensityCommand</strong>: a function object which create a greyscale image 
with the average of the three components for each pixel.</li>
<li><strong>HorizontalFlipCommand/VerticalFlipCommand</strong>: a function object which flips an 
image 
horizontally/vertically to create a new image.</li>
</ol>
</li>

<li>Interface <strong>ImageProcessingController</strong>: a representation of an image processing 
controller 
which contains the method start() which all controllers which implements it should contains.
<ol><li><strong>ImageProcessingControllerImpl</strong>: An implementation of the controller 
interface 
which supports all commands included in the script command.</li>
<li><strong>ImageUtil</strong>: A Util which has the method readPPM() which helps read a PPM file 
into an Image 
Processing Model.
</li></ol></li>

<li>Interface <strong>ImageProcessingModel</strong>:</li> a representation of what 
  an image processing model which contains 
the methods that all image processing models should contain.
<ol>
  <li><strong>PPMProcessingModel</strong>: A model which implements the model interface and represents a PPM image.</li>
</ol>
<li>Interface <strong>ImageProcessingView</strong>: a representation of what an image processing view which contains all the methods that all image processing views should contain.</li>
<ol>
<li><strong>PPMProcessingView</strong>: A view which implements the view interface and can load and save a PPM image.</li>
</ol>

<li><strong>Image Processing</strong>: Runs the Image Processing Program on the user's console.</li>
<li>Tests:
<ol><li><strong>ImageProcessingControllerImplTest</strong>: Tests for ImageProcessingControllerImpl 
class.</li>
<li><strong>ImageProcessingModelTest</strong>: Tests for ImageProcessingModel class.</li>
<li><strong>ImageUtilTest</strong>: Tests for ImageUtil class.</li>
<li><strong>ImageProcessingIntegrationTest</strong>: Integration Tests.</li>
</ol></li>
</ol>

### Accepted script of commands

### Image citation

The image img.ppm is the work of Audrey Lin, and she has authorized its usage in this
assignment. It is an original image converted to a PPM image format.

The image Pixel.ppm and PixelP6.ppm are the work of Trang Do, and she has authorized their usage
in this assignment. They are original images converted to PPM image format.
