package com.example.joinBook;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class JoinBook extends JavaPlugin {

    static JoinBook main;

    @Override
    public void onEnable() {
        // 首次运行自动生成config.yml
        saveDefaultConfig();

        // 注册监听器
        getServer().getPluginManager().registerEvents(
                new JoinListener(this),
                this
        );

        getLogger().info("JoinBook 已启用！");

        Objects.requireNonNull(Bukkit.getPluginCommand("joinbook")).setExecutor(new Commands());

        main = this;
    }

    @Override
    public void onDisable() {
        getLogger().info("JoinBook 已关闭！");
    }
}
