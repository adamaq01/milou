package com.santiifm.milou.ui.screens.sources.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddConsoleDialog(
    name: String? = null,
    subPath: String? = null,
    consoleId: String? = null,
    onDismiss: () -> Unit,
    onConfirm: (String, String?) -> Unit
) {
    var name by remember { mutableStateOf(name?:"") }
    var subPath by remember { mutableStateOf(subPath?:"") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (consoleId == null) "Add Console" else "Edit Console") },
        text = {
            Column {
                Text("Enter console name:")
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("Enter console subpath (optional):")
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = subPath,
                    onValueChange = { subPath = it },
                    label = { Text("Subpath") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (name.isNotBlank()) {
                        onConfirm(name.trim(), if (subPath.isNotBlank()) subPath.trim() else null)
                        onDismiss()
                    }
                },
                enabled = name.isNotBlank()
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
