# spotify-uitest

---

As its name, this project contains a UI test script to test the Spotify app (Android) that uses image-based elements in some of its
steps.
## Test Case

---
For now, there is only one test case: _Checking album or single a particular artist with a correct artwork_

## Running the Test Case

---
Before running the test case, there are two things that have to be reviewed, the configuration, and the image asset.

### Configuration

The configuration of the code is on `config.properties` file that located in `src/main/java/config` folder. It contains 5
variables.
- `appiumUrl` : The url of the appium server.
- `deviceName` : The name of the device that will be used.
- `udid` : The udid of the device that will be used.
- `imageMatcherElementCapture` : When the value is `true`, it will capture, mark the position, and also show the
  match-score of an image-based element. The default is `false`.
- `thresholdValue` : The threshold value of match-score to find an image-based element (0.0 ~ 1.0).

### Image Asset
As mentioned before, there are some steps that use image-based elements. The image asset is located in `src/main/resources
/imageElements` that contains 3 different folders grouped based on the page location.

All the image-elements / regular elements are mapped into the final variable, it's located in `src/main/java/locators` that
grouped based on the page location. for image-based elements, the value is the image location.

_All the image assets for image-elements are based on the image from the Samsung A51 device. It might still be used for the
others 1080p devices but the match-score might be lower. In order to run it on another device that has a different screen
resolution, the image asset has to be replaced._

There are 3 steps that used image-based elements.

```gherkin
Given I am on Home Page
```
This step is asserting the presence of `home_icon-active.jpg`

```gherkin
When I am on Search Page
```
This step is asserting the presence of `search_icon-active.jpg`

```gherkin
Then I can see the artwork same as "<artworkFilePath>"
```
This step is asserting the presence of whatever image that mapped into `artWorkFilePath` in the scenario outline. The
author places the example case into `albumSingle` folder.

### Running It
There are 2 ways to run it:
- Executing `src/test/java/CucumberRunner.java`
- Executing via terminal `./gradlew clean test -Dcucumber.options="--tags @Positive"`

## Action Functions

---
All the action functions that are used, are placed on `ActionUtil.java` that located in `src/main/java/utils`. The explanation of each
function has been attached.

## Note

---
- If there's an inquiry or anything related to this project, don't hesitate to contact me.