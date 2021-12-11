package world.cepi.crews.commands

import world.cepi.kepi.messages.sendFormattedTranslatableMessage
import world.cepi.kstom.command.kommand.Kommand

object PartyFauxCommand : Kommand({
    default {
        sender.sendFormattedTranslatableMessage("crews", "party.default")
    }
}, "party")