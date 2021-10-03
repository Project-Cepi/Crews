package world.cepi.crews.data

import net.minestom.server.entity.Player
import net.minestom.server.utils.time.TimeUnit
import java.time.Duration

class Crew(
    val members: List<Player> = mutableListOf(),
    var isPrivate: Boolean = true,
    private val invites: MutableMap<Player, Long> = mutableMapOf()
) {

    companion object {
        val inviteDecay = Duration.of(5, TimeUnit.MINUTE)
    }

    fun invite(player: Player) {
        invites[player] = System.currentTimeMillis()
    }

    fun isInvited(player: Player): Boolean {
        cleanInvites()
        return invites.containsKey(player)
    }

    fun disband() {
        members.forEach {
            it.sendMessage("Crew disbanded.")
        }
    }

    fun cleanInvites() {
        invites.values.removeIf { System.currentTimeMillis() - inviteDecay.toMillis() > it }
    }


}