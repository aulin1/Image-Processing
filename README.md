# Image-Processing

### Overview of the code

<ol>
<li>Interface <strong>ImageProcessingCommand</strong> : a representation of a command in the 
Controller; contains method execute() which all commands which 
implements this interface includes.
  <ol>
<li><strong>FilterCommand</strong> : an abstract class which represents a command that runs a filter over an image.
<li><strong>ColorChangeCommand</strong> : an abstract class which represents a command that changes the color of an image.
  <li><strong>BlurSharpenCommand</strong> : an abstract class which represents a command that changes the focus of an image.
implements this interface includes.
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
<li><strong>GaussianBlurCommand</strong>: a function object which puts a Gaussian Blur on an image.</li>
<li><strong>ImageSharpenCommand</strong>: a function object which sharpens an image.</li>
  <li><strong>SepiaToneCommand</strong>: a function object which creates a Sepia Tone filter on an image.</li>
    </ol>
  <li>Interface <strong>ModelCommand</strong> : a representation of a command in the 
Controller; contains method execute() which all commands which 
implements this interface includes.
  <ol>
  <li><strong>LoadCommand</strong>: a function object which contains the function to load an image.</li>
  <li><strong>SaveCommand</strong>: a function object which contains the function to save an image.</li>
    <li><strong>ChangeNameCommand</strong>: a function object which changes the name under which an image is saved.</li>

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
  <li><strong>UpdatedIPController</strong>: A controller which adds the new methods and can read in other image types
into an Image 
Processing Model.
</li></ol></li>

<li>Interface <strong>ImageClass</strong>:</li> a representation of an image and contains get methods for what is needed for an image.
<ol>
  <li><strong>ImageClassImpl</strong>: A class which implements the image interface and represents an image.</li>
</ol>
<li>Interface <strong>ImageProcessingModel</strong>: a representation of what an image processing 
model which contains all the methods that all image processing models should contain. It greatly 
handles the loading and saving of images in the program.</li>
<ol>
<li><strong>PPMProcessingModel</strong>: A model which implements the model interface and can load and save a PPM image.</li>
<li><strong>MockModel</strong>: A model which takes in a log. It is uses to test controller's input.
</li></ol>

<li><strong>Image Processing</strong>: Runs the Image Processing Program on the user's console.</li>
<li>Tests:
<ol><li><strong>MockModelTest</strong>: Tests for ImageProcessingControllerImpl 
class.</li>
<li><strong>ImageClassTest</strong>: Tests for ImageProcessingModel class.</li>
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

The model was revamped, with most of the filtering functionality moved to the command pattern, thus allowing for easier creation of future filters, and renamed to ImageClass to better show what it represents. The class should now be complete. 

The commands have been changed, with the functionality the command represents now included in the execute function rather than calling the method from the image. This is for abstraction and better mutability as now new commands can be added without changing the image interface and class. Abstract classes were created to avoid reptitive code, including FilterCommand, which gives the common functionality of commands that use a filter by changing each pixel using some calculation such that execute is not repeated, and ColorTransformCommand and BlurSharpenCommand which take the common functionality of their respective filter types for less code repitition.

The commands have been split into two types: ImageProcessingCommands which process an image and ModelCommands which are commands that directly change the model. This is for better code seperation. 

The commands have been moved into the respective packages that they belong into, rather than a command package. Additionally, model has been renamed into image, and view has been renamed into model to better represent what they actually are.

New helper functions were added to the old implementation of the controller in order for better readability.

Moved sections *How to use the Program*, *Accepted script of commands* and added *Examples of 
accepted commands* to USEME.md for usage clarity.

saveImage() in PPMProcessingView class now has an additional check for the indicated export format in the provided filepath to prevent the user from inputting incorrect file path (e.g: res/KoalaRed.jpg) as this model only supports save and load of PPM files

