package com.darkm4tter.fmobs;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class FMobs extends JavaPlugin implements Listener {

    private String VERSION = "1.2.0";
    private Random r = new Random();

    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getServer().getConsoleSender().sendMessage("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Bukkit.getServer().getConsoleSender().sendMessage("Developed for protocolmc.com");
        Bukkit.getServer().getConsoleSender().sendMessage("Version " + VERSION);
        Bukkit.getServer().getConsoleSender().sendMessage("FMobs is now enabled!");
        Bukkit.getServer().getConsoleSender().sendMessage("~~~~~~~~~~~~~~~~[FT]~~~~~~~~~~~~~~~~");
    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getScheduler().cancelTasks(this);
        Bukkit.getServer().getConsoleSender().sendMessage("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Bukkit.getServer().getConsoleSender().sendMessage("Developed for protocolmc.com");
        Bukkit.getServer().getConsoleSender().sendMessage("Version " + VERSION);
        Bukkit.getServer().getConsoleSender().sendMessage("FMobs is now disabled");
        Bukkit.getServer().getConsoleSender().sendMessage("~~~~~~~~~~~~~~~~[FT]~~~~~~~~~~~~~~~~");
    }

    @EventHandler
    public void FGolem(CreatureSpawnEvent e) {
        if (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER && e.getEntityType() == EntityType.IRON_GOLEM) {
            e.getEntity().setHealth(2.0D);
            e.getEntity().setFireTicks(10000);
        }
    }

    @EventHandler
    public void FDeath(EntityDeathEvent e){
        if(e.getEntityType() == EntityType.VILLAGER) {
            e.getDrops().clear();
            e.getDrops().add(new ItemStack(Material.EMERALD));
            int i = r.nextInt(5);
            if(i <= 2)
                e.getDrops().add(new ItemStack(Material.DIAMOND));
            else if(i <= 4){
                e.getDrops().add(new ItemStack(Material.DIAMOND));
                e.getDrops().add(new ItemStack(Material.IRON_INGOT));
            }
            else{
                e.getDrops().add(new ItemStack(Material.DIAMOND));
                e.getDrops().add(new ItemStack(Material.DIAMOND));
            }
        }
        else if(e.getEntityType() == EntityType.IRON_GOLEM) {
            e.getDrops().clear();
            int num = r.nextInt(2)+3;
            for(int i = 0; i < num; i++){
                e.getDrops().add(new ItemStack(Material.IRON_INGOT));
            }
        }
        else if(e.getEntityType() == EntityType.PIG_ZOMBIE){
            e.getDrops().clear();
            int num = r.nextInt(3);
            if(num == 0)
                e.getDrops().add(new ItemStack(Material.GOLD_INGOT));
        }
    }
}
