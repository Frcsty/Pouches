package com.github.frcsty.pouches.object;

import com.github.frcsty.pouches.Pouches;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PouchStorage {

    private final Map<String, Pouch> pouchHashMap = new HashMap<>();

    private void setPouch(final String name, final Pouch pouch) {
        this.pouchHashMap.put(name, pouch);
    }

    public final Pouch getPouch(final String name) {
        return this.pouchHashMap.get(name);
    }

    public final Map<String, Pouch> getPouchHashMap() { return this.pouchHashMap; }

    public final void loadPouches(final Pouches plugin) {
        final ConfigurationSection pouchesSection = plugin.getConfig().getConfigurationSection("pouches");
        if (pouchesSection == null) {
            return;
        }

        for (String pouchString : pouchesSection.getKeys(false)) {
            final ConfigurationSection pouchItemSection = plugin.getConfig().getConfigurationSection("pouches." + pouchString + ".item");
            final ConfigurationSection pouchSection = plugin.getConfig().getConfigurationSection("pouches." + pouchString);
            if (pouchItemSection == null || pouchSection == null) {
                continue;
            }

            final String materialString = pouchItemSection.getString("material");
            Validate.notNull(materialString);
            final Material material = Material.matchMaterial(materialString);

            final String display = pouchItemSection.getString("display");
            final List<String> lore = pouchItemSection.getStringList("lore");
            final boolean glow = pouchItemSection.getBoolean("glow");

            final String range = pouchSection.getString("range");
            final List<String> actions = pouchSection.getStringList("open-actions");

            setPouch(pouchString, new Pouch(pouchString, material, display, lore, glow, range, actions));
        }
    }
}
