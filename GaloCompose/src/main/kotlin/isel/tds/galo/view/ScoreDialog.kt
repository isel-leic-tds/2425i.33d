package isel.tds.galo.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import isel.tds.galo.model.Name
import isel.tds.galo.model.Player
import isel.tds.galo.model.Score

//@Composable
//fun ScoreDialog( score: Score, onClose: ()->Unit ) = AlertDialog(
//    onDismissRequest = onClose,
//    title = { Text("Score", style = MaterialTheme.typography.h4) },
//    text = {
//        Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
//            Column {
//                Player.entries.forEach { player ->
//                    Row(verticalAlignment = Alignment.CenterVertically) {
//                        PlayerView(size=20.dp, player=player)
//                        Text(" - ${score[player]}",
//                            style = MaterialTheme.typography.h6
//                        )
//                    }   }   }
//            Text("Draws - ${score[null]}",
//                style = MaterialTheme.typography.h6
//            )
//        }
//    },
//    confirmButton = { TextButton(onClick = onClose){ Text("Close") } }
//)

@Composable
fun ScoreDialog(score: Score, name: Name, onClose: () -> Unit) =
    DialogBase("Score in $name", onClose) {
        Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            Column {
                Player.entries.forEach { player ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PlayerView(size = 20.dp, player)
                        Text(
                            text = " - ${score[player]}",
                            style = MaterialTheme.typography.h6
                        )
                    }
                }
            }
            Text(
                text = "Draws - ${score[null]}",
                style = MaterialTheme.typography.h6
            )
        }
    }