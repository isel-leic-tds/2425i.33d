package isel.tds.galo.view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import isel.tds.galo.model.*


val CELL_SIZE = 100.dp
val LINE_WIDTH = 5.dp
val GRID_WIDTH = CELL_SIZE * BOARD_SIZE + LINE_WIDTH * (BOARD_SIZE -1)

@Composable
fun GridView( moves: Moves?, onClickCell: (Position)->Unit){
    Column(
        modifier=Modifier.size(GRID_WIDTH).background(Color.Black),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        repeat(BOARD_SIZE) { lin ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(BOARD_SIZE) { col ->
                    val pos = Position(lin * BOARD_SIZE+col)
                    PlayerView(
                        100.dp,
                        moves?.get(pos),
                        onClick = {onClickCell(pos)},
                        modifier = Modifier.size(CELL_SIZE).background(Color.White)
                    )
                }
            }
        }
    }

}

@Composable
@Preview
fun GridViewPreview(){
    val board = Board()
    GridView(board.moves, {})
}