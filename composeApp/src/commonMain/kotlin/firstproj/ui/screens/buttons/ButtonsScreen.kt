package firstproj.ui.screens.buttons

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonsScreen(
    onAction: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Material 3 Buttons",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(onClick = { onAction("Filled Button Clicked") }) {
                    Text("Filled Button")
                }

                ElevatedButton(onClick = { onAction("Elevated Button Clicked") }) {
                    Text("Elevated Button")
                }

                FilledTonalButton(onClick = { onAction("Filled Tonal Button Clicked") }) {
                    Text("Filled Tonal Button")
                }

                OutlinedButton(onClick = { onAction("Outlined Button Clicked") }) {
                    Text("Outlined Button")
                }

                TextButton(onClick = { onAction("Text Button Clicked") }) {
                    Text("Text Button")
                }

                Button(onClick = { onAction("Button with Icon Clicked") }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        Text("Button with Icon")
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text("Floating Action Button", style = MaterialTheme.typography.bodySmall)

                FloatingActionButton(
                    onClick = { onAction("FAB Clicked") },
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                }
            }
        }
    }
}
