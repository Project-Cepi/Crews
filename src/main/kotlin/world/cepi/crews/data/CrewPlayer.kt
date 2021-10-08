package world.cepi.crews.data

import net.minestom.server.entity.Player

data class CrewPlayer(val player: Player, val rank: CrewRank) {
    fun isOwner() = rank == CrewRank.CAPTAIN
}