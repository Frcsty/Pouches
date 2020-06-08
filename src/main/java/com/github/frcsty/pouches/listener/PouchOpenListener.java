package com.github.frcsty.pouches.listener;

import com.github.frcsty.pouches.Pouches;
import com.github.frcsty.pouches.object.Pouch;
import com.github.frcsty.pouches.utils.Replace;
import me.mattstudios.mfgui.gui.components.ItemNBT;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.SplittableRandom;

public class PouchOpenListener implements Listener {

    private final Pouches plugin;

    public PouchOpenListener(final Pouches plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPouchOpen(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final ItemStack item = event.getItem();
        final Action action = event.getAction();

        if (item == null) {
            return;
        }

        if (action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        final String pouchType = ItemNBT.getNBTTag(item, "pouch");

        if (pouchType == null) {
            return;
        }

        final Pouch pouch = plugin.getPouchStorage().getPouch(pouchType);

        if (pouch == null) {
            return;
        }

        if (item.getAmount() > 1) {
            item.setAmount(item.getAmount() - 1);
        } else {
            item.setType(Material.AIR);
        }

        player.getInventory().setItemInMainHand(item);
        final String[] range = pouch.getRange().split(";");
        final int amount = new SplittableRandom().nextInt(Integer.parseInt(range[0]), Integer.parseInt(range[1]));
        plugin.getActionManager().execute(player, Replace.replaceList(pouch.getActions(), "{amount}", String.valueOf(amount), "{player}", player.getName()));
    }
}
