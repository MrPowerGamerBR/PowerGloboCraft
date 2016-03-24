package com.mrpowergamerbr.powerglobocraft.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mrpowergamerbr.powerglobocraft.PowerGloboCraft;
import com.mrpowergamerbr.powerglobocraft.utils.CooldownUtils;

public class GloboCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		final Player p = (Player) arg0;

		p.sendMessage(PowerGloboCraft.prefix + "Pegando um livro...");
		Thread t = new Thread(new Runnable() {
			public void run() {
				ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
				BookMeta meta = (BookMeta) book.getItemMeta();
				
				if (!inventoryCanHoldItem(p.getInventory(), book)) {
					p.sendMessage(PowerGloboCraft.prefix + "§cVocê não tem espaço no seu inventário!");
					return;
				}
				
				if (CooldownUtils.hasCooldown("globonews", p, 1800)) {
					p.sendMessage(PowerGloboCraft.prefix + "Você já pegou um livro! Que tal esperar um pouquinho antes de pegar outro?");
					return;
				}
				CooldownUtils.activateCooldown("globonews", p);


				meta.setTitle("§c§lg1.globo.com");
				try {
					Document doc = Jsoup.connect("http://g1.globo.com/").get();
					List<String> pages = new ArrayList<String>();
					Elements media2 = doc.getElementsByClass("destaque-showtime__highlight-link");

					pages.add("§c§lGLOBO NEWS\n" + "§0Edição das " + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE) + " - " + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "/" + Calendar.getInstance().get(Calendar.MONTH) + "/" + Calendar.getInstance().get(Calendar.YEAR));

					for (Element src : media2) {
						Elements link = src.getElementsByClass("gui-text-title");

						pages.add("§3" + link.get(0).html() + "\n\n" + "§2" + src.attr("href"));
					}

					Elements media = doc.getElementsByClass("feed-post-link");

					for (Element src : media) {
						Elements link = src.getElementsByClass("feed-post-body-title");

						pages.add("§3" + link.get(0).html() + "\n\n" + "§2" + src.attr("href"));
					}

					meta.setPages(pages);
					book.setItemMeta(meta);

					p.getInventory().addItem(book);
					p.sendMessage(PowerGloboCraft.prefix + "§aVocê recebeu um livro da Globo News!");
				} catch (IOException e) {
					p.sendMessage(PowerGloboCraft.prefix + "§cErro ao contatar o http://g1.globo.com/");
				}
			}
		});
		t.start();


		return true;
	}

	/*
	 * Verificar se o player consegue segurar um tipo de item.
	 */
	public static boolean inventoryCanHoldItem(Inventory inv, org.bukkit.inventory.ItemStack is) {
		for (org.bukkit.inventory.ItemStack itemStack : inv) {
			if (itemStack != null) {
				if (itemStack.getType() == is.getType()) {
					if (itemStack.getAmount() <= (is.getMaxStackSize() - is.getAmount())) {
						return true;
					}
				}
			} else {
				return true;
			}
		}
		return false;
	}
}
