package net.aerotux.aerovanish;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import net.aerotux.aerovanish.command.MainCommand;
import net.aerotux.aerovanish.command.VanishCommand;
import net.aerotux.aerovanish.command.util.VanishUtil;
import net.aerotux.aerovanish.placeholderapi.Expansion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("LombokGetterMayBeUsed")
public final class AeroVanish extends JavaPlugin implements Listener {

    public static String PREFIX = "&7[&bAV&7] ";

    @Getter private static AeroVanish instance;

    private PaperCommandManager commandManager;

    public static AeroVanish getInstance() {
        return instance;
    }

    public PaperCommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public void onEnable() {

        instance = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(this, this);

        VanishUtil.initialize();

        getLogger().info("Enabled!");
        getLogger().info("Please join the Discord if you encounter issues!");

        registerCommands();

        new Expansion().register();

    }

    @Override
    public void onDisable() {
        VanishUtil.saveVanishedPlayers();
    }

    private void registerCommands() {
        commandManager = new PaperCommandManager(this);

        commandManager.registerCommand(new MainCommand());
        commandManager.registerCommand(new VanishCommand());

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (VanishUtil.getYamlManager().getVanishedPlayers().contains(event.getPlayer().getName())) {
            VanishUtil.vanish(event.getPlayer(), false);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

    }

}
