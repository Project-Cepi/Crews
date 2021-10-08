package world.cepi.crews.data

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.entity.Player
import net.minestom.server.utils.time.TimeUnit
import world.cepi.crews.CrewManager
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import java.time.Duration

class Crew(
    val members: MutableMap<Player, CrewPlayer> = mutableMapOf(),
    var isPrivate: Boolean = true
) {

    init {
        members.keys.forEach {
            CrewManager.crewToPlayer[it] = this
        }
    }

    constructor(player: Player): this(mutableMapOf(player to CrewPlayer(player, CrewRank.CAPTAIN)))

    companion object {
        val inviteDecay = Duration.of(5, TimeUnit.MINUTE)
    }

    fun add(player: Player, rank: CrewRank = CrewRank.CAPTAIN) {
        members[player] = CrewPlayer(player, rank)
    }

    operator fun get(player: Player) = members[player]

    fun leave(player: Player) {
        members.remove(player)

        members.keys.forEach {
            it.sendFormattedTranslatableMessage("crews", "leave", Component.text(it.username, NamedTextColor.BLUE))
        }
    }

    fun disband() {
        members.keys.forEach {
            it.sendFormattedTranslatableMessage("crews", "disband")
            CrewManager.crewToPlayer.remove(it)
        }
    }


}