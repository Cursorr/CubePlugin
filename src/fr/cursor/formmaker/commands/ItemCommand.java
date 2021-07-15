package fr.cursor.formmaker.commands;

import fr.cursor.formmaker.FormMaker;
import fr.cursor.formmaker.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class ItemCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        final FormMaker formMaker = FormMaker.getInstance();
        if (!(sender instanceof Player)) {
            System.out.println(formMaker.getPath("notConnectedToServer"));
            return false;
        }
        final Player player = (Player) sender;
        final ItemStack formItem = new ItemBuilder(Material.DIAMOND_PICKAXE, 1)
                .setName(formMaker.getPath("item.name"))
                .addEnchantment(Enchantment.ARROW_FIRE, 1)
                .addItemFlags(ItemFlag.HIDE_ENCHANTS)
                .toBuild();
        player.getInventory().addItem(formItem);
        return false;
    }
}
