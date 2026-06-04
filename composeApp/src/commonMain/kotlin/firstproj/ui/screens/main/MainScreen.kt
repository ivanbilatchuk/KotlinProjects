package firstproj.ui.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import firstproj.TimeZoneHelperImpl
import firstproj.ui.screens.AppScreen

@Composable
fun MainScreen(
    onNavigateToScreen: (AppScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    val timeZoneHelper = remember { TimeZoneHelperImpl() }
    var currentTime by remember { mutableStateOf(timeZoneHelper.currentTime()) }
    var currentTimeZone by remember { mutableStateOf(timeZoneHelper.currentTimeZone()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Timezone Dashboard",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = currentTime,
                    style = MaterialTheme.typography.displayMedium,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Time Zone: $currentTimeZone",
                    style = MaterialTheme.typography.bodyMedium
                )
                Button(
                    onClick = {
                        currentTime = timeZoneHelper.currentTime()
                        currentTimeZone = timeZoneHelper.currentTimeZone()
                        Logger.i { "Time updated manually: $currentTime ($currentTimeZone)" }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Refresh",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Refresh & Log")
                }
            }
        }

        Text(
            text = "Explore UI Components",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.Start).padding(top = 8.dp)
        )

        val items = listOf(
            ComponentNavItem(AppScreen.Buttons, "Interactive button styles", Icons.Default.PlayArrow),
            ComponentNavItem(AppScreen.Checkboxes, "Multi-selection boxes", Icons.Default.Check),
            ComponentNavItem(AppScreen.Chips, "Compact tag chips", Icons.Default.Star),
            ComponentNavItem(AppScreen.DatePicker, "Calendar selector dialog", Icons.Default.DateRange),
            ComponentNavItem(AppScreen.Dialog, "Pop-up alert dialogs", Icons.Default.Info),
            ComponentNavItem(AppScreen.Divider, "Layout separators", Icons.Default.Menu),
            ComponentNavItem(AppScreen.ProgressBar, "Loading indicator status", Icons.Default.List),
            ComponentNavItem(AppScreen.RadioButtons, "Single-selection buttons", Icons.Default.LocationOn),
            ComponentNavItem(AppScreen.Switch, "Binary toggle switches", Icons.Default.Settings),
            ComponentNavItem(AppScreen.TimePicker, "Clock time selector dialog", Icons.Default.Build)
        )

        for (i in items.indices step 2) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ComponentCard(
                    item = items[i],
                    onClick = { onNavigateToScreen(items[i].screen) },
                    modifier = Modifier.weight(1f)
                )
                if (i + 1 < items.size) {
                    ComponentCard(
                        item = items[i + 1],
                        onClick = { onNavigateToScreen(items[i + 1].screen) },
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

data class ComponentNavItem(
    val screen: AppScreen,
    val description: String,
    val icon: ImageVector
)

@Composable
fun ComponentCard(
    item: ComponentNavItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(110.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.screen.title,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = item.screen.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
