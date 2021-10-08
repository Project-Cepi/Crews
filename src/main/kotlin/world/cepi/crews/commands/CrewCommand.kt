package world.cepi.crews.commands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import world.cepi.crews.CrewManager
import world.cepi.crews.crew
import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.kstom.command.arguments.ArgumentPlayer
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand

object CrewCommand : Kommand({

    val create by literal
    val invite by literal
    val disband by literal
    val accept by literal
    val list by literal

    val user = ArgumentPlayer("user")

    syntax(create) {
        if (CrewManager.hasCrew(player)) {
            player.sendFormattedTranslatableMessage("crews", "in")
            return@syntax
        }

        CrewManager.createCrew(player)

        player.sendFormattedTranslatableMessage("crews", "create")
    }

    syntax(invite, user) {

        if (!CrewManager.hasCrew(player)) {
            player.sendFormattedTranslatableMessage("crews", "in.not")
            return@syntax
        }

        if ((!user).crew != null) {
            player.sendFormattedTranslatableMessage(
                "crews", "user.in",
                Component.text((!user).username, NamedTextColor.BLUE)
            )
        }

        CrewManager.invitePlayer(player, !user)

        player.sendFormattedTranslatableMessage(
            "crews", "invite",
            Component.text((!user).username, NamedTextColor.BLUE)
        )

        sender.sendFormattedTranslatableMessage(
            "crews", "invited",
            Component.text(player.username, NamedTextColor.BLUE)
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
            Component.text(crew.members.joinToString(", ") { it.username })
        )
    }

    syntax(disband) {

        if (!CrewManager.hasCrew(player)) {
            player.sendFormattedTranslatableMessage("crews", "in.not")
            return@syntax
        }

        CrewManager.disbandCrew(player)

    }

    syntax(accept) {
        if (CrewManager.hasCrew(player)) {
            player.sendFormattedTranslatableMessage("crews", "in")
            return@syntax
        }

        CrewManager.acceptInvite(player)
    }

}, "crew")