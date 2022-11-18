package sperias.essential.command.cmd_punishment;

import SPEssential.SPEssential;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import sperias.essential.command.Command;
import sperias.essential.command.controller.PunishmentController;
import sperias.essential.entity.PlayerApi;
import sun.jvm.hotspot.StackTrace;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class Ban extends Command implements CommandExecutor, TabCompleter{

    public Ban(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] args) {
        // /ban {playerName 0} {reason 1} {year 2} {month 3} {day 4} {hour 5} {minute 6} {second 7}
        if(args.length >= 8)
        {
            StringBuilder reason = new StringBuilder();
            for(int i = 1; i < (args.length - 6); i++) reason.append(args[i]).append(" ");
            int year, month, day, hour, minute, second;
            try {
                year = Integer.parseInt(args[args.length - 6]);
                month = Integer.parseInt(args[args.length - 5]);
                day = Integer.parseInt(args[args.length - 4]);
                hour = Integer.parseInt(args[args.length - 3]);
                minute = Integer.parseInt(args[args.length - 2]);
                second = Integer.parseInt(args[args.length - 1]);
            }catch (NumberFormatException e)
            {
                sender.sendMessage("§cVeuillez insérer des nombres valides pour la date d'expiration");
                return false;
            }
            try {
                PlayerApi target = PlayerApi.fetchPlayerAPI(args[0]);
                Timestamp expirationDate = getExpirationDate(year, month, day, hour, minute, second);
                if(!new PunishmentController(sender).canBan(target, expirationDate, reason.toString())) return false;
                plugin.getPlayerBanned().put(target.getUUID(), new sperias.essential.entity.Ban(expirationDate, reason.toString()));
                if(Bukkit.getPlayer(target.getUUID()) != null && Bukkit.getPlayer(target.getUUID()).isOnline())
                {
                    Bukkit.getPlayer(target.getUUID()).kickPlayer(plugin.getPlayerBanned().get(target.getUUID()).getBannedMessage());
                }
            } catch (IOException ignore) {}
            return true;
        }

        return false;
    }

    public Timestamp getExpirationDate(int year, int month, int day, int hour, int minute, int second)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        calendar.add(Calendar.HOUR, hour);
        calendar.add(Calendar.MINUTE, minute);
        calendar.add(Calendar.SECOND, second);
        return new Timestamp(calendar.getTimeInMillis());
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if(args.length == 1)
        {
            return Arrays.asList("Hello", "Noob");
        }
        return null;
    }
}
