package view

enum class SlotImage(val filename: String) {
    APPLE("apple.png"),
    BAR("bar.png"),
    BELL("bell.png"),
    CHERRIES("cherries.png"),
    DIAMOND("diamond.png"),
    GRAPPES("grappes.png"),
    LEMON("lemon.png"),
    SEVEN("seven.png"),
    WATERMELON("watermelon.png"),
    BANANA("banana.png");
}

fun Byte.toSlotImage(): SlotImage {
    return when (this) {
        0.toByte() -> SlotImage.APPLE
        1.toByte() -> SlotImage.BAR
        2.toByte() -> SlotImage.BELL
        3.toByte() -> SlotImage.CHERRIES
        4.toByte() -> SlotImage.DIAMOND
        5.toByte() -> SlotImage.GRAPPES
        6.toByte() -> SlotImage.LEMON
        7.toByte() -> SlotImage.SEVEN
        8.toByte() -> SlotImage.WATERMELON
        9.toByte() -> SlotImage.BANANA
        else -> throw IllegalArgumentException("Invalid slot value: $this")
    }
}