package cjminecraft.rad.mixin;

import cjminecraft.rad.event.KeyInputEvent;
import cjminecraft.rad.event.KeyInputEventHandler;

import net.minecraft.client.KeyboardHandler;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardHandler.class)
public class KeyboardListenerMixin {
    @Inject(at = @At("TAIL"), method = "keyPress")
    public void onKeyEvent(long windowPointer, int key, int scanCode, int action, int modifiers, CallbackInfo info){
        KeyInputEventHandler.EVENT.invoker().interact(new KeyInputEvent(key, scanCode, action, modifiers));
    }
}
