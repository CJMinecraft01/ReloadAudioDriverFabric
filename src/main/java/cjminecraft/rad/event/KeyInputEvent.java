package cjminecraft.rad.event;

import com.mojang.blaze3d.platform.InputConstants;

/**
 * This event fires when a keyboard input is detected.
 */
public class KeyInputEvent {

    private final int key;
    private final int scanCode;
    private final int action;
    private final int modifiers;

    public KeyInputEvent(int key, int scanCode, int action, int modifiers){

        this.key = key;
        this.scanCode = scanCode;
        this.action = action;
        this.modifiers = modifiers;
    }

    /**
     * Integer representing the key's action.
     *
     * @see InputConstants#PRESS
     * @see InputConstants#RELEASE
     * @see InputConstants#REPEAT
     */
    public int getAction(){
        return action;
    }

    /**
     * The keyboard key that triggered this event.
     * https://www.glfw.org/docs/latest/group__keys.html
     *
     * @see InputConstants key constants starting with "GLFW_KEY_"
     */
    public int getKey() {
        return key;
    }

    /**
     * Bit field representing the modifier keys pressed.
     *
     * @see InputConstants#MOD_CONTROL
     */
    public int getModifiers() {
        return modifiers;
    }

    /**
     * Platform-specific scan code.
     * Used for {@link InputConstants#getKey(int, int)}
     *
     * The scan code is unique for every key, regardless of whether it has a key code.
     * Scan codes are platform-specific but consistent over time, so keys will have different scan codes depending
     * on the platform but they are safe to save to disk as custom key bindings.
     */
    public int getScanCode() {
        return scanCode;
    }
}
