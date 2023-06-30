package me.lily.peacefulmining

import net.minecraft.client.Minecraft
import net.minecraft.util.ChatComponentText
import net.minecraft.util.EnumChatFormatting.GRAY
import net.weavemc.loader.api.ModInitializer
import net.weavemc.loader.api.command.Command
import net.weavemc.loader.api.command.CommandBus

class Mod : ModInitializer {

    companion object {
        var enabled = false
            set(value) {
                field = value
                Minecraft.getMinecraft().thePlayer?.addChatMessage(
                    ChatComponentText(
                        "${GRAY}Peaceful Mining has been ${
                            if (field) "enabled"
                            else "disabled"
                        }."
                    )
                )
            }
    }

    override fun preInit() {
        CommandBus.register(object : Command("peacefulmining") {
            override fun handle(args: Array<out String>) {
                enabled = !enabled
            }
        })
    }

}