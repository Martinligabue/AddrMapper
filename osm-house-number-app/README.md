# OSM House Number Entry App

## Overview

This project is an Android application designed to contribute house number data to OpenStreetMap (OSM). It features both offline and graphical modes for data entry.

## Project Structure

```
osm-house-number-app
├── app
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com
│   │   │   │       └── example
│   │   │   │           └── osmapp
│   │   │   │               ├── MainActivity.kt
│   │   │   │               └── App.kt
│   │   │   ├── res
│   │   │   │   ├── layout
│   │   │   │   │   └── activity_main.xml
│   │   │   │   ├── values
│   │   │   │   │   └── strings.xml
│   │   │   │   └── mipmap
│   │   │   │       └── ic_launcher.xml
│   │   │   └── AndroidManifest.xml
│   │   └── test
│   │       └── java
│   │           └── com
│   │               └── example
│   │                   └── osmapp
│   │                       └── ExampleUnitTest.kt
│   └── build.gradle
├── build.gradle
├── settings.gradle
└── README.md
```

## Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd osm-house-number-app
   ```

2. **Open the Project**
   Open the project in your preferred IDE.

3. **Install Recommended Extensions**
   - Kotlin Language
   - Android iOS Support
   - Gradle for Java
   - XML Tools

4. **Build the Project**
   Use the following command to build the project:
   ```bash
   ./gradlew build
   ```

5. **Run the Application**
   You can run the application on an emulator or a physical device.

## Usage

The app allows users to enter house number data in two modes:
- **Offline Mode**: For quick data entry without internet access.
- **Graphical Mode**: Provides a map interface with GPS precision feedback.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any enhancements or bug fixes.

## License

This project is licensed under the MIT License. See the LICENSE file for details.