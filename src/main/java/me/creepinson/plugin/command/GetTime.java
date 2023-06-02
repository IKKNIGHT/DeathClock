package me.creepinson.plugin.command;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;

import static me.creepinson.plugin.Main.DIALOG;
import static me.creepinson.plugin.utils.DeathClockUtils.getTime;

public class GetTime implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {

        if (sender instanceof ConsoleCommandSender) {

            sender.sendMessage(DIALOG + ChatColor.DARK_RED + "You cannot run this command from the console! Bozo admin get dunked on");
            return false;
        }
        Player p = (Player) sender;
        if (args.length == 0) {
            Long Frt = Instant.now().until(getTime(p), ChronoUnit.MILLIS);
            Duration duration = Duration.ofMillis(Frt);

            long h = duration.toHours();
            long m = duration.toMinutes() % 60;
            long s = duration.getSeconds() % 60;

            String timeInHms = String.format("%02d:%02d:%02d", h, m, s);
            p.sendMessage(DIALOG+ChatColor.YELLOW + "The amount of time you have right now is: " + timeInHms);
            return true;
        }
        p.sendMessage(DIALOG + ChatColor.DARK_RED + "Some wierd error happened, I have no idea how to fix it either lmao do /time");


        return false;
    }
}
