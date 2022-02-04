package fr.maxlego08.zoldenchant;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.cryptomorin.xseries.XMaterial;

/**
 * 
 * @author Maxlego08
 * Website: https://groupez.dev/
 * Serveur Minecraft Vote: https://serveur-minecraft-vote.fr/
 *
 * The plugin allows to have the enchantments of the 1.7.10 in the recent versions of minecraft
 */
public class OldEnchantPlugin extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
		new Metrics(this, 14186);
	}

	@EventHandler
	public void inventoryClick(InventoryClickEvent event) {
		Inventory inventory = event.getInventory();
		if (inventory.getType().equals(InventoryType.ENCHANTING) && event.getRawSlot() == 1) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void inventoryOpen(InventoryOpenEvent event) {
		Inventory inventory = event.getInventory();
		if (inventory.getType().equals(InventoryType.ENCHANTING)) {
			inventory.setItem(1, this.getLapisItemStack());
		}
	}

	@EventHandler
	public void inventoryClose(InventoryCloseEvent event) {
		Inventory inventory = event.getInventory();
		if (inventory.getType().equals(InventoryType.ENCHANTING)) {
			inventory.setItem(1, null);
		}
	}

	@EventHandler
	public void enchantItem(EnchantItemEvent event) {
		Inventory inventory = event.getInventory();
		inventory.setItem(1, this.getLapisItemStack());

		int newLevel = event.getEnchanter().getLevel() - event.getExpLevelCost() + (event.whichButton() + 1);
		event.getEnchanter().setLevel(newLevel);
		event.setExpLevelCost(1);
	}

	/**
	 * Allows to recover the itemstack used in the enchantment table
	 * 
	 * @return lapis lazuli itemstack
	 */
	private ItemStack getLapisItemStack() {
		return new ItemStack(XMaterial.LAPIS_LAZULI.parseMaterial(), 64);
	}

}
