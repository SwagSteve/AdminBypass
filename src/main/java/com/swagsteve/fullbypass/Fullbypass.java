package com.swagsteve.fullbypass;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.security.Permission;

public final class Fullbypass extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Enable Message
        System.out.println("[AB] Enabling!");

        // Register Events
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Disable Message
        System.out.println("[AB] Disabling!");
    }


    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        if (e.getResult().equals(PlayerLoginEvent.Result.KICK_FULL)) {
            Player p = e.getPlayer();
            if (p.isOp() || p.hasPermission("adminbypass.bypass")) {
                e.allow();
            }
        }
    }
}
