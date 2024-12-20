package model

data class SlotState
(val slot1: Byte, val slot2: Byte, val slot3: Byte) {
    init{
        require(slot1 in 0..9 && slot2 in 0..9 && slot3 in 0..9) {
            "Slot values must be between 0 and 9"
        }
    }
    companion object {
        fun random(): SlotState {
            return SlotState(generateRandomByteForSlot(), generateRandomByteForSlot(), generateRandomByteForSlot())
        }
        private fun generateRandomByteForSlot() = (0..9).random().toByte()
    }

}

fun SlotState.isWinner(): Boolean {
    return this.slot1 == slot2 && slot2 == slot3
}
//internal constructor