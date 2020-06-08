package com.github.frcsty.pouches.listener.events;

import com.github.frcsty.pouches.object.Pouch;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@SuppressWarnings("unused")
public class PouchOpenEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Player player;
    private Pouch pouch;

    public PouchOpenEvent(final Player player, final Pouch pouch) {
        this.player = player;
        this.pouch = pouch;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public final Player getPlayer() { return this.player; }
    public final Pouch getPouch() { return this.pouch; }
}
