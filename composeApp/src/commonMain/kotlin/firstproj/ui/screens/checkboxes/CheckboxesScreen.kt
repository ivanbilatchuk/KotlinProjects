package firstproj.ui.screens.checkboxes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp

@Composable
fun CheckboxesScreen(
    onAction: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var standaloneChecked by remember { mutableStateOf(false) }

    val options = remember { mutableStateListOf("Kotlin", "Java", "TypeScript") }
    val checkedStates = remember { mutableStateMapOf("Kotlin" to true, "Java" to false, "TypeScript" to false) }

    val child1 = "Notifications"
    val child2 = "Sound"
    val childStates = remember { mutableStateMapOf(child1 to true, child2 to false) }

    val parentState = remember(childStates[child1], childStates[child2]) {
        val hasTrue = childStates.values.any { it }
        val hasFalse = childStates.values.any { !it }
        when {
            hasTrue && hasFalse -> ToggleableState.Indeterminate
            hasTrue -> ToggleableState.On
            else -> ToggleableState.Off
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Material 3 Checkboxes",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
                Text("Standalone Checkbox", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { standaloneChecked = !standaloneChecked }
                ) {
                    Checkbox(
                        checked = standaloneChecked,
                        onCheckedChange = { standaloneChecked = it }
                    )
                    Text("I agree to the Terms of Service")
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
                Text("Multi-Option Checklist", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                options.forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                val current = checkedStates[option] ?: false
                                checkedStates[option] = !current
                                onAction("$option toggled to ${!current}")
                            }
                            .padding(vertical = 4.dp)
                    ) {
                        Checkbox(
                            checked = checkedStates[option] ?: false,
                            onCheckedChange = {
                                checkedStates[option] = it
                                onAction("$option toggled to $it")
                            }
                        )
                        Text(option)
                    }
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
                Text("Tri-State / Parent-Child Checkbox", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val newState = parentState != ToggleableState.On
                            childStates[child1] = newState
                            childStates[child2] = newState
                            onAction("Parent set all to $newState")
                        }
                ) {
                    TriStateCheckbox(
                        state = parentState,
                        onClick = {
                            val newState = parentState != ToggleableState.On
                            childStates[child1] = newState
                            childStates[child2] = newState
                            onAction("Parent set all to $newState")
                        }
                    )
                    Text("Enable Preferences", style = MaterialTheme.typography.bodyLarge)
                }

                Spacer(modifier = Modifier.height(4.dp))

                listOf(child1, child2).forEach { child ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 32.dp, top = 4.dp, bottom = 4.dp)
                            .clickable {
                                val current = childStates[child] ?: false
                                childStates[child] = !current
                                onAction("$child toggled to ${!current}")
                            }
                    ) {
                        Checkbox(
                            checked = childStates[child] ?: false,
                            onCheckedChange = {
                                childStates[child] = it
                                onAction("$child toggled to $it")
                            }
                        )
                        Text(child)
                    }
                }
            }
        }
    }
}
