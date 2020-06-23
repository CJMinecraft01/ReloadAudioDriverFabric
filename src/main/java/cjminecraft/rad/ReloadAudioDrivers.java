package cjminecraft.rad;

import cjminecraft.rad.event.KeyInputEvent;
import cjminecraft.rad.event.KeyInputEventHandler;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.TranslatableText;

import org.lwjgl.glfw.GLFW;

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
		if(event.getAction() != GLFW.GLFW_PRESS) return;
		MinecraftClient client = MinecraftClient.getInstance();
		if(client.currentScreen == null){
			if(InputUtil.isKeyPressed(client.getWindow().getHandle(), GLFW.GLFW_KEY_F3)){
				if (event.getKey() == GLFW.GLFW_KEY_R){
					client.keyboard.switchF3State = true;
					client.getSoundManager().soundSystem.reloadSounds();
					showToast();
				}else if (event.getKey() == GLFW.GLFW_KEY_Q){
					client.inGameHud.getChatHud().addMessage(new TranslatableText("reload_audio_driver.details"));
				}
			}
		}
	}

	private static void showToast(){
		SystemToast.show(
				MinecraftClient.getInstance().getToastManager(),
				SystemToast.Type.TUTORIAL_HINT,
				new TranslatableText("reload_audio_driver.toast.title"),
				new TranslatableText("reload_audio_driver.toast.body.success")
		);
	}
}
