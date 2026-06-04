package firstproj.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

import firstproj.ui.screens.main.MainScreen
import firstproj.ui.screens.buttons.ButtonsScreen
import firstproj.ui.screens.checkboxes.CheckboxesScreen
import firstproj.ui.screens.chips.ChipsScreen
import firstproj.ui.screens.datepicker.DatePickerScreen
import firstproj.ui.screens.dialogs.DialogScreen
import firstproj.ui.screens.divider.DividerScreen
import firstproj.ui.screens.progress.ProgressBarScreen
import firstproj.ui.screens.radio.RadioButtonsScreen
import firstproj.ui.screens.switches.SwitchScreen
import firstproj.ui.screens.timepicker.TimePickerScreen

enum class AppScreen(val title: String) {
    Main("Dashboard"),
    Buttons("Buttons Showcase"),
    Checkboxes("Checkboxes Showcase"),
    Chips("Chips Showcase"),
    DatePicker("DatePicker Dialog Showcase"),
    Dialog("Dialog Showcase"),
    Divider("Divider Showcase"),
    ProgressBar("Progress Bar Showcase"),
    RadioButtons("Radio Buttons Showcase"),
    Switch("Switch Showcase"),
    TimePicker("TimePicker Dialog Showcase")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    currentScreen: AppScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = currentScreen.title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: AppScreen.Main.name
    val currentScreen = remember(currentRoute) {
        try {
            AppScreen.valueOf(currentRoute)
        } catch (e: Exception) {
            AppScreen.Main
        }
    }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val showMessage: (String) -> Unit = { message ->
        scope.launch {
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        topBar = {
            AppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreen.Main.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = AppScreen.Main.name) {
                MainScreen(
                    onNavigateToScreen = { screen ->
                        navController.navigate(screen.name)
                    }
                )
            }
            composable(route = AppScreen.Buttons.name) {
                ButtonsScreen(onAction = showMessage)
            }
            composable(route = AppScreen.Checkboxes.name) {
                CheckboxesScreen(onAction = showMessage)
            }
            composable(route = AppScreen.Chips.name) {
                ChipsScreen(onAction = showMessage)
            }
            composable(route = AppScreen.DatePicker.name) {
                DatePickerScreen(onAction = showMessage)
            }
            composable(route = AppScreen.Dialog.name) {
                DialogScreen(onAction = showMessage)
            }
            composable(route = AppScreen.Divider.name) {
                DividerScreen()
            }
            composable(route = AppScreen.ProgressBar.name) {
                ProgressBarScreen()
            }
            composable(route = AppScreen.RadioButtons.name) {
                RadioButtonsScreen(onAction = showMessage)
            }
            composable(route = AppScreen.Switch.name) {
                SwitchScreen(onAction = showMessage)
            }
            composable(route = AppScreen.TimePicker.name) {
                TimePickerScreen(onAction = showMessage)
            }
        }
    }
}
