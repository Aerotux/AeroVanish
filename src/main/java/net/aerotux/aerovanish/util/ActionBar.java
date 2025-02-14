package net.aerotux.aerovanish.util;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ActionBar {

    public static void send(Player player, String string) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(string));
    }

    public static void send(String string) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            send(player, string);
        }
    }

}
