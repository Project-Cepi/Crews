package world.cepi.crews.commands

import net.minestom.server.command.builder.Command
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.ArgumentPlayer
import world.cepi.kstom.command.arguments.literal

object CrewCommand : Command("crew") {

    init {
        val create = "create".literal()
        
        val invite = "invite".literal()

        val user = ArgumentPlayer("user")

        addSyntax(create) {

        }

        addSyntax(invite, user) {

        }
    }

}