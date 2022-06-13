package cjminecraft.rad;

import cjminecraft.rad.event.KeyInputEvent;
import cjminecraft.rad.event.KeyInputEventHandler;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.network.chat.Component;

public class ReloadAudioDrivers implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		initClient();
	}

	@Environment(EnvType.CLIENT)
	private static void initClient(){
		KeyInputEventHandler.EVENT.register(ReloadAudioDrivers::onKeyInputEvent);
	}

	private static void onKeyInputEvent(KeyInputEvent event) {
		if(event.getAction() != InputConstants.PRESS) return;
		Minecraft client = Minecraft.getInstance();
		if(client.screen == null){
			if(InputConstants.isKeyDown(client.getWindow().getWindow(), InputConstants.KEY_F3)){
				if (event.getKey() == InputConstants.KEY_R){
					client.keyboardHandler.handledDebugKey = true;
					client.getSoundManager().soundEngine.reload();
					showToast();
				}else if (event.getKey() == InputConstants.KEY_Q){
					client.gui.getChat().addMessage(Component.translatable("reload_audio_driver.details"));
				}
			}
		}
	}

	private static void showToast(){
		SystemToast.add(
				Minecraft.getInstance().getToasts(),
				SystemToast.SystemToastIds.TUTORIAL_HINT,
				Component.translatable("reload_audio_driver.toast.title"),
				Component.translatable("reload_audio_driver.toast.body.success")
		);
	}
}
