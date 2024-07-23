package net.zoplax.zoplax.placeholderapi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.zoplax.zoplax.Zoplax;
import net.zoplax.zoplax.command.vanish.util.VanishUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Expansion extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "Zoplax";
    }

    @Override
    public @NotNull String getAuthor() {
        return String.join(", ", Bukkit.getPluginManager().getPlugin(Zoplax.getInstance().getName()).getDescription().getAuthors());
    }

    @Override
    public @NotNull String getVersion() {
        return Bukkit.getPluginManager().getPlugin(Zoplax.getInstance().getName()).getDescription().getVersion();
    }

    @Override
    public boolean canRegister(){
        return true;
    }

    @Override
    public boolean persist(){
        return true;
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, String params){
        if(player == null){
            return "";
        }
        if(params == null){
            return "";
        }
        if (params.equals("vanish_tag")) {
            return VanishUtil.requestTag(player);
        }
        return null;
    }

}
