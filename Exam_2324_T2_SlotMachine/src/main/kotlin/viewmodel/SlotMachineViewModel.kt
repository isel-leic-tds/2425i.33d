package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import model.SlotState
import model.isWinner

class SlotMachineViewModel {
    var playerName by mutableStateOf("")
    var slotState by mutableStateOf(SlotState.random())
        private set

    fun play() {
        slotState = SlotState.random()
    }

    fun isWinner() = slotState.isWinner()

    fun isPlayerNameValid() = playerName.isNotBlank() && playerName.filter { !it.isWhitespace()  }.length >= 3


}

