package isel.tds.galo.view

import isel.tds.galo.model.Board

object ConsoleApplication {
    fun start() {

        val commands = getCommands()
        var board: Board? = null
        while (true) {
            val (cmdName, args) = readCommand()
            val cmd = commands[cmdName]
            if (cmd == null) {
                println("Invalid command $cmdName")
            } else try {
                board = cmd.execute(args, board)
                board.show()
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
