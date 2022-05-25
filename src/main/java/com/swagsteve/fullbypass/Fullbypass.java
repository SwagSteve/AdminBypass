package com.swagsteve.fullbypass;

import Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Fullbypass extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        // Enable Message
        getLogger().info("Enabling!");

        //Config
        getConfig().options().copyDefaults();
        getConfig().addDefault("Options.Enable-Join-Bypass-Message", true);
        getConfig().addDefault("Options.Join-Bypass-Message", "&a&lYou Bypassed The Max Player Count! &r&online%/%maxcount%");
        saveDefaultConfig();

        // Register Events
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Disable Message
        getLogger().info("Disabling!");

        //Save Config
        saveConfig();
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        if (e.getResult().equals(PlayerLoginEvent.Result.KICK_FULL)) {
            Player p = e.getPlayer();
            if (p.isOp() || p.hasPermission("adminbypass.bypass")) {
                if (getConfig().getBoolean("Options.Enable-Join-Bypass-Message")) {
                    Integer onlineSize = Bukkit.getOnlinePlayers().size();
                    Integer maxSize = Bukkit.getMaxPlayers();
                    p.sendMessage(Utils.Color(getConfig().getString("Options.Join-Bypass-Message").replace("%online%", onlineSize.toString()).replace("%maxcount%", maxSize.toString())));
                }
                e.allow();
            }
        }
    }
}
