package com.example.joinBook;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.List;

public class JoinListener implements Listener {

    private final JoinBook plugin;

    public JoinListener(JoinBook plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        // 仅首次进入发放
        if (!player.hasPlayedBefore()) {

            FileConfiguration config = plugin.getConfig();

            ItemStack book = new ItemStack(Material.WRITTEN_BOOK);

            BookMeta meta = (BookMeta) book.getItemMeta();

            if (meta == null) {
                return;
            }

            meta.setTitle(
                    ChatColor.translateAlternateColorCodes(
                            '&',
                            config.getString("book.title", "服务器指南")
                    )
            );

            meta.setAuthor(
                    ChatColor.translateAlternateColorCodes(
                            '&',
                            config.getString("book.author", "服务器")
                    )
            );

            List<String> pages = config.getStringList("book.pages");

            for (String page : pages) {

                page = ChatColor.translateAlternateColorCodes('&', page);

                meta.addPage(page);
            }

            book.setItemMeta(meta);

            player.getInventory().addItem(book);

            // 自动打开书
            player.openBook(book);

            boolean kitsenable = JoinBook.main.getConfig().getBoolean("kits.enable");

            if (kitsenable) {
                player.sendMessage(JoinBook.main.getConfig().getString("kits.message"));
            }
        }
    }
}