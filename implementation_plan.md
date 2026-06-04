# Implementation Plan - Custom Theme, Navigation, and UI Showcase (Custom Design)

Add a customized theme, navigation framework, and 10 demonstration screens to showcase Jetpack Compose Multiplatform components. The implementation will be written originally from scratch, using the reference repository solely for architectural inspiration.

## User Review Required

> [!IMPORTANT]
> - All theme configuration (`Color.kt`, `Theme.kt`, `Type.kt`) and the 10 showcase screens will be designed and implemented originally from scratch with our own custom styling.
> - The font asset `audiowide_regular.ttf` will be copied from `reference_repo` into our resources to fulfill the custom font requirement.
> - The Timezone Helper component will be integrated as a custom dashboard widget on the Main Screen.

## Proposed Changes

### Configuration & Dependency Layer

#### [MODIFY] [libs.versions.toml](file:///c:/Education/CHNU/2/CrossPlatform/FirstProj/gradle/libs.versions.toml)
- Add navigation and material-icons dependencies:
  - `navigation-compose = "2.9.2"`
  - `material-icons-core = "1.7.3"`
- Declare libraries in the `[libraries]` section:
  - `navigation-compose = { module = "org.jetbrains.androidx.navigation:navigation-compose", version.ref = "navigation-compose" }`
  - `material-icons-core = { module = "org.jetbrains.compose.material:material-icons-core", version.ref = "material-icons-core" }`

#### [MODIFY] [build.gradle.kts](file:///c:/Education/CHNU/2/CrossPlatform/FirstProj/composeApp/build.gradle.kts)
- Add the dependencies to the `commonMain` source set:
  - `implementation(libs.navigation.compose)`
  - `implementation(libs.material.icons.core)`

---

### Custom Theme & Assets

#### [NEW] [audiowide_regular.ttf](file:///c:/Education/CHNU/2/CrossPlatform/FirstProj/composeApp/src/commonMain/composeResources/font/audiowide_regular.ttf)
- Copy the `audiowide_regular.ttf` font asset from the reference repository.

#### [NEW] [Color.kt](file:///c:/Education/CHNU/2/CrossPlatform/FirstProj/composeApp/src/commonMain/kotlin/firstproj/ui/theme/Color.kt)
- Define a custom dark/light theme color palette (e.g. Deep Indigo and Violet accents).

#### [NEW] [Type.kt](file:///c:/Education/CHNU/2/CrossPlatform/FirstProj/composeApp/src/commonMain/kotlin/firstproj/ui/theme/Type.kt)
- Create custom Typography mappings utilizing the Audiowide font.

#### [NEW] [Theme.kt](file:///c:/Education/CHNU/2/CrossPlatform/FirstProj/composeApp/src/commonMain/kotlin/firstproj/ui/theme/Theme.kt)
- Define the `AppTheme` composable wrapper.

---

### Navigation & Entrypoint

#### [MODIFY] [App.kt](file:///c:/Education/CHNU/2/CrossPlatform/FirstProj/composeApp/src/commonMain/kotlin/firstproj/App.kt)
- Replace current layout with `AppTheme { AppNavigation() }`.

#### [NEW] [AppNavigation.kt](file:///c:/Education/CHNU/2/CrossPlatform/FirstProj/composeApp/src/commonMain/kotlin/firstproj/ui/screens/AppNavigation.kt)
- Define `AppScreen` enum with all 11 destinations (Main + 10 showcase screens).
- Implement standard M3 `Scaffold` with custom styling and a global `SnackbarHostState`.

---

### Component Showcase Screens

- All screens will be developed in `composeApp/src/commonMain/kotlin/firstproj/ui/screens`:
  - `main/MainScreen.kt` - dashboard with Timezone Helper clock and a clean grid of navigation buttons.
  - `buttons/ButtonsScreen.kt` - showcases buttons.
  - `checkboxes/CheckboxesScreen.kt` - showcases checkboxes.
  - `chips/ChipsScreen.kt` - showcases chips.
  - `datepicker/DatePickerScreen.kt` - showcases Datepicker dialog.
  - `dialogs/DialogScreen.kt` - showcases standard and custom dialogs.
  - `divider/DividerScreen.kt` - showcases divider lines.
  - `progress/ProgressBarScreen.kt` - showcases progress bars.
  - `radio/RadioButtonsScreen.kt` - showcases radio buttons.
  - `switches/SwitchScreen.kt` - showcases switch toggles.
  - `timepicker/TimePickerScreen.kt` - showcases Timepicker dialog.

---

## Verification Plan

### Automated Build Verification
- Compile and build using Gradle:
  ```powershell
  .\gradlew :composeApp:compileKotlinJvm
  ```

### Manual Verification
- Launch the application:
  ```powershell
  .\gradlew :composeApp:run
  ```
- Verify:
  - Theme styling and fonts render correctly.
  - Navigation between the main screen and all 10 component screens is smooth.
  - Each component screen behaves correctly.
  - Timezone helper runs on the main screen.
