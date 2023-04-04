## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

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

---

## User Guide

**Transform image to black and white**

Select 'Greyscale' under the 'Colour' menu, the image will be transformed into black and white. To revert the image back to colour, the undo button must be selected after the image has been transformed to Greyscale. If other edits have been made to the image after Greyscale has been applied, you must undo all edits until you reach the Greyscale undo.

**Adjust contrast**

To change the contrast of the image, click the 'Contrast' button under the 'Colour' menu. You will be presented with a pop up menu that allows you to change the contrast between -100% and 100%. Use the arrows to make your selection. Once you have selected the amount you would like to change the contrast press 'Ok' and the image will be tranformed to the new paramters. You can increase and decrease contrast on already edited images. Sometimes when an image's contrast is edited, it's pixel colour can slightly change due to keeping it within range of 0 to 255, if wanting to revert the image back to it's original contrast it is best to use undo once the contrast has been adjusted. If other edits have been made to the image after contrast has been applied, you must undo all edits until you reach the contrast undo.

**Adjust brightness**

To change the brightness of the image, click the 'Brightness' button under the 'Colour' menu. You will be presented with a pop up menu that allows you to change the brightness between -100% and 100%. Use the arrows to make your selection. Once you have selected the amount you would like to change the brightness press 'Ok' and the image will be tranformed to the new paramters. You can increase and decrease brightness on already edited images. Sometimes when an image's brightness is edited, it's pixel colour can slightly change due to keeping it within range of 0 to 255, if wanting to revert the image back to it's original brightness it is best to use undo once the brightness has been adjusted. If other edits have been made to the image after brightness has been applied, you must undo all edits until you reach the brightness undo.

**Change the langague of ANDIE**

To change the language of the interface, select 'Change language / Sprache ändern' under the 'Language / Sprache' menu. You will be presented with a pop up menu that will allow you to select either 'English' or 'Deutsch'. Once you have selected your preferred language press 'Ok'. The pop up menu will close. You will then need to exit the application and re-open it. Once it is re-opened all words will be changed to the language that was selected. You can change language at any time repeating the steps above.

**Applying Filters**

To apply any filters, open the "filter" menu at the top of the screen. Click the filter you wish to apply, and enter a radius from 1-10 if prompted to (For Gaussian, Median and Mean filters). The radius will affect how strongly the filter is applied.

**Mean Filter**
A Mean filter is a simple blur which takes the mean of each neighboring pixel (based on a radius) to blur the image evenly.

**Gaussian Filter**
A Gaussian filter is a more complex blur which makes a weighted average of neighboring pixels (based on a radius) using the 2d Gaussian formula. This means that each pixel will be blurred using a kernel which is shaped like a bell curve, making nearby pixels have more influence than further away pixels.

**Median Filter**
A Median filter is a blur that gets the surrounding pixels, gets the median of each of the neighboring pixels (based on a radius) individual RGB values. This results in a different kind of blur compared to the Mean and Gaussian filters.

**Sharpen Filter**
The Sharpen filter is almost like an inverse blur, it makes each pixel more different than its surrounding neighbors. This filter does not use a radius, but can be applied more than once for a stronger effect.

---

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
