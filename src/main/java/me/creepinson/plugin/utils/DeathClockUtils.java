package me.creepinson.plugin.utils;

import me.creepinson.plugin.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import me.creepinson.plugin.listener.PlayerJoined;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static me.creepinson.plugin.Main.getPlugin;

public class DeathClockUtils {
    private static NamespacedKey key = new NamespacedKey(JavaPlugin.getProvidingPlugin(Main.class), "death_clock");

    public static boolean hasExpired(Player player) {

        Instant expired = Instant.ofEpochMilli(player.getPersistentDataContainer().get(PlayerJoined.key, PersistentDataType.LONG));
        return Instant.now().isAfter(expired);
    }
    /**
     * Set the initial Death Clock on this player.
     *
     * @param player the Player to set the Death Clock on.
     * @param hours the hours from now for the Death Clock.
     */
    public static void setClock(Player player, long hours) {

        Instant expires = Instant.now().plus(hours, ChronoUnit.HOURS);
        player.getPersistentDataContainer().set(key, PersistentDataType.LONG, expires.toEpochMilli());
    }
    public static void addTime(Player player, long hours) {

        PersistentDataContainer pdc = player.getPersistentDataContainer();
        Instant expires = Instant.ofEpochMilli(pdc.getOrDefault(key, PersistentDataType.LONG, Instant.now().toEpochMilli()));
        expires.plus(hours, ChronoUnit.HOURS);
        pdc.set(key, PersistentDataType.LONG, expires.toEpochMilli());
    }

    /**
     * Subtract time from a Players Deathclock.
     *
     * @param player the Player to remove time from.
     * @param hours the number of hours to remove.
     */
    public static void subtractTime(Player player, long hours) {

        PersistentDataContainer pdc = player.getPersistentDataContainer();
        Instant expires = Instant.ofEpochMilli(pdc.getOrDefault(key, PersistentDataType.LONG, Instant.now().toEpochMilli()));
        expires.minus(hours, ChronoUnit.HOURS);
        pdc.set(key, PersistentDataType.LONG, expires.toEpochMilli());
    }

    public static Instant getTime(Player player) {

        PersistentDataContainer pdc = player.getPersistentDataContainer();
        Instant expires = Instant.ofEpochMilli(pdc.getOrDefault(key, PersistentDataType.LONG, Instant.now().toEpochMilli()));
        return expires;
    }

}
