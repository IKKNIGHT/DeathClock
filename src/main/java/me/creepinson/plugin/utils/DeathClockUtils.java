package me.creepinson.plugin.utils;

import me.creepinson.plugin.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DeathClockUtils {

    private static final NamespacedKey key = new NamespacedKey(JavaPlugin.getProvidingPlugin(Main.class), "death_clock");
    private static final NamespacedKey pause = new NamespacedKey(JavaPlugin.getProvidingPlugin(Main.class), "pausekey");

    public static boolean hasExpired(Player player) {

        Instant expired = Instant.ofEpochMilli(player.getPersistentDataContainer().get(key, PersistentDataType.LONG));
        return Instant.now().isAfter(expired);
    }

    /**
     * Set the initial Death Clock on this player.
     *
     * @param player the Player to set the Death Clock on.
     * @param hours  the hours from now for the Death Clock.
     */
    public static void setClock(Player player, long hours) {

        Instant expires = Instant.now().plus(hours, ChronoUnit.HOURS);
        player.getPersistentDataContainer().set(key, PersistentDataType.LONG, expires.toEpochMilli());
    }

    public static void addTime(Player player, long hours) {

        PersistentDataContainer pdc = player.getPersistentDataContainer();
        Instant expires = Instant.ofEpochMilli(pdc.getOrDefault(key, PersistentDataType.LONG, Instant.now().toEpochMilli()));
        expires = expires.plus(hours, ChronoUnit.HOURS);
        pdc.set(key, PersistentDataType.LONG, expires.toEpochMilli());
    }

    /**
     * Subtract time from a Players Deathclock.
     *
     * @param player the Player to remove time from.
     * @param hours  the number of hours to remove.
     */
    public static void subtractTime(Player player, long hours) {

        PersistentDataContainer pdc = player.getPersistentDataContainer();
        Instant expires = Instant.ofEpochMilli(pdc.getOrDefault(key, PersistentDataType.LONG, Instant.now().toEpochMilli()));
        expires = expires.minus(hours, ChronoUnit.HOURS);
        pdc.set(key, PersistentDataType.LONG, expires.toEpochMilli());
    }

    public static Instant getTime(Player player) {

        PersistentDataContainer pdc = player.getPersistentDataContainer();
        Instant expires = Instant.ofEpochMilli(pdc.getOrDefault(key, PersistentDataType.LONG, Instant.now().toEpochMilli()));
        return expires;
    }

    public static void pauseTime(Player player) {

        PersistentDataContainer pdc = player.getPersistentDataContainer();
        pdc.set(pause, PersistentDataType.LONG, Instant.now().toEpochMilli());
    }

    public static void resumeTime(Player player) {

        //to unpause you read the value under "pause" then set key to key + (Instant.now() - pause)
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        Instant expires = Instant.ofEpochMilli(pdc.getOrDefault(key, PersistentDataType.LONG, Instant.now().toEpochMilli()));
        Instant paused = Instant.ofEpochMilli(pdc.getOrDefault(pause, PersistentDataType.LONG, Instant.now().toEpochMilli()));
        // paused.until(Instant.now())
        expires = expires.plus(paused.until(Instant.now(), ChronoUnit.MILLIS), ChronoUnit.MILLIS);
        pdc.set(key, PersistentDataType.LONG, expires.toEpochMilli());
    }


}
