# How to run the program

### Choosing Image Processing Program Version
Through command line, provide the string **"new"** to use the new version and **"old"** to use the 
older version of the program. Arguments besides the ones mentioned above will throw an exception 
when running the program.

The expected command has to be the <strong>first string in the command line</strong>. The program 
would not run if provided otherwise.

### Condition of command usage
<li>A valid image path has to be used while loading and saving the image to and from the 
program in order for the process to be successful
    <ul>Example: res/Koala.ppm to refer to the Koala file of PPM format in the res/ folder</ul></li>
<li>An image has to be loaded to the program first before any image processing methods can be 
applied to it. An error message will appear if otherwise</li>
<li>The newer version of the program supports <strong>most conventional image formats</strong></li>


### Accepted script of commands (old and new version)
<li><strong>load image-path image-name</strong>: Load a PPM image from the provided image path and 
store it in the program under the provided name.
</li>
<li><strong>save image-path image-name</strong>: Save a PPM image of the provided name to a PPM 
file at the provided file path.
</li>
<li><strong>change-name old-name new-name</strong>: Change the name of a currently stored image 
in the program to the new provided name.
</li>
<li><strong>red-component/green-component/blue-component image-name dest-image-name</strong>: 
Create 
a greyscale image with the red/green/blue component and store it in the program with the given name.
</li>
<li><strong>value image-name dest-image-name</strong>: 
Create a greyscale image with the maximum value of three components for each pixel.
</li>
<li><strong>luma image-name dest-image-name</strong>: 
create a greyscale image with the weighted sum 0.2126r + 0.7152g + 0.0722b.
</li>
<li><strong>intensity image-name dest-image-name</strong>: 
Create a greyscale image with the average of the three components for each pixel.
</li>
<li><strong>horizontal-flip/vertical-flip image-name dest-image-name</strong>: 
Flip an image horizontally/vertically to create an image and store it with the provided 
destination image name. 
</li>
<li><strong>brighten image-name dest-image-name increment</strong>: 
Brighten an image by a given increment to create a new image and stores in the program as the 
provided destination name.
<ul><li>Positive increment brightens the image </li>
<li>Negative increment darkens the image</li>
<li>An increment of 0 returns the original image and store it with the provided destination 
name</li></ul>
</li>

### New functionalities supported by the updated controller
<li><strong>load image-path image-name</strong>: Load a <em>supported format</em> of image from 
the 
provided image path and 
store it in the program under the provided name.
</li>
<li><strong>gaussian-blur image-name dest-image-name</strong>: 
create a blurred image from the image of the provided name and store it with the provided 
destination name.
</li>
<li><strong>sharpen image-name dest-image-name</strong>: 
create a sharpened image from the image of the provided name and store it with the provided 
destination name.
</li>
<li><strong>greyscale image-name dest-image-name</strong>: 
create a greyscale image with the weighted sum 0.2126r + 0.7152g + 0.0722b.
</li>

### Examples of accepted commands
