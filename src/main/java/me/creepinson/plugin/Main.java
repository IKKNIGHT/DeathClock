package me.creepinson.plugin;

import me.creepinson.plugin.command.GetTime;
import me.creepinson.plugin.command.GiveTime;
import me.creepinson.plugin.command.Status;
import me.creepinson.plugin.listener.PlayerDeath;
import me.creepinson.plugin.listener.PlayerJoined;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static me.creepinson.plugin.utils.DeathClockUtils.getTime;
import static me.creepinson.plugin.utils.DeathClockUtils.hasExpired;

/**
 * @author Creepinson101
 * <p>
 * This is a Spigot Template for complete beginners. Feel free to use it
 * in any of your plugins! This template includes a few utilities made
 * both by me, and some other programmers out there.
 */
public class Main extends JavaPlugin {

    // Feel free to change this to your own plugin's name and color of your choice.
    public static final String CHAT_PREFIX = ChatColor.BLACK + "[Death Clock]";
    public static final String DIALOG = Main.CHAT_PREFIX + ChatColor.WHITE + " > ";

    private static Main plugin; // This is a static plugin instance that is private. Use getPlugin() as seen
    // further below.

    PluginDescriptionFile pdfFile; // plugin.yml

    public void onDisable() {
        plugin = getPlugin(Main.class);
        this.pdfFile = getDescription();

    }

    public static Main getPlugin() { // getter for the static plugin instance
        return plugin;
    }

    public PluginDescriptionFile getPdfFile() {
        return pdfFile;
    }

    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        this.pdfFile = getDescription();
        /*
         * Register a command to the list of usable commands. If you don't register the
         * command, it won't work! Also if you change the command name, make sure to
         * also change in the plugin.yml file.
         */
        //create a voidless worldcreator

        // create another world which is voidless



        this.getCommand("givetime").setExecutor(new GiveTime());
        this.getCommand("status").setExecutor(new Status());
        this.getCommand("time").setExecutor(new GetTime());

        /*
         * Make sure you register your listeners if you have any! If you have a class
         * that implements Listener, you need to make sure to register it. Otherwise it
         * will DO NOTHING!
         */
        this.getServer().getPluginManager().registerEvents(new PlayerJoined(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        /*
         * This line lets you send out information to the console. In this case it would
         * say: Template-Plugin - Version 1.0.0 - has been enabled!
         */
        this.getLogger()
                .info(this.pdfFile.getName() + " - Version " + this.pdfFile.getVersion() + " - has been enabled!");

        // run code as long as server active
        int timeInSeconds = 1;
        int timeInTicks = 20 * timeInSeconds;
        new BukkitRunnable() {

            @Override
            public void run() {
                //The code inside will be executed in {timeInTicks} ticks.
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (hasExpired(p)) {
                        // put players in another world

                        Bukkit.getBanList(BanList.Type.NAME).addBan(String.valueOf(p.getUniqueId()), "Clock Ran Out", null, null);
                    }
                    // set actionbar
                    Long Frt = Instant.now().until(getTime(p), ChronoUnit.MILLIS);
                    Duration duration = Duration.ofMillis(Frt);

                    long h = duration.toHours();
                    long m = duration.toMinutes() % 60;
                    long s = duration.getSeconds() % 60;

                    String timeInHms = String.format("%02d:%02d:%02d", h, m, s);
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(timeInHms));
                }

            }
        }.runTaskTimer(JavaPlugin.getPlugin(Main.class), 1, timeInTicks);   // every 20 ticks we do this!! AKA 1 second


    }

}
