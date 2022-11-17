package sperias.essential.command.cmd_entertainment;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import sperias.essential.command.controller.EntertainmentController;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Condense implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(args.length == 0 && sender instanceof Player)
        {
            Player player = (Player) sender;
            EntertainmentController essentialController = new EntertainmentController(player);
            ItemStack block = null;
            Material playerMainItem = player.getInventory().getItemInMainHand().getType();
            if(playerMainItem == Material.COAL) block = new ItemStack(Material.COAL_BLOCK);
            if(playerMainItem == Material.COPPER_INGOT) block = new ItemStack(Material.COPPER_BLOCK);
            if(playerMainItem == Material.IRON_INGOT) block = new ItemStack(Material.IRON_BLOCK);
            if(playerMainItem == Material.LAPIS_LAZULI) block = new ItemStack(Material.LAPIS_BLOCK);
            if(playerMainItem == Material.REDSTONE) block = new ItemStack(Material.REDSTONE_BLOCK);
            if(playerMainItem == Material.GOLD_NUGGET) block = new ItemStack(Material.GOLD_INGOT);
            if(playerMainItem == Material.GOLD_INGOT) block = new ItemStack(Material.GOLD_BLOCK);
            if(playerMainItem == Material.DIAMOND) block = new ItemStack(Material.DIAMOND_BLOCK);
            if(playerMainItem == Material.EMERALD) block = new ItemStack(Material.EMERALD_BLOCK);
            if(playerMainItem == Material.NETHERITE_INGOT) block = new ItemStack(Material.NETHERITE_BLOCK);
            if(playerMainItem == Material.QUARTZ) block = new ItemStack(Material.QUARTZ_BLOCK);
            AtomicInteger quantityItem = new AtomicInteger();
            Arrays.stream(player.getInventory().getContents())
                    .filter(item -> item != null && item.getType() == playerMainItem)
                    .forEach(item -> quantityItem.addAndGet(item.getAmount()));
            if(!essentialController.canCondense(block, Integer.parseInt(quantityItem.toString()))) return false;
            int nbBlock = 0;
            int nbRest = 0;
            if(block.getType() == Material.QUARTZ_BLOCK) nbBlock = (int) Integer.parseInt(quantityItem.toString()) / 4;
            if(block.getType() != Material.QUARTZ_BLOCK) nbBlock = (int) Integer.parseInt(quantityItem.toString()) / 9;
            if(block.getType() == Material.QUARTZ_BLOCK) nbRest = (int) Integer.parseInt(quantityItem.toString()) % 4;
            if(block.getType() != Material.QUARTZ_BLOCK) nbRest = (int) Integer.parseInt(quantityItem.toString()) % 9;
            block.setAmount(nbBlock);
            Arrays.stream(player.getInventory().getContents())
                            .filter(item -> item != null && item.getType() == playerMainItem)
                                    .forEach(itemStack -> player.getInventory().remove(playerMainItem));
            player.getInventory().addItem(block);
            player.getInventory().addItem(new ItemStack(playerMainItem, nbRest));
            return true;
        }

        return false;
    }
}
