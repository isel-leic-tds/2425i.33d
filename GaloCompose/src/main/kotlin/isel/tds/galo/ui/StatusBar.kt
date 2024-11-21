package isel.tds.galo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import isel.tds.galo.model.Board
import isel.tds.galo.model.BoardDraw
import isel.tds.galo.model.BoardRun
import isel.tds.galo.model.BoardWin

@Composable
fun StatusBar(board: Board?) {
    Row(modifier = Modifier.width(GRID_WIDTH)
        .background(Color.LightGray),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
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