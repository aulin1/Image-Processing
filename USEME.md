# How to run the program

## Running the GUI version using jar file
Navigate to the res folder and type **"java -jar Image-Processing.jar"** with no trailing arguments. Further arguments will either start the program in command line version or is not supported by the program.

## Running through the command line
If you wish to run the program, then navigate to the res folder and type **"java -jar Image-Processing.jar"** followed by any arguments as listed below. If you do not have additional arguments, the program will open a GUI to interact with.

### Running with a script file.
To run with a script file, use the command **"-file"** followed by the path for the script file. You must provide an existing TXT file in form of a file path through the command line. An example of a valid input is:
> -file exampleScript.txt

Only TXT file is supported in this method. An exception will be throw if otherwise. The jar file is located in the res folder, so pathing must work from there.

**Ensure that the final line in the script provided is *"q"* to exit the program**. If not, the 
program will throw an exception as the program ran out of input before quitting.

### Running a text based version.
To run a text based version of the image processing program, use the command **"-text"**. Then, in the console, type the commands as listed below. The program will not quit until you type **"q"**.

### Choosing Image Processing Program Version
Through command line, provide the string **"-old"** to use the older version of the program. 

Arguments besides the ones mentioned above will throw an exception when running the program.

The expected command has to be the <strong>first string in the command line</strong>. The program 
would not run if provided otherwise.

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

### How to use the Image Processing Program GUI
#### Starting and general usage of the program
- If you start the program GUI version in accordance to the appropriate step above, you should see this window pop up. This is the starting screen of the program:
<img width="802" alt="image" src="https://user-images.githubusercontent.com/116769130/203163931-f092b0cc-d753-4751-af58-2080b13246da.png">
- To begin using the program, please upload an image of your choice from your machine by clicking on the *File* menu and the *Load file* menu item from the drop down menu:
<img width="306" alt="image" src="https://user-images.githubusercontent.com/116769130/203164121-8c14368c-a1a5-497f-8f35-3338aade2861.png">
- After uploading an image, your program would look similar to below. For this example, the *Koala.ppm* file was loaded from the *res* folder:
<img width="802" alt="image" src="https://user-images.githubusercontent.com/116769130/203164460-09157e27-0dbc-4965-8dec-7ebcfe272aa9.png">
- To access the functionalities of the image processing program, navigate to the *Image Processing* tab on the menu bar. The *Image Processing* tab should look as below:
<img width="167" alt="image" src="https://user-images.githubusercontent.com/116769130/203164938-8c7d31d2-1a05-4f86-9033-e24131231e20.png">
- These are the functionalities of image processing that is supported by the program:
    - *Color transform* -> *red/blue/green/sepia*: Create a greyscale of the image based on the chosen values
    - *Flip* -> *Horizontal flip/Vertical flip*: Flip the orientation of the currently processing image to the designatede orientation
    - *Filter* -> *Intensity/Value/Luma/Brightness*: Filter the chosen value of the currently processing image
    - *Blur*: Applies Gaussian blur on the current processing image
    - *Sharpen*: Sharpen the currentt processing image

- If you would like to save the current processing image to your machine, navigate to the *File* menu tab and click on the *Save file* tab from the drop down menu. Then, a file saving panel should pop up.
    - **It is important that you add the file extension of your desired image format of the image to be saved in the file name while saving as the program would not be able to save it correctly otherwise*
- If you would like to quit the program, simply close the window which the program is currently running on and the program will automatically close itself.

- On the right side of the program is the logistic panel. Here, you can see the histogram of the key color values of the image which is being processed (top right) and the log of the commands executed in the program (bottom right).
<img width="270" alt="image" src="https://user-images.githubusercontent.com/116769130/203168507-6cdab7ca-4cf1-48c0-82cb-fa55c12b8b3c.png">
- If the log get clogged up and you would like to clear it, click the *Clear log* button at the bottom of the logistic panel.

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
