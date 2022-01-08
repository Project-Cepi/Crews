package world.cepi.crews

import net.minestom.server.extensions.Extension;
import world.cepi.crews.commands.CrewCommand
import world.cepi.crews.commands.PartyFauxCommand
import world.cepi.crews.events.DropHandler
import world.cepi.kstom.event.listenOnly
import world.cepi.kstom.util.log
import world.cepi.kstom.util.node

class Crews : Extension() {

    override fun initialize(): LoadStatus {

        CrewCommand.register()
        PartyFauxCommand.register()

        node.listenOnly(DropHandler::drop)
        node.listenOnly(DropHandler::pickup)

        log.info("[Crews] has been enabled!")

        return LoadStatus.SUCCESS
    }

    override fun terminate() {

        CrewCommand.unregister()

        log.info("[Crews] has been disabled!")
    }

}