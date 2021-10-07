package world.cepi.crews

import net.minestom.server.entity.Player
import world.cepi.crews.data.Crew

object CrewManager {

    val crewToPlayer = mutableMapOf<Player, Crew>()

    val crewInviteToPlayer = mutableMapOf<Player, Crew>()

    fun hasCrew(player: Player) =
        crewToPlayer.containsKey(player)

    fun createCrew(player: Player) {
        crewToPlayer[player] = Crew(mutableListOf(player))
    }

    fun getCrew(player: Player) = crewToPlayer[player]

    fun disbandCrew(owner: Player): Boolean {
        val crew = getCrew(owner) ?: return false

        crew.disband()

        crew.members.forEach {
            crewToPlayer.remove(it)
        }

        return true
    }

    fun invitePlayer(owner: Player, player: Player): Boolean {
        getCrew(owner)?.invite(player) ?: return false

        return true
    }

    fun hasInvite(player: Player) =
        crewInviteToPlayer.containsKey(player)

}