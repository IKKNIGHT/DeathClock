package me.creepinson.plugin.command;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static me.creepinson.plugin.Main.DIALOG;
import static me.creepinson.plugin.utils.DeathClockUtils.*;

public class GiveTime implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(DIALOG + ChatColor.DARK_RED + "You cannot run this command from the console! Bozo admin get dunked on");
            return false;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage(DIALOG + ChatColor.RED + "Wrong Usage! Do /giveTime <Player> <Time - Hours>");
            return true;
        }
        if (args.length == 1) {
            player.sendMessage(DIALOG + ChatColor.RED + "Wrong Usage! Do /giveTime <Player> <Time - Hours>");
            return true;
        }
        // create code to give player time in hours using addTime method from DeathClockUtils
        if (args.length == 2) {
            Player target = player.getServer().getPlayer(args[0]);
            // player dose not have to be online
            if (target == null) {
                player.sendMessage(DIALOG + ChatColor.RED + "Player not found!");
                return true;
            }
            Long Frt = Instant.now().until(getTime(player), ChronoUnit.MILLIS);
            long h = Frt / 3600000;
            if (h >= Long.parseLong(args[1])) {
                subtractTime(player, Long.parseLong(args[1]));
                addTime(target, Long.parseLong(args[1]));
                player.sendMessage(DIALOG + ChatColor.GREEN + "You have given " + target.getName() + " " + args[1] + " hours of time!");
                target.sendMessage(DIALOG + ChatColor.GREEN + "You have been given " + args[1] + " hours of time by " + player.getName());
                return true;
            } else {
                player.sendMessage(DIALOG + ChatColor.RED + "You do not have enough time to give!");
                return true;
            }

        }


        player.sendMessage(DIALOG + ChatColor.DARK_RED + "Some wierd error happened, I have no idea how to fix it either lmao do /giveTime <Player> Time");
        return false;
    }
}
