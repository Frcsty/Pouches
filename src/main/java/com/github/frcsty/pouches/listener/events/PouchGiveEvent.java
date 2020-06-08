package com.github.frcsty.pouches.listener.events;

import com.github.frcsty.pouches.object.Pouch;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@SuppressWarnings("unused")
public class PouchGiveEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player target;
    private Pouch pouch;
    private int amount;

    public PouchGiveEvent(final Player target, final Pouch pouch, final int amount) {
        this.target = target;
        this.pouch = pouch;
        this.amount = amount;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public final Player getTarget() {
        return this.target;
    }

    public final Pouch getPouch() {
        return this.pouch;
    }

    public final int getAmount() {
        return this.amount;
    }

}
