package world.cepi.crews

import net.minestom.server.entity.Player
import world.cepi.crews.data.Crew
import java.time.Instant

object CrewManager {

    data class CrewInvite(val instant: Instant, val crew: Crew)

    val crewToPlayer = mutableMapOf<Player, Crew>()

    val crewInviteToPlayer = mutableMapOf<Player, CrewInvite>()

    fun hasCrew(player: Player) =
        crewToPlayer.containsKey(player)

    fun createCrew(player: Player) {
        crewToPlayer[player] = Crew(mutableListOf(player))
    }

    operator fun get(player: Player) = crewToPlayer[player]

    fun disbandCrew(owner: Player): Boolean {
        val crew = get(owner) ?: return false

        crew.disband()

        crew.members.forEach {
            crewToPlayer.remove(it)
        }

        return true
    }

    fun acceptInvite(player: Player): Boolean {
        val invite = crewInviteToPlayer[player] ?: return false

        invite.crew.members.add(player)

        return true
    }

    fun cleanInvites() {
        crewInviteToPlayer.values.removeIf { System.currentTimeMillis() - Crew.inviteDecay.toMillis() > it.instant.toEpochMilli() }
    }

    fun invitePlayer(owner: Player, player: Player): Boolean {
        val crew = get(owner) ?: return false

        crewInviteToPlayer[player] = CrewInvite(Instant.now(), crew)

        return true
    }

    fun hasInvite(player: Player) =
        crewInviteToPlayer.containsKey(player)

}

val Player.crew get() = CrewManager[this]