package isel.tds.galo.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun waitingIndicator() = CircularProgressIndicator(
    Modifier.fillMaxSize().padding(30.dp),
    strokeWidth = 15.dp
)