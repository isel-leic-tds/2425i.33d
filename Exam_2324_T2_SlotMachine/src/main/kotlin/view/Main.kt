package view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import model.SlotState
import viewmodel.SlotMachineViewModel

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Slot Machine",
        icon = painterResource("slotMachine.jpg")
    ) {

        val slotMachineViewModel = remember { SlotMachineViewModel() }

        MaterialTheme {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                PlayerInputDetails(
                    nameGetter = { slotMachineViewModel.playerName},//slotMachineViewModel::playerName ,
                    nameSetter = { changedName -> slotMachineViewModel.playerName = changedName })
                SlotMachine(
                    slotState = slotMachineViewModel::slotState,
                    isEnabled = slotMachineViewModel::isPlayerNameValid,
                    play = slotMachineViewModel::play
                )
                ResultPanel(slotMachineViewModel)
            }
        }
    }
}

@Composable
private fun ResultPanel(slotMachineViewModel: SlotMachineViewModel) {
    Text(
        text = if (slotMachineViewModel.isWinner()) "Congratulations, ${slotMachineViewModel.playerName}! You won!" else "Better luck next time, ${slotMachineViewModel.playerName}!",
        modifier = Modifier.padding(top = 16.dp)
    )
}

@Composable
private fun PlayerInputDetails(nameGetter: () -> String, nameSetter: (String) -> Unit) {
    OutlinedTextField(
        value = nameGetter(),
        onValueChange = nameSetter,
        label = { Text("Enter your name") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
    )
}

@Composable
fun SlotMachine(slotState: () -> SlotState, isEnabled: () -> Boolean, play: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val (slot1, slot2, slot3) = slotState()

        AddSlot(slot1)
        Spacer(modifier = Modifier.width(16.dp))
        AddSlot(slot2)
        Spacer(modifier = Modifier.width(16.dp))
        AddSlot(slot3)
        Spacer(modifier = Modifier.width(16.dp))


    }
    Button(
        enabled = isEnabled(),
        onClick = play, modifier = Modifier.padding(top = 16.dp)
    ) {
        Text("Play")
    }
}

@Composable
fun AddSlot(slot: Byte) {
    Image(
        painter = painterResource(slot.toSlotImage().filename),
        contentDescription = null,
        modifier = Modifier.size(80.dp).clip(shape = RectangleShape).background(Color.White).padding(8.dp)
    )
}
