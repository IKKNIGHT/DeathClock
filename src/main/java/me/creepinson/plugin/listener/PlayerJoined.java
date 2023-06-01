package me.creepinson.plugin.listener;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataType;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static me.creepinson.plugin.Main.DIALOG;
import static me.creepinson.plugin.Main.getPlugin;
import static me.creepinson.plugin.utils.DeathClockUtils.setClock;


public class PlayerJoined implements Listener {
    public static NamespacedKey key = new NamespacedKey(getPlugin(), "death_clock");
    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if(!p.hasPlayedBefore()){
            p.sendMessage(DIALOG + ChatColor.GREEN + "Welcome to the server!");
            // set time
            setClock(p,10);

        }else{
            p.sendMessage(DIALOG + ChatColor.GREEN + "Welcome Back!");
        }

    }

}
