package cjminecraft.rad.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface KeyInputEventHandler {
    Event<KeyInputEventHandler> EVENT = EventFactory.createArrayBacked(KeyInputEventHandler.class, (listeners) ->
            (keyEvent) -> {
                for (KeyInputEventHandler listener : listeners) {
                    listener.interact(keyEvent);
                }
            });

    void interact(KeyInputEvent event);
}
