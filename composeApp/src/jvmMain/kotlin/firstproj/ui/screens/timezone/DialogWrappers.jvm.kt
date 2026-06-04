package firstproj.ui.screens.timezone

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogWindow
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberDialogState

@Composable
actual fun AddTimeDialogWrapper(
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    DialogWindow(
        onCloseRequest = { onDismiss() },
        state = rememberDialogState(
            position = WindowPosition(Alignment.Center),
            size = DpSize(width = 400.dp, height = Dp.Unspecified),
        ),
        title = "Add Timezones",
        content = {
            content()
        }
    )
}

@Composable
actual fun MeetingDialogWrapper(
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    DialogWindow(
        onCloseRequest = { onDismiss() },
        state = rememberDialogState(
            position = WindowPosition(Alignment.Center),
            size = DpSize(width = 400.dp, height = Dp.Unspecified),
        ),
        title = "Meeting Times",
        content = {
            content()
        }
    )
}
