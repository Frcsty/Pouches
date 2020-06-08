package com.github.frcsty.pouches.command;

import com.github.frcsty.pouches.Pouches;
import com.github.frcsty.pouches.listener.events.PouchGiveEvent;
import com.github.frcsty.pouches.object.PouchStorage;
import com.github.frcsty.pouches.utils.Color;
import com.github.frcsty.pouches.utils.Replace;
import me.mattstudios.mf.annotations.*;
import me.mattstudios.mf.base.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import static com.github.frcsty.pouches.object.PouchBuilder.getItem;

@SuppressWarnings("unused")
@Command("frozenpouch")
public class GivePouchCommand extends CommandBase {

    private final Pouches plugin;
    private final FileConfiguration config;

    public GivePouchCommand(final Pouches plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    @Default
    @Permission("pouches.commands.reload")
    public void reloadPlugin(final CommandSender sender) {
        final long startTime = System.currentTimeMillis();
        final PouchStorage pouchStorage = plugin.getPouchStorage();

        pouchStorage.getPouchHashMap().clear();
        new BukkitRunnable() {
            @Override
            public void run() {
                plugin.reloadConfig();
                pouchStorage.loadPouches(plugin);
            }
        }.runTaskAsynchronously(plugin);

        final String estimatedTime = String.valueOf(System.currentTimeMillis() - startTime);
        sender.sendMessage(Color.colorize(Replace.replaceString(config.getString("messages.reload-plugin"), "{time}", estimatedTime)));
    }

    @SubCommand("give")
    @Permission("pouches.commands.give")
    public void givePouch(final CommandSender sender, @Completion("#pouches") final String pouch, final String player, @Completion("#range:64") final Integer amount) {
        final Player target = Bukkit.getPlayerExact(player);
        final ItemStack item = getItem(plugin.getPouchStorage(), pouch, amount);

        if (item == null) {
            sender.sendMessage(Color.colorize(Replace.replaceString(config.getString("messages.invalid-pouch"), "{type}", pouch)));
            return;
        }

        if (target == null) {
            sender.sendMessage(Color.colorize(config.getString("messages.invalid-target")));
            return;
        }

        if (!target.isOnline()) {
            sender.sendMessage(Color.colorize(Replace.replaceString(config.getString("messages.offline-target"), "{target-name}", target.getName())));
            return;
        }

        if (target.getInventory().firstEmpty() == -1) {
            target.sendMessage(Color.colorize(config.getString("messages.target-full-inventory")));
            target.getWorld().dropItem(target.getLocation(), item);
        } else {
            target.getInventory().addItem(item);
        }

        Bukkit.getPluginManager().callEvent(new PouchGiveEvent(target, plugin.getPouchStorage().getPouch(pouch), amount));
        target.sendMessage(Color.colorize(Replace.replaceString(config.getString("messages.target-receive-pouch"), "{amount}", String.valueOf(amount), "{type}", pouch)));
    }
}
