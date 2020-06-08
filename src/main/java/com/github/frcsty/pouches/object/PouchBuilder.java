package com.github.frcsty.pouches.object;

import com.github.frcsty.pouches.utils.Color;
import me.mattstudios.mfgui.gui.components.ItemNBT;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PouchBuilder {

    public static ItemStack getItem(final PouchStorage storage, final String name, final int amount) {
        final Pouch pouch = storage.getPouch(name);

        if (pouch == null) {
            return null;
        }

        ItemStack item = new ItemStack(pouch.getMaterial(), amount);
        final ItemMeta meta = item.getItemMeta();

        if (pouch.getDisplay() != null) {
            meta.setDisplayName(Color.colorize(pouch.getDisplay()));
        }

        if (!pouch.getLore().isEmpty()) {
            meta.setLore(Color.colorize(pouch.getLore()));
        }

        item.setItemMeta(meta);

        item = ItemNBT.setNBTTag(item, "pouch", name);

        return item;
    }
}
