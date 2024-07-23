package net.aerotux.aerovanish.command.util;

import net.aerotux.aerovanish.AeroVanish;
import net.aerotux.aerovanish.util.ActionBar;
import net.aerotux.aerovanish.util.CC;
import net.aerotux.aerovanish.util.YamlManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class VanishUtil {

    private static Set<Player> vanished = new HashSet<>();
    private static AeroVanish instance = AeroVanish.getInstance();
    private static YamlManager yamlManager = new YamlManager(instance, "vanishedplayers.yml");

    public static void initialize() {
        getYamlManager().getVanishedPlayers().forEach(playerName -> {
            Player player = Bukkit.getPlayer(playerName);
            if (player != null) {
                vanish(player, false);
            }
        });
    }

    public static void saveVanishedPlayers() {
        getYamlManager().setVanishedPlayers(vanished.stream().map(Player::getName).collect(Collectors.toList()));
    }

    public static void vanish(Player player, boolean updateFile) {
        String unVanishJoinMessage = CC.translate(instance.getConfig().getString("unvanished-join-message"))
                .replace("{player}", player.getName());
        String vanishLeaveMessage = CC.translate(instance.getConfig().getString("vanish-leave-message"))
                .replace("{player}", player.getName());

        if (vanished.contains(player)) {
            vanished.remove(player);
            player.sendMessage(CC.translate(instance.getConfig().getString("unvanished-message")));
            for (Player on : Bukkit.getOnlinePlayers()) {
                on.showPlayer(instance, player);
                if (instance.getConfig().getBoolean("vanish-unvanish-leave-join-messages")) {
                    on.sendMessage(unVanishJoinMessage);
                }
            }
        } else {
            vanished.add(player);
            player.sendMessage(CC.translate(instance.getConfig().getString("vanish-message")));
            for (Player on : Bukkit.getOnlinePlayers()) {
                on.hidePlayer(instance, player);
                if (instance.getConfig().getBoolean("vanish-unvanish-leave-join-message")) {
                    on.sendMessage(vanishLeaveMessage);
                }
            }

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (vanished.contains(player)) {
                        if (instance.getConfig().getBoolean("vanished-actionbar")) {
                            ActionBar.send(player, CC.translate(instance.getConfig().getString("vanished-actionbar-message")));
                        }
                    } else {
                        cancel();
                    }
                }
            }.runTaskTimerAsynchronously(instance, 0, 20);
        }

        for (Player bypass : Bukkit.getOnlinePlayers()) {
            if (bypass.hasPermission("aerovanish.bypass")) {
                if (vanished.contains(player)) {
                    bypass.showPlayer(instance, player);
                }
            }
        }

        if (updateFile) {
            saveVanishedPlayers();
        }
    }

    public static void vanish(Player player) {
        vanish(player, true);
    }

    public static String requestTag(Player player) {
        if (vanished.contains(player)) {
            if (instance.getConfig().getBoolean("vanished-nametag-suffix")) {
                return CC.translate(instance.getConfig().getString("vanished-suffix"));
            } else {
                return "";
            }
        }
        return "";
    }

    public static YamlManager getYamlManager() {
        return yamlManager;
    }
}
