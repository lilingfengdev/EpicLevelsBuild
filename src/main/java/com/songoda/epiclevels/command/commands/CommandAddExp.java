package com.songoda.epiclevels.command.commands;

import com.songoda.epiclevels.EpicLevels;
import com.songoda.epiclevels.command.AbstractCommand;
import com.songoda.epiclevels.utils.Methods;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandAddExp extends AbstractCommand {

    public CommandAddExp(AbstractCommand parent) {
        super(parent, false, "AddExp");
    }

    @Override
    protected ReturnType runCommand(EpicLevels instance, CommandSender sender, String... args) {
        if (args.length != 3) return ReturnType.SYNTAX_ERROR;

        Player player = Bukkit.getPlayer(args[1].toLowerCase());

        if (player == null) {
            sender.sendMessage(instance.getReferences().getPrefix() + instance.getLocale().getMessage("command.general.notonline", args[1]));
            return ReturnType.FAILURE;
        }

        if (!Methods.isInt(args[2])) {
            sender.sendMessage(Methods.formatText(instance.getReferences().getPrefix() + instance.getLocale().getMessage("command.general.notint")));
            return ReturnType.SYNTAX_ERROR;
        }

        long amount = Long.parseLong(args[2]);
        instance.getPlayerManager().getPlayer(player).addExperience(amount);


        sender.sendMessage(instance.getReferences().getPrefix() + instance.getLocale().getMessage("command.addexp.success", player.getName(), amount));

        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(EpicLevels instance, CommandSender sender, String... args) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "epiclevels.addexp";
    }

    @Override
    public String getSyntax() {
        return "/levels AddExp <Player> <Amount>";
    }

    @Override
    public String getDescription() {
        return "Add experience to a player.";
    }
}
