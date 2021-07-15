package fr.cursor.formmaker;

import fr.cursor.formmaker.commands.ItemCommand;
import fr.cursor.formmaker.commands.SetCommand;
import fr.cursor.formmaker.listeners.OnClick;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class FormMaker extends JavaPlugin {

    private static FormMaker instance;
    private final HashMap<Player, Location> firstLocation = new HashMap<>();
    private final HashMap<Player, Location> secondLocation = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        System.out.println("[FormMaker] - Plugin allumé.");
        getCommand("util").setExecutor(new ItemCommand());
        getCommand("set").setExecutor(new SetCommand());

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new OnClickListener(), this);
    }

    public static FormMaker getInstance() {
        return instance;
    }

    public String getPath(String path) {
        return instance.getConfig().getString(path).replace("&", "§");
    }

    public Location getFirstLocation(Player player) {return firstLocation.get(player);}
    public Location getSecondLocation(Player player) {return secondLocation.get(player);}

    public void setFirstLocation(Player player, Location location) {firstLocation.put(player, location);}
    public void setSecondLocation(Player player, Location location) {secondLocation.put(player, location);}

}
