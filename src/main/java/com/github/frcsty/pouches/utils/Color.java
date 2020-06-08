package com.github.frcsty.pouches.utils;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class Color {

    public static String colorize(String message)
    {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> colorize(List<String> message)
    {
        return message.stream().map(Color::colorize).collect(Collectors.toList());
    }
}
