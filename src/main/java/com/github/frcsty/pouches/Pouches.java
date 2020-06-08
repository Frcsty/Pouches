package com.github.frcsty.pouches;

import com.github.frcsty.pouches.command.GivePouchCommand;
import com.github.frcsty.pouches.listener.PouchOpenListener;
import com.github.frcsty.pouches.object.PouchStorage;
import com.github.frcsty.pouches.utils.Actions;
import com.github.frcsty.pouches.utils.lib.ActionManager;
import me.mattstudios.mf.base.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Pouches extends JavaPlugin {

    private final PouchStorage pouchStorage = new PouchStorage();
    private final Actions actions = new Actions();
    private final ActionManager actionManager = new ActionManager(this);

    @Override
    public void onEnable() {
        saveDefaultConfig();

        final CommandManager commandManager = new CommandManager(this);

        commandManager.getCompletionHandler().register("#pouches", input -> {
            return new ArrayList<>(getConfig().getConfigurationSection("pouches").getKeys(false));
        });

        commandManager.register(new GivePouchCommand(this));
        actionManager.loadDefaults();
        actions.loadCustomActions(actionManager);

        getServer().getPluginManager().registerEvents(new PouchOpenListener(this), this);
        pouchStorage.loadPouches(this);
    }

    @Override
    public void onDisable() {
        reloadConfig();
    }

    public final PouchStorage getPouchStorage() {
        return this.pouchStorage;
    }

    public final ActionManager getActionManager() {
        return this.actionManager;
    }

}
