package isel.tds.galo.view

import androidx.compose.material.*
import androidx.compose.runtime.*
import isel.tds.galo.model.Name


enum class InputName(val txt: String) {
    ForStart("start"), ForJoin("join")
}


@Composable
fun StartOrJoinDialog(
    type: InputName,
    onCancel: ()->Unit,
    onAction: (Name)->Unit
) {
    var name by remember { mutableStateOf("") }  // Name in edition
    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text(text = "Name to ${type.txt}",
            style = MaterialTheme.typography.h5
        ) },
        text = { OutlinedTextField(value = name,
            onValueChange = { name = it },
            label = { Text("Name of game") }
        ) },
        confirmButton = {
            TextButton(enabled = Name.isValid(name),
                onClick = { onAction(Name(name)) }
            ) { Text(type.txt) }
        },
        dismissButton = {
            TextButton(onClick = onCancel){ Text("cancel") }
        }
    )
}