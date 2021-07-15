package fr.cursor.formmaker.listeners;

import fr.cursor.formmaker.FormMaker;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class OnClickListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        final FormMaker formMaker = FormMaker.getInstance();
        final ItemStack clickedItem = event.getItem();
        final Player player = event.getPlayer();

        if (event.getClickedBlock() == null || event.getAction() == null || clickedItem == null || !(clickedItem.toString().contains("The Handler")))
            return;

        if (!player.getGameMode().equals(GameMode.CREATIVE)) {
            player.sendMessage(formMaker.getPath("wrongGameMode"));
            return;
        }
        event.setCancelled(true);
        final Block clickedBlock = event.getClickedBlock();
        String position = "None";
        final Location blockLocation = clickedBlock.getLocation();
        if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            position = "First";
            formMaker.setFirstLocation(player, blockLocation);
        } else if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            position = "Second";
            formMaker.setSecondLocation(player, blockLocation);
        }

        player.sendMessage(formMaker.getPath("command.setBlock").replace("%place%", position)
                .replace("%X%", Integer.toString(blockLocation.getBlockX()))
                .replace("%Y%", Integer.toString(blockLocation.getBlockY()))
                .replace("%Z%", Integer.toString(blockLocation.getBlockZ()))
        );
    }

}
