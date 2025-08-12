# **Android OSM House Number Entry App – Development Guide**

## **Project Overview**

This guide provides a complete implementation plan for an Android app that contributes house number data to OpenStreetMap (OSM).  
 The app features two modes:

1. **Offline Mode** – minimal interface for fast data entry without internet.

2. **Graphical Mode** – optional map display with GPS precision feedback, a 3×4 numeric keypad, offset management, letter input, and intelligent changeset management with quality indicators.

## **1\. OpenStreetMap Integration with Quality Tags**

### **Understanding OSM Changeset Quality Tags**

**Modern OSM Changeset Warning System**  
 Since iD editor versions 2.15.0 and 2.16.0, OSM automatically adds validation-related tags to changesets:

* `warnings:{type}:{subtype}={count}` — unresolved issues

* `resolved:{type}:{subtype}={count}` — fixed issues

**Common Warning Tag Examples**

* `warnings:crossing_ways:highway-highway=1`

* `warnings:disconnected_way:highway=2`

* `warnings:missing_tag:highway_classification=3`

**App-Specific Quality Tags**  
 For this house number app, include custom quality tags in changesets:

\<warnings:low\_precision\_location\>3\</warnings:low\_precision\_location\>

\<warnings:possible\_duplicate\_address\>2\</warnings:possible\_duplicate\_address\>

### **House Number Data Structure**

**Single Node Approach**  
 Add house numbers as individual nodes (`addr:housenumber`) not attached directly to buildings.

**Improved Duplicate Detection Strategy**

* Search within 50 m radius.

* Match house number **and** normalized street name (case-folded, trimmed, accent-stripped).

* Use simple Levenshtein or Jaro–Winkler similarity for near matches (e.g., “Mozart-Gasse” vs “Mozartgasse”).

* Flag suspected duplicates but allow override.

---

## **2\. Android Location Management and Precision**

### **GPS Accuracy Without Internet**

Android GPS works offline; accuracy varies:

* Optimal conditions: **1–2 m**

* Typical outdoors: **5–10 m**

* Poor reception: **\>10 m**

### **Improved Location Configuration**

Instead of requiring obscure developer settings (“Force Full GNSS Measurements”), dynamically adjust accuracy to balance performance and battery:

private fun configureAdaptiveAccuracy(highPrecision: Boolean): LocationRequest {

    val priority \= if (highPrecision) {

        Priority.PRIORITY\_HIGH\_ACCURACY

    } else {

        Priority.PRIORITY\_BALANCED\_POWER\_ACCURACY

    }

    return LocationRequest.Builder(priority, 1000L)

        .setMinUpdateIntervalMillis(500L)

        .setMaxUpdates(Int.MAX\_VALUE)

        .setWaitForAccurateLocation(highPrecision)

        .build()

}

---

## **3\. Secure OAuth 2.0 with PKCE and Token Storage**

### **OAuth 2.0 with PKCE**

* Use **AppAuth for Android** with PKCE enabled to prevent code interception.

* Redirect URIs must be custom scheme-based (`com.example.app:/oauth2redirect`) and registered with OSM.

### **Secure Token Storage**

* Store tokens using **Android Keystore System** with encrypted SharedPreferences:

val masterKey \= MasterKey.Builder(context)

    .setKeyScheme(MasterKey.KeyScheme.AES256\_GCM)

    .build()

val securePrefs \= EncryptedSharedPreferences.create(

    context,

    "auth\_prefs",

    masterKey,

    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256\_SIV,

    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256\_GCM

)

---

## **4\. Changeset Geographic Management**

**Limits**

* Max 1 km × 1 km or 50 entries per changeset.

* Group by proximity, street, and session time.

---

## **5\. Offline-First Synchronization Model**

### **Hybrid Sync Strategy**

* **Immediate sync** when online.

* **Queue locally** when offline using Room DB \+ WorkManager.

* WorkManager periodically retries until upload succeeds.

workManager.enqueueUniqueWork(

    "upload\_changes",

    ExistingWorkPolicy.KEEP,

    uploadWorkRequest

)

---

## **6\. Gradle Dependencies (Updated 2025 Versions)**

dependencies {

    // Core Android

    implementation("androidx.core:core-ktx:1.13.1")

    implementation("androidx.appcompat:appcompat:1.7.1")

    implementation("androidx.constraintlayout:constraintlayout:2.2.0")

    implementation("androidx.fragment:fragment-ktx:1.8.2")

    // Architecture Components

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.4")

    implementation("androidx.room:room-runtime:2.7.0")

    implementation("androidx.room:room-ktx:2.7.0")

    kapt("androidx.room:room-compiler:2.7.0")

    // Location Services

    implementation("com.google.android.gms:play-services-location:21.3.0")

    implementation("com.google.android.gms:play-services-maps:19.0.0")

    // Networking

    implementation("com.squareup.retrofit2:retrofit:3.0.0")

    implementation("com.squareup.retrofit2:converter-gson:3.0.0")

    implementation("com.squareup.okhttp3:okhttp:5.0.0")

    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0")

    // Dependency Injection

    implementation("com.google.dagger:hilt-android:2.57")

    kapt("com.google.dagger:hilt-compiler:2.57")

    // OAuth 2.0 with PKCE

    implementation("net.openid:appauth:0.11.1")

    // Map rendering

    implementation("org.osmdroid:osmdroid-android:6.1.18")

    // Image loading

    implementation("com.github.bumptech.glide:glide:4.16.0")

}

---

## **7\. Data Validation & Quality Assurance**

* **House number format checks** (numeric \+ optional letter).

* **Street name normalization** before matching.

* **Coordinate boundary checks**.

* **Change tagging** for low precision & possible duplicates.

---

## **8\. Testing Enhancements**

* Location accuracy tests under multiple GPS conditions.

* Offline sync tests with simulated network loss.

* Security tests for token storage & OAuth flow.

structure?
com.example.osmhousenumber/
├── data/
│   ├── repository/
│   │   ├── LocationRepository.kt
│   │   └── OsmRepository.kt
│   ├── network/
│   │   ├── OsmApiService.kt
│   │   └── AuthService.kt
│   └── local/
│       └── PreferencesManager.kt
├── domain/
│   ├── model/
│   │   ├── HouseNumber.kt
│   │   └── Location.kt
│   └── usecase/
│       ├── SubmitHouseNumberUseCase.kt
│       └── GetLocationUseCase.kt
├── presentation/
│   ├── ui/
│   │   ├── MainActivity.kt
│   │   ├── NumberInputFragment.kt
│   │   └── LocationFragment.kt
│   ├── viewmodel/
│   │   └── MainViewModel.kt
│   └── adapter/
└── di/
    └── AppModule.kt
