package sperias.essential.command.cmd_punishment;

import SPEssential.SPEssential;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import sperias.essential.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.essential.command.controller.PunishmentController;
import sperias.essential.entity.PlayerApi;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class TempBan extends Command implements CommandExecutor {

    public TempBan(SPEssential plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] args) {
        // /ban {playerName 0} {reason 1} {year 2} {month 3} {day 4} {hour 5} {minute 6} {second 7}
        if(args.length == 8)
        {
            ConsoleCommandSender player = (ConsoleCommandSender) sender;
            Player target = Bukkit.getPlayer(args[0]);
            PunishmentController punishmentController = new PunishmentController(player);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.YEAR, Integer.parseInt(args[2]));
            calendar.add(Calendar.MONTH, Integer.parseInt(args[3]));
            calendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(args[4]));
            calendar.add(Calendar.HOUR, Integer.parseInt(args[5]));
            calendar.add(Calendar.MINUTE, Integer.parseInt(args[6]));
            calendar.add(Calendar.SECOND, Integer.parseInt(args[7]));
            try {
                if(!punishmentController.canBan(target, args[0], new Timestamp(calendar.getTimeInMillis()), args[1])) return false;
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(target != null)
            {
                plugin.getPlayerBanned().put(target.getUniqueId(), calendar.getTime());
            }
            else
            {
                try {
                    plugin.getPlayerBanned().put(PlayerApi.fetchPlayerAPI(args[0]).getUUID(), calendar.getTime());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }
}
