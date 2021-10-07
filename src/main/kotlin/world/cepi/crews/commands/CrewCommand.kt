package world.cepi.crews.commands

import world.cepi.crews.CrewManager
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
            player.sendMessage("You are already in a crew!")
            return@syntax
        }

        CrewManager.createCrew(player)

        player.sendMessage("Crew created! Invite players using /crew invite (player)")
    }

    syntax(invite, user) {

        if (!CrewManager.hasCrew(player)) {
            player.sendMessage("You are not in a crew!")
            return@syntax
        }

        CrewManager.invitePlayer(player, !user)

        player.sendMessage("Invited ${(!user).username}")
        sender.sendMessage("${player.username} invited you!")

    }

    syntax(list) {
        if (!CrewManager.hasCrew(player)) {
            player.sendMessage("You are not in a crew!")
            return@syntax
        }

        val crew = CrewManager.getCrew(player) ?: return@syntax

        player.sendMessage("Members: " + crew.members.joinToString(", ") { it.username })
    }

    syntax(disband) {

        if (!CrewManager.hasCrew(player)) {
            player.sendMessage("You are not in a crew!")
            return@syntax
        }

        CrewManager.disbandCrew(player)

    }

    syntax(accept, user) {

    }

}, "crew")