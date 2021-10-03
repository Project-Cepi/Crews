package world.cepi.crews

import net.minestom.server.entity.Player
import world.cepi.crews.data.Crew
import java.lang.ref.WeakReference

object CrewManager {

    val crewToPlayer = mutableMapOf<WeakReference<Player>, Crew>()

    val crewInviteToPlayer = mutableMapOf<WeakReference<Player>, Crew>()

    fun hasCrew(player: Player) =
        crewToPlayer.containsKey(WeakReference(player))

    fun createCrew(player: Player) {
        crewToPlayer[WeakReference(player)] = Crew(mutableListOf(player))
    }

    fun getCrew(player: Player) = crewToPlayer[WeakReference(player)]

    fun disbandCrew(owner: Player): Boolean {
        (getCrew(owner) ?: return false).disband()

        return true
    }

    fun hasInvite(player: Player) =
        crewInviteToPlayer.containsKey(WeakReference(player))

}