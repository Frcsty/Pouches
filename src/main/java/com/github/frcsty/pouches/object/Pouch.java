package com.github.frcsty.pouches.object;

import org.bukkit.Material;

import java.util.List;

public class Pouch {

    private String name;
    private Material material;
    private String display;
    private List<String> lore;
    private boolean glow;
    private String range;
    private List<String> actions;

    public Pouch(final String name, final Material material, final String display, final List<String> lore, final boolean glow, final String range, final List<String> actions) {
        this.name = name;
        this.material = material;
        this.display = display;
        this.lore = lore;
        this.glow = glow;
        this.range = range;
        this.actions = actions;
    }

    public final String getName() {
        return name;
    }

    public final void setName(final String value) {
        this.name = value;
    }

    public final Material getMaterial() {
        return material;
    }

    public final void setMaterial(final Material value) {
        this.material = value;
    }

    public final String getDisplay() {
        return display;
    }

    public final void setDisplay(final String value) {
        this.display = value;
    }

    public final List<String> getLore() {
        return lore;
    }

    public final void setLore(final List<String> values) {
        this.lore = values;
    }

    public final boolean isGlow() {
        return this.glow;
    }

    public final void setGlow(final boolean value) {
        this.glow = value;
    }

    public final String getRange() {
        return range;
    }

    public final void setRange(final String value) {
        this.range = value;
    }

    public final List<String> getActions() {
        return this.actions;
    }

    public final void setActions(final List<String> values) {
        this.actions = values;
    }
}
