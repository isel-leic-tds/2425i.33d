package isel.tds.isel.tds.tennis

enum class Points(val value: Int) {
    LOVE(0),
    FIFTEEN(15),
    THIRTY(30),
    FORTY(40),
    ADVANTAGE(50),
    GAME(60);

    fun next(): Points = Points.values()[ordinal + 1]
}