package world.cepi.crews.data

import net.minestom.server.entity.Player
import net.minestom.server.utils.time.TimeUnit
import java.time.Duration

class Crew(
    val members: List<Player> = listOf(),
    var isPrivate: Boolean = true,
    val invites: Map<Player, Int>
) {



}