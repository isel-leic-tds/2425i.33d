package isel.tds.galo.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import isel.tds.galo.model.*

@Composable
fun StatusBar(board: Board?, you: Player?) {
    Row(modifier = Modifier.width(GRID_WIDTH)
        .background(Color.LightGray),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        you?.let{
            Text("You ", style = MaterialTheme.typography.h5)
            PlayerView(modifier = Modifier.padding(4.dp), player = it)
            Spacer(Modifier.width(30.dp))
        }
        val (text, player) = when(board){
            null -> "Game not started" to null
            is BoardRun -> "Turn: " to board.turn
            is BoardWin -> "Winner: " to board.winner
            is BoardDraw -> "Draw" to null
        }
        Text(text, fontSize = 32.sp)
        PlayerView(32.dp, player)
    }
}