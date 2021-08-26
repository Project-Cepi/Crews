package world.cepi.crews

import net.minestom.server.extensions.Extension;
import world.cepi.crews.commands.CrewCommand
import world.cepi.kstom.command.register
import world.cepi.kstom.command.unregister

class Crews : Extension() {

    override fun initialize() {

        CrewCommand.register()

        logger.info("[Crews] has been enabled!")
    }

    override fun terminate() {

        CrewCommand.unregister()

        logger.info("[Crews] has been disabled!")
    }

}