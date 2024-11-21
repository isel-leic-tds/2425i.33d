package isel.tds.galo.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import isel.tds.galo.model.Player

@Composable
fun PlayerView (
    size: Dp= 100.dp,
    player: Player?,
    onClick: ()->Unit = {},
    modifier: Modifier = Modifier.size(size)
){
    if(player == null){
        Box( modifier
            .clickable(onClick = onClick))
    }else{
        val filename = when(player){
            Player.X -> "cross"
            Player.O -> "circle"
        }
        Image(painter = painterResource("$filename.png"),
            contentDescription = "Player $player $filename",
            modifier = modifier)
    }
}

@Composable
@Preview
fun PlayerPreview(){
    Column( Modifier.background(Color.Black)) {
        PlayerView(100.dp, null)
        PlayerView(100.dp, Player.X)
        PlayerView(50.dp, Player.O)
    }
}