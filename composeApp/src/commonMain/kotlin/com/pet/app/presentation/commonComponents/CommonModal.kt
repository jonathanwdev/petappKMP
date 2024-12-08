package com.pet.app.presentation.commonComponents


import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CommonModal(
    onDismiss: () -> Unit,
    onDismissButtonLabel: String = "Dismiss",
    onConfirm: () -> Unit,
    onConfirmButtonLabel: String = "Confirm",
    title: String,
    text: String,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        title = { Text(title) },
        text = { Text(text) },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(onConfirmButtonLabel)
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(onDismissButtonLabel)
            }
        }
    )

}

