# AddrMapper

**Contribute house number data to OpenStreetMap—offline or with map view, featuring GPS precision feedback, duplicate detection, and quality-aware changeset tagging.** (maybe?)

---

## Table of Contents
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

---

## Features
- **Fully offline-capable**: Input house numbers even without connectivity.
- **GPS precision feedback**: Visual indicators (green/orange/red) based on accuracy.
- **Duplicate detection**: Proximity and normalized street-name matching with manual override.
- **Changeset quality tags**: Automatically adds warning counts for low-precision or duplicate entries.
- **Hybrid sync strategy**: Syncs immediately when online or queues data with retry logic via WorkManager.

---

## Installation
1. Clone the repository:  
   `git clone link`
2. Open the project in Android Studio and ensure SDK minimum is 26 (Android 8.0).
3. Build and run on a device or emulator with GPS support.

---

## Usage
1. Enter house numbers using the numeric keypad.
2. View GPS accuracy via color-coded indicator.
3. Adjust street name suggestions and manual offset if needed.
4. Submit data online or queue for later upload with automatic changeset grouping.

*(screenshots)*

---

## Contributing
We welcome contributions! Please see [`CONTRIBUTING.md`](CONTRIBUTING.md) for guidelines.  
Typical workflow:
1. Fork the repo  
2. Create a feature branch (`git checkout -b feature-name`)  
3. Make changes, commit (`git commit -m 'Add feature X'`), and push  
4. Open a pull request and reference any issues.

