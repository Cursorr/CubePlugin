package fr.cursor.formmaker.commands;

import fr.cursor.formmaker.FormMaker;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        FormMaker formMaker = FormMaker.getInstance();
        if (!(sender instanceof Player)) {
            System.out.println(formMaker.getPath("notConnectedToServer"));
            return false;
        }
        Player player = (Player) sender;
        Location firstLocation = formMaker.getFirstLocation(player);
        Location secondLocation = formMaker.getSecondLocation(player);
        if (firstLocation == null || secondLocation == null) {
            player.sendMessage(formMaker.getPath("locationsNotFound"));
            return false;
        }
        int xMin = Math.min(firstLocation.getBlockX(), secondLocation.getBlockX());
        int xMax = Math.max(firstLocation.getBlockX(), secondLocation.getBlockX());
        int yMin = Math.min(firstLocation.getBlockY(), secondLocation.getBlockY());
        int yMax = Math.max(firstLocation.getBlockY(), secondLocation.getBlockY());
        int zMin = Math.min(firstLocation.getBlockZ(), secondLocation.getBlockZ());
        int zMax = Math.max(firstLocation.getBlockZ(), secondLocation.getBlockZ());

        for (int y = yMin; y <= yMax; y++) {
            for (int x = xMin; x <= xMax; x++) {
                for (int z = zMin; z <= zMax; z++) {
                    if ((x == xMin || x == xMax) || (y == yMin || y == yMax) || (z == zMin || z == zMax)) {
                        Location newLocation = new Location(player.getWorld(), x, y, z);
                        player.getWorld().getBlockAt(newLocation).setType(Material.GLASS);
                    }
                }
            }
        }
        return false;
    }
}
