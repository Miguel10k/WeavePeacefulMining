package me.lily.peacefulmining.mixin

import me.lily.peacefulmining.Mod
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.EntityRenderer
import net.minecraft.item.ItemAxe
import net.minecraft.item.ItemPickaxe
import net.minecraft.item.ItemShears
import net.minecraft.item.ItemSpade
import net.minecraft.item.ItemHoe
import net.minecraft.util.MovingObjectPosition
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Redirect
import org.lwjgl.input.Mouse

@Mixin(EntityRenderer::class)
class MixinEntityRenderer {

    @Redirect(
        method = ["getMouseOver"],
        at = At(
            value = "INVOKE",
            target = "Ljava/util/List;size()I"
        )
    )
    fun skipEntityLoop(instance: List<*>?): Int {
        val minecraft = Minecraft.getMinecraft()
        val player = minecraft.thePlayer

        if (Mod.enabled && minecraft.objectMouseOver != null) {
            val typeOfHit = minecraft.objectMouseOver.typeOfHit
            val isRightClick = Mouse.isButtonDown(1)

            if ((typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && !isRightClick) ||
                (typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && isRightClick)) {
                if (player?.heldItem?.item is ItemPickaxe ||
                    player?.heldItem?.item is ItemShears ||
                    player?.heldItem?.item is ItemAxe ||
                    player?.heldItem?.item is ItemHoe ||
                    player?.heldItem?.item is ItemSpade) {
                    return 0
                }
            }
        }
        return instance!!.size
    }
}
