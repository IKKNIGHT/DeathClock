package me.creepinson.plugin.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import static me.creepinson.plugin.Main.DIALOG;
import static me.creepinson.plugin.utils.DeathClockUtils.addTime;
import static me.creepinson.plugin.utils.DeathClockUtils.subtractTime;

public class PlayerDeath implements Listener {
    @EventHandler
    public void PlayerDeathEvent(PlayerDeathEvent event) {
        Player p = event.getEntity();
        subtractTime(p, 1);
        p.sendMessage(DIALOG + ChatColor.DARK_RED + "You Died... this means you have lost one hour of your life!");
        Entity n = event.getEntity().getKiller();
        if (n instanceof Player np) {
            if (!np.equals(p)) { // check if suicide kill
                addTime(np, 1);
                np.sendMessage(DIALOG + ChatColor.GREEN + "You killed someone, you get 1 Hour!");
            }
        }
    }
}
