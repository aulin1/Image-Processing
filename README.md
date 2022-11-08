# Image-Processing

### Overview of the code

<ol>
<li>Interface <strong>ImageProcessingCommand</strong> : a representation of a command in the 
Controller; contains method execute() which all commands which 
implements this interface includes.
<li><strong>FilterCommand</strong> : an abstract class which represents a command that runs a filter over an image.
<li><strong>ColorChangeCommand</strong> : an abstract class which represents a command that changes the color of an image.
  <li><strong>BlurSharpenCommand</strong> : an abstract class which represents a command that changes the focus of an image.
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
<li><strong>BrightnessCommand</strong>: a function object changes the brightness of an object.</li>
<li><strong>ChangeNameCommand</strong>: a function object which changes the name under which an image is saved.</li>
<li><strong>GaussianBlurCommand</strong>: a function object which puts a Gaussian Blur on an image.</li>
<li><strong>ImageSharpenCommand</strong>: a function object which sharpens an image.</li>
  <li><strong>SepiaToneCommand</strong>: a function object which creates a Sepia Tone filter on an image.</li>
  <li><strong>LoadPPMCommand</strong>: a function object which contains the function to load a PPM image.</li>
  <li><strong>SavePPMCommand</strong>: a function object which contains the function to save a PPM image.</li>
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
  <li><strong>ImageProcessingModelImpl</strong>: A model which implements the model interface and represents an image.</li>
</ol>
<li>Interface <strong>ImageProcessingView</strong>: a representation of what an image processing 
view which contains all the methods that all image processing views should contain. It greatly 
handles the loading and saving of images in the program.</li>
<ol>
<li><strong>PPMProcessingView</strong>: A view which implements the view interface and can load and save a PPM image.</li>
<li><strong>MockView</strong>: A view which takes in a log. It is uses to test controller's input.
</li></ol>

<li><strong>Image Processing</strong>: Runs the Image Processing Program on the user's console.</li>
<li>Tests:
<ol><li><strong>MockViewTest</strong>: Tests for ImageProcessingControllerImpl 
class.</li>
<li><strong>ImageProcessingModelTest</strong>: Tests for ImageProcessingModel class.</li>
<li><strong>ImageUtilTest</strong>: Tests for ImageUtil class.</li>
<li><strong>ImageProcessingCommandTest</strong>: Tests for the commands.</li>
<li><strong>ImageProcessingIntegrationTest</strong>: Integration Tests.</li>
</ol></li>
</ol>

### How to run the program
Run the main function, and type a command from the accepted script of commands into the console.

### Image citation

The image dot.ppm is the work of Audrey Lin, and she has authorized its usage in this
assignment. It is an original image created in the PPM image format.

The image Pixel.ppm and PixelP6.ppm are the work of Trang Do, and she has authorized their usage
in this assignment. They are original images converted to PPM image format.

### Design Changes

The model was revamped, with most of the filtering functionality moved to the command pattern, thus allowing for easier creation of future filters. The model should now be complete. 

Moved sections *How to use the Program*, *Accepted script of commands* and added *Examples of 
accepted commands* to USEME.md for usage clarity.
