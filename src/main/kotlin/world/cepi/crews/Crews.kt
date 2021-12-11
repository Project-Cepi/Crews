package world.cepi.crews

import net.minestom.server.extensions.Extension;
import world.cepi.crews.commands.CrewCommand
import world.cepi.crews.commands.PartyFauxCommand
import world.cepi.crews.events.DropHandler
import world.cepi.kstom.event.listenOnly

class Crews : Extension() {

    override fun initialize() {

        CrewCommand.register()
        PartyFauxCommand.register()

        eventNode.listenOnly(DropHandler::drop)
        eventNode.listenOnly(DropHandler::pickup)

        logger.info("[Crews] has been enabled!")
    }

    override fun terminate() {

        CrewCommand.unregister()

        logger.info("[Crews] has been disabled!")
    }

}