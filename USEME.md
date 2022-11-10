# How to run the program

### Running a script file through the command line
Provide an existing TXT file in form of a file path through the command line to run the program 
with the arguments provided in the document. An example of a valid input is:
> exampleScript.txt

Only TXT file is supported in this method. An exception will be throw if otherwise. The jar file is located in the res folder, so pathing must work from there.

**Ensure that the final line in the script provided is *"q"* to exit the program**. If not, the 
program will throw an exception as the program ran out of input before quitting.
### Choosing Image Processing Program Version
Through command line, provide the string **"old"** to use the older version of the program. Arguments besides the ones mentioned above will throw an exception 
when running the program.

The expected command has to be the <strong>first string in the command line</strong>. The program 
would not run if provided otherwise.

If there is no argument provided, the default new version of the controller will run instead. If the argument provided is not an accepted command, the new version of the controller will run instead.

### Condition of command usage
#### Using the command line prior to running the program
<li>The order of the provided arguments matter, when feeding the arguments through the command 
line, make sure the required command (e.g: "old", "new" or res/exampleScript.txt) are provided 
as the first argument in the line</li>
<li>Any arguments provided after the first position is not read and unhandled</li>

#### Using open script when running the program
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
<li><strong>save image-path image-name</strong>: Save a <em>supported format</em> of image of the provided name to a PPM 
file at the provided file path.
</li>
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
<li><strong>sepia image-name dest-image-name</strong>: 
create a sepia version of the image with the provided name and refer to it as the destination 
image name.
</li>

### Examples of accepted commands (supported by the updated version of the program)
1. Load Pixel.png from the res/ folder and call it *pixel*
> load res/Pixel.png pixel
2. Change the reference name from *pixel* to *pixelOG*
> change-name pixel pixelOG
3. Create a greyscale with the red component of *pixelOG* and refer to the new image as *pixelRed*
> red-component pixelOG pixelRed
4. Create a greyscale with the green component of *pixelOG* and refer to the new image as 
   *pixelGreen*
> green-component pixelOG pixelGreen
5. Create a greyscale with the blue component of *pixelOG* and refer to the new image as *pixelBlue*
> blue-component pixelOG pixelBlue
6. Create a greyscale with the maximum value of the three components of *pixelRed* and refer to 
   the new image as *pixelRedValue*
> value pixelRed pixelRedValue
7. Create a greyscale with the weighted sum 0.2126r + 0.7152g + 0.0722b from *pixelBlue* and 
   refer to it as *pixelBlueLuma*
> luma pixelBlue pixelBlueLuma
8. Create a greyscale image with the average of the three components for each pixel from 
   *pixelGreen* and refer to it as *pixelGreenIntensity*
> intensity pixelGreen pixelGreenIntensity
9. Flip *pixelOG* horizontally and refer to it as *pixelHorizontal*
> horizontal-flip pixelOG pixelHorizontal
10. Flip *pixelOG* vertically and refer to it as *pixelVertical*
> vertical-flip pixelOG pixelVertical
11. Brighten *pixelRedValue* by 10 and refer to it as *pixelRedValueBrightened*
> brighten pixelRedValue pixelRedValueBrightened 10
12. Darken *pixelOG* by 20 and refer to it as *pixelDarken*
> brighten pixelOG pixelDarken -20
13. Brighten *pixelOG* by 0 and refer to it as *pixelNoChange*
> brighten pixelOG pixelNoChange 0
14. Apply gaussian blur on *pixelBlueLuma* and refer to it as *pixelBlueLumaBlur*
> gaussian-blur pixelBlueLuma pixelBlueLumaBlur
15. Sharpen *pixelGreen* and refer to it as *pixelGreenSharpen*
> sharpen pixelGreen pixelGreenSharpen
16. Create a greyscale of *pixelOG* and refer to it as *pixelGreyscale*
> greyscale pixelOG pixelGreyscale
17. Create a sepia version of *pixelOG* and refer to it as *pixelSepia*
> sepia pixelOG pixelSepia
17. Save *pixelGreenIntensity* to the res/ folder as a BMP file and refer to it as *PGI.bmp*
> save res/PGI.bmp pixelGreenIntensity
18. Quit the program
> q

### Examples of accepted commands (supported by the old version of the program)
1. Load Pixel.png from the res/ folder and call it *pixel*
> load res/Pixel.ppm pixel
2. Change the reference name from *pixel* to *pixelOG*
> change-name pixel pixelOG
3. Create a greyscale with the red component of *pixelOG* and refer to the new image as *pixelRed*
> red-component pixelOG pixelRed
4. Create a greyscale with the green component of *pixelOG* and refer to the new image as
   *pixelGreen*
> green-component pixelOG pixelGreen
5. Create a greyscale with the blue component of *pixelOG* and refer to the new image as *pixelBlue*
> blue-component pixelOG pixelBlue
6. Create a greyscale with the maximum value of the three components of *pixelRed* and refer to
   the new image as *pixelRedValue*
> value pixelRed pixelRedValue
7. Create a greyscale with the weighted sum 0.2126r + 0.7152g + 0.0722b from *pixelBlue* and
   refer to it as *pixelBlueLuma*
> luma pixelBlue pixelBlueLuma
8. Create a greyscale image with the average of the three components for each pixel from
   *pixelGreen* and refer to it as *pixelGreenIntensity*
> intensity pixelGreen pixelGreenIntensity
9. Flip *pixelOG* horizontally and refer to it as *pixelHorizontal*
> horizontal-flip pixelOG pixelHorizontal
10. Flip *pixelOG* vertically and refer to it as *pixelVertical*
> vertical-flip pixelOG pixelVertical
11. Brighten *pixelRedValue* by 10 and refer to it as *pixelRedValueBrightened*
> brighten pixelRedValue pixelRedValueBrightened 10
12. Darken *pixelOG* by 20 and refer to it as *pixelDarken*
> brighten pixelOG pixelDarken -20
13. Brighten *pixelOG* by 0 and refer to it as *pixelNoChange*
> brighten pixelOG pixelNoChange 0
14. Save *pixelGreenIntensity* to the res/ folder as a PPM file and refer to it as *PGI.bmp*
> save res/PGI.ppm pixelGreenIntensity
15. Quit the program
> q
