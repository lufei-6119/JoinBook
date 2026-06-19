package com.example.joinBook;

import org.bukkit.GameMode;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equals("reload")){
            try {
                JoinBook.main.reloadConfig();
            }catch (Exception e){
                JoinBook.main.getLogger().warning("重载失败！");
                JoinBook.main.getLogger().warning("请检查配置文件 或 再次重载插件！");
            }

            return true;
        }

        if (args.length == 1 && args[0].equals("reload")){
            sender.sendMessage("JoinBook插件指令查询");
            sender.sendMessage("/jb reload - 重载插件");

            return true;
        }
        return true;
    }
}
