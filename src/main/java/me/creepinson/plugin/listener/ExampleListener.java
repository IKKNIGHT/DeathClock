package me.creepinson.plugin.listener;

import me.creepinson.plugin.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class ExampleListener implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player p = event.getPlayer();
		if (!p.hasPermission("template.breakblocks")) {
			p.sendMessage(Main.CHAT_PREFIX +  ChatColor.WHITE + " > " + ChatColor.RED + "You do not have permission to break blocks!");
			event.setCancelled(true);
		}
		//copy and paste me for free code :O EPIC
	}

}
