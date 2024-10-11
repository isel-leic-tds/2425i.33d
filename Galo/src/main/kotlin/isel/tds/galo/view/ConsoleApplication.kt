package isel.tds.galo.view

import isel.tds.galo.model.Board

object ConsoleApplication {
    fun start() {

        val commands = getCommands()
        var board: Board? = null
        while (true) {
            print("> ")
            //val cmd = readln().uppercase().split(' ')
            val (cmdName, args) = readCommand()
            val cmd = commands[cmdName]
            if (cmd == null) {
                println("Invalid command $cmdName")
            }else{
                board = cmd.execute(args, board)
                board.show()
                if(cmd.isToFinish) break
            }
        }
    }



}
