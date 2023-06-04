package me.creepinson.plugin.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import static me.creepinson.plugin.utils.DeathClockUtils.pauseTime;

public class PlayerLeft implements Listener {
    @EventHandler
    public void PlayerLeftEvent(PlayerQuitEvent event) {
        // get time and save in pdc
        Player p = event.getPlayer();
        pauseTime(p);
    }
}
