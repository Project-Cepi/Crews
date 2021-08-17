package world.cepi.crews

import net.minestom.server.extensions.Extension;

class Crews : Extension() {

    override fun initialize() {
        logger.info("[Crews] has been enabled!")
    }

    override fun terminate() {
        logger.info("[Crews] has been disabled!")
    }

}