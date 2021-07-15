package fr.cursor.formmaker.utils;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.Dye;

import java.util.Arrays;

public class ItemBuilder {

    private ItemStack is;

    public ItemBuilder(Material m) {
        this(m, 1);
    }

    public ItemBuilder(ItemStack is) {
        this.is = is;
    }

    public ItemBuilder(Material m, int amount) {
        is = new ItemStack(m, amount);
    }

    public ItemBuilder(Material m, int amount, byte durability) {
        is = new ItemStack(m, amount, durability);
    }

    public ItemBuilder setName(String string) {
        ItemMeta itemMeta = is.getItemMeta();
        itemMeta.setDisplayName(string);
        is.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addLore(String... strings) {
        ItemMeta itemMeta = is.getItemMeta();
        itemMeta.setLore(Arrays.asList(strings));
        is.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setDurability(int durability) {
        is.setDurability((short) durability);
        return this;
    }

    public ItemBuilder setSkullOwner(String skullOwner) {
        SkullMeta skullMeta = (SkullMeta) is.getItemMeta();
        skullMeta.setOwner(skullOwner);
        return this;
    }

    public ItemBuilder setDyeColor(DyeColor dyeColor) {
        Dye dye = new Dye();
        dye.setColor(dyeColor);
        return this;
    }

    public ItemBuilder setLeatherArmorColor(Color color) {
        try {
            LeatherArmorMeta im = (LeatherArmorMeta) is.getItemMeta();
            im.setColor(color);
            is.setItemMeta(im);
        } catch (ClassCastException expected) {
        }
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int value) {
        ItemMeta itemMeta = is.getItemMeta();
        itemMeta.addEnchant(enchantment, value, false);
        is.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addUnsafeEnchantement(Enchantment enchantment, int value) {
        ItemMeta itemMeta = is.getItemMeta();
        itemMeta.addEnchant(enchantment, value, true);
        is.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addItemFlags(ItemFlag itemFlag) {
        ItemMeta itemMeta = is.getItemMeta();
        itemMeta.addItemFlags(itemFlag);
        is.setItemMeta(itemMeta);
        return this;
    }

    public ItemStack toBuild() {
        return is;
    }

}
