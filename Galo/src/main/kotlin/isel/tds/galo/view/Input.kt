package isel.tds.galo.view


data class CommandLine(val name: String, val args: List<String>)

tailrec fun readCommand(): CommandLine {
    print("> ")
    val line = readln().split(' ').filter { it.isNotBlank() }
    return if (line.isEmpty()) readCommand()
    else CommandLine(line.first().uppercase(), line.drop(1))
}