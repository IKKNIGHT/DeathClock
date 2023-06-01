package me.creepinson.plugin.listener;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import static me.creepinson.plugin.Main.DIALOG;

public class PlayerJoined implements Listener {
    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if(!p.hasPlayedBefore()){
            p.sendMessage(DIALOG + ChatColor.GREEN + "Welcome to the server!");
        }else{
            p.sendMessage(DIALOG + ChatColor.GREEN + "Welcome Back!");
        }
    }
}
