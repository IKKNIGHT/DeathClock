package me.creepinson.plugin.listener;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static me.creepinson.plugin.Main.DIALOG;
import static me.creepinson.plugin.utils.DeathClockUtils.*;


public class PlayerJoined implements Listener {

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if (!p.hasPlayedBefore()) {
            p.sendMessage(DIALOG + ChatColor.GREEN + "Welcome to the server!");
            // set time
            setClock(p, 10);

        } else {
            p.sendMessage(DIALOG + ChatColor.GREEN + "Welcome Back!");
            resumeTime(p);
        }
        // add actionbar titles
        Long Frt = Instant.now().until(getTime(p), ChronoUnit.MILLIS);
        Duration duration = Duration.ofMillis(Frt);

        long h = duration.toHours();
        long m = duration.toMinutes() % 60;
        long s = duration.getSeconds() % 60;

        String timeInHms = String.format("%02d:%02d:%02d", h, m, s);
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(timeInHms));
    }

}
