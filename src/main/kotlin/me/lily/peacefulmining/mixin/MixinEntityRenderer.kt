package me.lily.peacefulmining.mixin

import me.lily.peacefulmining.Mod
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.EntityRenderer
import net.minecraft.item.ItemPickaxe
import net.minecraft.util.MovingObjectPosition
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Redirect

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
        if(Mod.enabled &&
            Minecraft.getMinecraft().objectMouseOver != null &&
            Minecraft.getMinecraft().objectMouseOver.typeOfHit
            == MovingObjectPosition.MovingObjectType.BLOCK &&
                Minecraft.getMinecraft().thePlayer?.heldItem?.getItem() is ItemPickaxe) {
            return 0
        }
        return instance!!.size
    }

}