package net.aerotux.aerovanish.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import net.aerotux.aerovanish.AeroVanish;
import net.aerotux.aerovanish.util.CC;
import org.bukkit.entity.Player;
import org.checkerframework.checker.formatter.qual.ConversionCategory;

@CommandAlias("aerovanish|av")
@CommandPermission("aerovanish.admin")
public class MainCommand extends BaseCommand {

    @Default
    public void main(Player player) {

        player.sendMessage(CC.translate("&cUse: /aerovanish [argument]"));

    }

    @Subcommand("reload")
    @CommandPermission("aerovanish.admin")
    public void reload(Player player) {

        player.sendMessage(CC.translate("&aYou have reloaded AeroVanish by Aerotux!"));
        AeroVanish.getInstance().reloadConfig();

    }

}
