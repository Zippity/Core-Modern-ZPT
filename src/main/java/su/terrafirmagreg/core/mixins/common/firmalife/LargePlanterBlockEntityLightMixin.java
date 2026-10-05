package su.terrafirmagreg.core.mixins.common.firmalife;

import com.eerussianguy.firmalife.common.blockentities.LargePlanterBlockEntity;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = LargePlanterBlockEntity.class, remap = false)
public abstract class LargePlanterBlockEntityLightMixin {
    @ModifyReturnValue(method = "skylightValid", at = @At("RETURN"))
    private boolean skylightValidOverride(boolean original, @Local(argsOnly = true) BlockPos pos) {

        Level level = ((BlockEntity) (Object) this).getLevel();

        if (!original) {
            assert level != null;
            if (level.getBrightness(LightLayer.BLOCK, pos) >= level.getMaxLightLevel() - 5) {
                return true;
            }
        }

        return original;
    }
}
