package net.aerotux.aerovanish.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import net.aerotux.aerovanish.command.util.VanishUtil;
import org.bukkit.entity.Player;

@CommandAlias("vanish|v")
@CommandPermission("vanish.use")
public class VanishCommand extends BaseCommand {

    @Default
    public void main(Player player) {

        VanishUtil.vanish(player);

    }

}
