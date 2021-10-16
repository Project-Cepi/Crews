package world.cepi.crews.events

import net.minestom.server.event.item.ItemDropEvent
import net.minestom.server.entity.ItemEntity
import net.minestom.server.entity.Player
import net.minestom.server.event.item.PickupItemEvent
import net.minestom.server.potion.Potion
import net.minestom.server.potion.PotionEffect
import net.minestom.server.utils.time.TimeUnit
import world.cepi.crews.crew

// adopted from https://github.com/Minestom/VanillaReimplementation/blob/6e71d1c418d5df5e5b1f9856f9d12017ec0a62a9/src/main/java/net/minestom/vanilla/PlayerInit.java
object DropHandler {

    fun drop(event: ItemDropEvent) = with(event) {
        val droppedItem = event.itemStack

        val itemEntity = ItemEntity(droppedItem)
        itemEntity.setPickupDelay(500, TimeUnit.MILLISECOND)
        itemEntity.isAutoViewable = false
        itemEntity.setInstance(player.instance!!, player.position.add(.0, 1.5, .0))
        (player.crew?.members?.keys ?: listOf(player)).forEach { itemEntity.addViewer(it) }
        itemEntity.isGlowing = true
        val velocity = player.position.direction().mul(6.0)
        itemEntity.velocity = velocity
    }

    fun pickup(event: PickupItemEvent) = with(event) {

        val player = entity as? Player ?: return@with

        event.isCancelled = !player.inventory.addItemStack(event.itemStack)
    }

}