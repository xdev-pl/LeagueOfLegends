package pl.luxdev.lol.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemCrafter {
	
	public static ItemStack createItem(Material material, int amount, short data, String displayname, String loreLine) {
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		List<String> lore = new ArrayList<>();
		lore.add(loreLine);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack createItemWE(Material material, int amount, short data, String displayname, String loreLine, Enchantment ent, int entl) {
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		List<String> lore = new ArrayList<>();
		lore.add(loreLine);
		meta.setLore(lore);
		item.setItemMeta(meta);
		item.addUnsafeEnchantment(ent, entl);
		return item;
	}
}
