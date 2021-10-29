package world.cepi.crews.commands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor
import world.cepi.crews.CrewManager
import world.cepi.crews.crew
import world.cepi.crews.data.Crew
import world.cepi.kepi.messages.sendFormattedMessage
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.kepi.messages.translations.formatTranslableMessage
import world.cepi.kstom.command.arguments.ArgumentPlayer
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand

object CrewCommand : Kommand({

    val create by literal
    val invite by literal
    val disband by literal
    val accept by literal
    val list by literal
    val leave by literal

    val user = ArgumentPlayer("user")

    syntax(create) {
        if (CrewManager.hasCrew(player)) {
            player.sendFormattedTranslatableMessage("crews", "in")
            return@syntax
        }

        Crew(player)

        player.sendFormattedTranslatableMessage("crews", "create")
    }

    syntax(invite, user) {

        if (!CrewManager.hasCrew(player)) {
            Crew(player)
        }

        if ((!user).crew != null) {
            player.sendFormattedTranslatableMessage(
                "crews", "user.in",
                Component.text((!user).username, NamedTextColor.BLUE)
            )
            return@syntax
        }

        CrewManager.invitePlayer(player, !user)

        player.sendFormattedTranslatableMessage(
            "crews", "invite",
            Component.text((!user).username, NamedTextColor.BLUE)
        )

        (!user).sendFormattedMessage(
            (!user).formatTranslableMessage(
                "crews", "invited",
                Component.text(player.username, NamedTextColor.BLUE)
            )
            .hoverEvent(HoverEvent.showText((!user).formatTranslableMessage("common", "click.to_open")))
            .clickEvent(ClickEvent.runCommand("/crew accept"))
        )

    }

    syntax(list) {
        if (!CrewManager.hasCrew(player)) {
            player.sendFormattedTranslatableMessage("crews", "in.not")
            return@syntax
        }

        val crew = CrewManager[player] ?: return@syntax

        player.sendFormattedTranslatableMessage(
            "crews", "members",
            Component.text(crew.members.keys.joinToString(", ") { it.username })
        )
    }

    syntax(disband) {

        if (!CrewManager.hasCrew(player)) {
            player.sendFormattedTranslatableMessage("crews", "in.not")
            return@syntax
        }

        player.crew?.disband()

    }

    syntax(accept) {
        if (CrewManager.hasCrew(player)) {
            player.sendFormattedTranslatableMessage("crews", "in")
            return@syntax
        }

        CrewManager.acceptInvite(player)
    }

    syntax(leave) {
        if (!CrewManager.hasCrew(player)) {
            player.sendFormattedTranslatableMessage("crews", "in.not")
            return@syntax
        }

        if (player.crew?.get(player)?.isOwner() == true) {
            player.sendFormattedTranslatableMessage("crews", "leave.denied")
        }

        player.crew?.leave(player)
    }

}, "crew")