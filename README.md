# Mobile Automation Framework for Trust Wallet (Cucumber, TestNG, Java, Maven)

This document outlines the setup and execution of an automated mobile testing framework designed for Trust Wallet applications. The framework leverages Cucumber for Behavior-Driven Development (BDD), TestNG as the test runner, Java as the programming language, and Maven for dependency management.

## Project Overview

This automation framework targets the "Create Wallet" functionality of the Trust Wallet mobile application. It includes a Gherkin-based test scenario to ensure the successful creation of a new wallet. The framework is structured to promote readability, maintainability, and collaboration within a testing team.

**Key Technologies:**

* **Cucumber:** Enables writing test cases in a human-readable format using the Gherkin syntax.
* **TestNG:** A powerful and flexible Java testing framework used for test execution and reporting.
* **Java:** The primary programming language for implementing the automation logic.
* **Maven:** A build automation tool used for managing project dependencies and building the project.
* **Appium:** An open-source tool for automating native, web, and hybrid mobile applications.

## Test Scenario

The following test case is implemented within this framework:

```gherkin
Feature: Create Wallet Functionality

  Background:
    Given Trust Wallet application is opened

  Scenario: Create Wallet Successfully

    When User clicks on "Create New Wallet" button
    And "Create passcode" page is displayed
    And User enters the passcode
    And "Confirm passcode" page is displayed
    And User confirms the passcode
    And Notifications pop-up appears and user taps the skip button
    And User skips crypto deposit screen
    And User navigates to Wallets page
    Then User verifies the wallet name on the Wallet page


## Framework Structure

├── src
│   └── test
│       ├── java
│       │   └── com
│       │       └── example
│       │           ├── hooks
│       │           │   └── Hooks.java              # Cucumber hooks for setup and teardown
│       │           ├── pages
│       │           │   ├── ConfirmPasscodePage.java # Page object for Confirm Passcode screen
│       │           │   ├── CreatePasscodePage.java  # Page object for Create Passcode screen
│       │           │   ├── HomePage.java            # Page object for the Home screen
│       │           │   ├── SkipNotificationPopUp.java # Page object for Skip Notification Pop-up
│       │           │   ├── WalletPage.java          # Page object for the Wallet screen
│       │           │   └── WelcomePage.java         # Page object for the Welcome screen
│       │           ├── runners
│       │           │   └── TestRunner.java          # TestNG runner for Cucumber features
│       │           ├── stepdefinitions
│       │           │   └── CreateWalletSteps.java   # Step definitions for Create Wallet feature
│       │           └── utils
│       │               └── DriverUtils.java         # Utility class for driver management
│       └── resources
│           └── apps
│               └── trustwallet.apk            # Application under test (APK file)
├── pom.xml                                     # Maven dependencies
├── README.md                                   # Project documentation
└── testng.xml                                  # TestNG configuration


## APK Handling

**Important**: The Trust Wallet APK exceeds GitHub's file size limit (100MB). To avoid issues when pushing to GitHub:

- The APK files are excluded from version control (added to `.gitignore`)
- You need to download the APK separately (see Setup Instructions below)

## Detailed Setup Guide

### Prerequisites

1. **Java Development Kit (JDK) 11**
   - Download and install from [Oracle JDK](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html) or use OpenJDK
   - Set JAVA_HOME environment variable:
     - Windows: Open System Properties > Advanced > Environment Variables > New System Variable
       - Variable name: `JAVA_HOME`
       - Variable value: `C:\Program Files\Java\jdk-11` (or your JDK installation path)
     - Add `%JAVA_HOME%\bin` to the Path variable

2. **Maven**
   - Download from [Maven website](https://maven.apache.org/download.cgi)
   - Extract to a directory (e.g., `C:\Program Files\Maven`)
   - Set environment variables:
     - Variable name: `M2_HOME`
     - Variable value: `C:\Program Files\Maven` (or your Maven installation path)
     - Add `%M2_HOME%\bin` to the Path variable

3. **Android Studio**
   - Download and install from [Android Developer website](https://developer.android.com/studio)
   - During installation, ensure you install:
     - Android SDK
     - Android SDK Platform
     - Android Virtual Device (AVD)
   - After installation, open Android Studio > Tools > SDK Manager and install:
     - Latest Android SDK Build-Tools
     - Android SDK Command-line Tools
     - Android SDK Platform-Tools
     - Android Emulator

4. **Configure Android Environment Variables**
   - Set ANDROID_HOME environment variable:
     - Variable name: `ANDROID_HOME`
     - Variable value: `C:\Users\<your-username>\AppData\Local\Android\Sdk` (or your SDK installation path)
   - Add these directories to the Path variable:
     - `%ANDROID_HOME%\tools`
     - `%ANDROID_HOME%\tools\bin`
     - `%ANDROID_HOME%\platform-tools`
     - `%ANDROID_HOME%\emulator`

5. **Create Android Virtual Device (Emulator)**
   - Open Android Studio > Device Manager
   - Click "Create Virtual Device"
   - Select a phone (e.g., Pixel 8)
   - Download a system image (e.g., Android 15)
   - Name your device and finish the setup

6. **Appium Setup**
   - Install Node.js and npm from [Node.js website](https://nodejs.org/)
   - Install Appium Server:
     ```
     npm install -g appium
     ```
   - Verify installation:
     ```
     appium --version
     ```
   - Install Appium Desktop from [GitHub releases](https://github.com/appium/appium-desktop/releases)
   - Install Appium Inspector from [GitHub releases](https://github.com/appium/appium-inspector/releases)
   - Install UIAutomator2 Driver:
     ```
     npm install -g appium-uiautomator2-driver
     ```

### Project Setup

1. **Download the Trust Wallet APK**
   ```bash
   # Create the apk directory if it doesn't exist
   mkdir -p apk
   
   # Download the Trust Wallet APK from the official website
   # Visit: https://trustwallet.com/
   ```
   Once downloaded, place the APK in the `apk/` directory with the filename `trust-wallet-latest.apk`.

2. **Install Dependencies**
   ```
   mvn clean install
   ```

3. **Configure the Application Properties**
   - Update `src/test/java/utils/DriverUtils` with your device details:
     ```
     appium.url=http://127.0.0.1:5/wd/hub
     platform.name=Android
     device.name=pixel-6-pro
     app.path=./src/test/resources/apps/trustwallet.apk
     ```

### Running Tests

1. **Start the Android Emulator**
   - Using Android Studio:
     - Open Android Studio > Device Manager
     - Click the play button next to your virtual device
   - Using Command Line:
     ```
     emulator -avd <your_avd_name> -no-snapshot-load
     ```
   - Verify device is connected:
     ```
     adb devices
     ```

2. **Start Appium Server**
   - Using Command Line:
     ```
     appium -p 4725 -a 127.0.0.1 --base-path /wd/hub
     ```
   - Or using Appium Desktop:
     - Open Appium Desktop
     - Set host to 0.0.0.0 and port to 4725
     - Click "Start Server"

3. **Run Tests**
   - Run all tests:
     ```
     mvn clean test
     ```
   - Run specific test class:
     ```
     mvn clean test -Dtest=CreateWalletTest -DplatformName=Android
     ```
   - Run specific test method:
     ```
     mvn clean test -Dtest=CreateWalletTest#testCreateSecretPhraseWallet -DplatformName=Android
     ```
   - Run with specific platform (Android/iOS):
     ```
     mvn clean test -DplatformName=Android
     ```
   - Run tests with custom TestNG XML:
     ```
     mvn clean test -DsuiteXmlFile=testng.xml
     ```

   > **Note:** When running specific tests with `-Dtest=`, you must always include `-DplatformName=Android` parameter, as it's required by the BaseTest setup method but not automatically passed when bypassing the TestNG XML configuration.

### Appium Inspector Configuration

When using Appium Inspector to identify elements:

1. Set Remote Host: `127.0.0.1`
2. Set Port: `4725`
3. Set Path: `/wd/hub`
4. Configure Capabilities:
   {
     "appium:automationName": "UiAutomator2",
     "appium:platformName": "Android",
     "appium:platformVersion": "13",
     "appium:deviceName": "emulator-5554",
     "appium:app": "<absolute_path_to_your_apk>"
   }
   ```
5. Click "Start Session"