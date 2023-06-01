package me.creepinson.plugin.command;

import me.creepinson.plugin.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import static me.creepinson.plugin.Main.CHAT_PREFIX;
import static me.creepinson.plugin.Main.DIALOG;

// All command classes need to implement the CommandExecutor interface to be a proper command!
public class Status implements CommandExecutor {

    /* Called when the command is ran
    args variable is the commands arguments in an array of strings.
    sender variable is the sender who ran the command
    */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(DIALOG + ChatColor.DARK_RED + "You cannot run this command from the console! Bozo admin get dunked on");
            return false;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage(DIALOG + ChatColor.GREEN + "UP!");
            return true;
        }
        player.sendMessage(DIALOG + ChatColor.DARK_RED + "Some wierd error happened, I have no idea how to fix it either lmao do /status");
        return false;
    }

}
