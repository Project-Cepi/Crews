package world.cepi.crews.data

import net.minestom.server.entity.Player
import net.minestom.server.utils.time.TimeUnit
import java.time.Duration

class Crew(
    val members: MutableList<Player> = mutableListOf(),
    var isPrivate: Boolean = true
) {

    companion object {
        val inviteDecay = Duration.of(5, TimeUnit.MINUTE)
    }

    fun disband() {
        members.forEach {
            it.sendMessage("Crew disbanded.")
        }
    }


}