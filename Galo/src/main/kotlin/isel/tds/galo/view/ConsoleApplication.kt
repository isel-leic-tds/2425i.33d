package isel.tds.galo.view

import isel.tds.galo.model.Clash
import isel.tds.galo.storage.GameSerializer
import isel.tds.galo.storage.TextFileStorage

object ConsoleApplication {
    fun start() {
        var clash = Clash(TextFileStorage("games", GameSerializer))
        val commands = getCommands()
//        val commands = getCommands(TextFileStorage("games", GameSerializer))
//        var game = Game()
        while (true) {
            val (cmdName, args) = readCommand()
            val cmd = commands[cmdName]
            if (cmd == null) {
                println("Invalid command $cmdName")
            } else try {
                clash = cmd.execute(args, clash)
                clash.show()
                if (cmd.isToFinish) break
            } catch (e: IllegalStateException) {
                println(e.message)
            } catch (e: IllegalArgumentException) {
                println("${e.message}\nUse: $cmdName")
            }catch(e: Exception){
                println("Error: ${e.message}")
            }
        }

    }
}
