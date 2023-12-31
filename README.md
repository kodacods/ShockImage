## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

# Part 2

## Who did what
| Deliverables                         | Completed by  |
| ------------------------------------ | ------------- |
| **Filters**                          |               |
| Extended filters | Alex |
| Emboss Filter                       | Alex          |
| Sobel Filter| Alex |
| **ToolBar**| Beka |
| **Keyboard shortcuts** | Stella |
| **Mouse selection**| Stella & Meg |
| **Crop** | Stella & Meg |
| **Drawing functions** | Stella |
| **Macros** | Beka |
| **Extras** | |
| Stamp | Alex |
| Puzzle game | Meg |
| **Exception handling** | Alex |

---

## Testing

---

## User Guide
(Additional to Part 1)

**Additional filters**

*Sobel Filter*:

The sobel filter is an edge detection filter that has two options, vertical or horizontal. The image is then enhanced when there is large changes in colour/brightness, e.g. at edges.

*Emboss Filter*:

The emboss filter is an edge detection filter that has eight options, which are each direction and their diagonals. The image is then enhanced when there is large changes in colour/brightness, e.g. at edges.

**Macros**

*Start Recording Macros*

To start recording macros, either access via Macros menu or button on the tool bar. All operations will be recorded and stored until Stop Recording is selected.

*Stop Recording*

To stop recording macros, either access via Macros menu or button on the tool bar. The application will stop recording any further operations. 

*Save Macros to File*

To save macros to a file, select Save Macros to File in the menu. Enter the file name and the operations that have been stored will be saved as a .ops file to your selected path.

*Replay Macros*

To replay any previously save macros, access Replay Macros in the menu. Select the .ops file you wish to replay and all operations will be applied to the image.

**Extras**

*Stamp*

The stamp function (in the transform menu) is an added function which allows you to add an image on top of the current image, kind of like a sticker. When the stamp option is chosen, and the image you wish to stamp is selected, you can click anywhere on the image to stamp it. If you want to stop stamping the image, then you press escape.

*Puzzle Game*

The puzzle game function in the games menu is a simple puzzle game that includes 3 difficulties (2 * 2, 3 * 3, 4 * 4 in terms of how many cells the image is subdivided into), wherein the player swaps randomly shuffled cells of subdivided image to match the original image displayed to the left of the puzzle. It also records how many moves were made and how many were done upon finishing the puzzle.

-----

# Part 1

## Who did what

| Deliverables                         | Completed by  |
| ------------------------------------ | ------------- |
| **Filters**                          |               |
| Sharpen filter                       | Alex          |
| Gaussian blur filter                 | Alex          |
| Median filter                        | Stella & Alex |
| **Colour**                           |               |
| Contrast                             | Beka          |
| Brightness                           | Beka          |
| **Resize, rotate, flip**             |               |
| Image rotations                      | Meg           |
| Image flip                           | Meg           |
| Image resize                         | Meg           |
| **Multilingual Support**             | Beka          |
| **Exception Handling**               | Alex          |
| **Image export**                     | Meg           |
| **Other error avoidance/prevention** | Alex          |
| **Testing**                          | Stella & Alex |

---

## Testing


**Overall User Evaluation**
All file and filter actions produce an expected output at a satisfactory response rate.

**RGB Testing**
The RGBTest file contains 2 tests:

_MedianFilterTest_/_BrightnessTest_/_ContrastTest_/_SharpenFilterTest_

This test compares the RGB values of an manually read image to control image (initialised as a data field) after a filter has been applied. It compares the value at randomised x and y co-ordinates using the _randInt_ method.

_Rotate180Test_/_Rotate90Test_/_Anti90Test_/_FlipHorzTest_/_FlipVertTest_

This second test uses the control image to checks the RGB values of a specified co-ordinate of an untouched image to the correlating position on a copy image but with the action performed on it.

**Truncate Testing**
This is a branch test of the truncate method equation used in _Brightness_ and _Contrast_ to ensure the expected value is returned for each if-statement.

**Gaussian Testing**
This test checks if the first and second equations used in _GaussianBlur_ return the expected output.

**Kernel Testing**
The FilterKernelTesting file contains 2 tests:

_getDefaultGaussianBlurKernel_

This test will test if the default size (3x3) kernel generated by the GaussianBlur filter is the same as the 3x3 kernel from the lab book, with a variance of 0.001.

_getDefaultMeanFilterKernel_

This test will test if the default size (3x3) kernel generated by the MeanFilter filter is the same the 3x3 kernel from the lab book, with a variance of 0.001.

---

## User Guide

**Export Image**

To export and edited image, select 'Export' under the 'File' menu. You will then be asked to create a name for the file and which directory you would like it to be kept.

**Applying Filters**

To apply the filters, click on the type of filter you would like to apply (Blur / Edge detection), and you will be shown a screen where you can adjust the filters to your hearts content!

**Blur Filters**

- *Mean Blur*:
A Mean blur is a simple blur which takes the mean of each neighboring pixel (based on a radius) to blur the image evenly.

- *Gaussian Blur*:
A Gaussian blur is a more complex blur which makes a weighted average of neighbouring pixels (based on a radius) using the 2d Gaussian formula. This means that each pixel will be blurred using a kernel which is shaped like a bell curve, making nearby pixels have more influence than further away pixels.

- *Median Blur*:
A Median blur is a blur that gets the surrounding pixels, gets the median of each of the neighbouring pixels (based on a radius) individual RGB values. This results in a different kind of blur compared to the Mean and Gaussian filters.

**Sharpen Filter**

The Sharpen filter is almost like an inverse blur, it makes each pixel more different than its surrounding neighbours. This filter does not use a radius, but can be applied more than once for a stronger effect.

**Adjust contrast**

To change the contrast of the image, click the 'Contrast' button under the 'Colour' menu. You will be presented with a pop up menu that allows you to select the contrast between -90% and 90% with increments of 10%. Use the arrows to make your selection. Once you have selected the amount you would like to change the contrast press 'Ok' and the image will be transformed to the new parameters. You can increase and decrease contrast on already edited images. Sometimes when an image's contrast is edited, it's pixel colour can slightly change due to keeping it within range of 0 to 255, if wanting to revert the image back to its original contrast it is best to use undo once the contrast has been adjusted. If other edits have been made to the image after contrast has been applied, you must undo all edits until you reach the contrast undo.

**Adjust brightness**

To change the brightness of the image, click the 'Brightness' button under the 'Colour' menu. You will be presented with a pop up menu that allows you to select the brightness between -90% and 90% with increments of 10%. Use the arrows to make your selection. Once you have selected the amount you would like to change the brightness press 'Ok' and the image will be transformed to the new parameters. You can increase and decrease brightness on already edited images. Sometimes when an image's brightness is edited, it's pixel colour can slightly change due to keeping it within range of 0 to 255, if wanting to revert the image back to its original brightness it is best to use undo once the brightness has been adjusted. If other edits have been made to the image after brightness has been applied, you must undo all edits until you reach the brightness undo.

**Image resize**
To change the size of the image, click on the 'Resize' button under the 'Transform' menu. You will be presented with a pop up menu that allows you to select the amount you would like to change the size by, between 0 and 10 with increments of 0.1 and starting at 1, where 1 is the original image size. Each 0.1 increment is representative of 10%.

**Image rotate**
To rotate the image 90 degrees clockwise, select 'Rotate Clockwise 90' under the 'Transform' menu.

To rotate the image 90 degrees anticlockwise, select 'Rotate Anticlockwise 90' under the 'Transform' menu.

To rotate the image 180 degrees, select 'Rotate 180' under the 'Transform' menu

**Image flip**

To flip the image vertically, select 'Flip Vertical' under the 'Transform' menu.

To flip the image Horizontal, select 'Flip Horizontal' under the 'Transform' menu.

**Change the language of ANDIE**

To change the language of the interface, select 'Change language / Sprache ändern' under the 'Language / Sprache' menu. You will be presented with a pop up menu that will allow you to select either 'English' or 'Deutsch'. Once you have selected your preferred language press 'Ok'. The pop up menu will close. You will then need to exit the application and re-open it. Once it is re-opened all words will be changed to the language that was selected. You can change language at any time repeating the steps above.

**Selection**

Selection is automatic, click and drag anywhere on the screen to make a rectangle based selection.

**Crop**

Select an area to crop and apply using the 'Crop' button.

**Drawing**

To draw on the image, use the 'Drawing' menu and select desired shape, colour, fill. Only one shape is drawn for each selection.

**KeyBoard Shortcuts**

To access keyboard shortcuts, apply the controls shown next to the desired application e.g. to save use ctrl + s.

---

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
