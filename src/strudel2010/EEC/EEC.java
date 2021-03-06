package strudel2010.EEC;

import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class EEC extends JavaPlugin{
	public final Logger logger = Logger.getLogger("Minecraft");
	public static EEC plugin;
	public final MyPlayerListener pl = new MyPlayerListener(this);
	
	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Has Been Endabled.");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.pl, this);
	}
	
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Has Been Disabled.");
	}
	
	private static int getRandomNumber(int begin, int end) {
		Random generator = new Random();
		return generator.nextInt(end - begin + 1) + begin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		String[] fox = new String[8];
		fox[0] = "Ring-ding-ding-ding-dingeringeding!";
		fox[1] = "Wa-pa-pa-pa-pa-pa-pow!";
		fox[2] = "Hatee-hatee-hatee-ho!";
		fox[3] = "Joff-tchoff-tchoffo-tchoffo-tchoff!";
		fox[4] = "Jacha-chacha-chacha-chow!";
		fox[5] = "Fraka-kaka-kaka-kaka-kow!";
		fox[6] = "A-hee-ahee ha-hee!";
		fox[7] = "A-oo-oo-oo-ooo!";
		Player player = (Player) sender;
		Location loc = player.getLocation();
		if(commandLabel.equalsIgnoreCase("sendme")) {
			player.sendMessage(ChatColor.GOLD + "Sent");
		}
		if(commandLabel.equalsIgnoreCase("cluck")) {
			String message = "";
			for(int i = 0; i < args.length; i++) {
				if(i < args.length - 1) {
					message += args[i] + " ";
				}
				else {
					message += args[i];
				}
			}
			player.sendMessage(ChatColor.RED + "" + ChatColor.MAGIC + "" + message);
		}
		if(commandLabel.equalsIgnoreCase("fox")) {
			player.sendMessage(ChatColor.GREEN + fox[getRandomNumber(0, fox.length - 1)]);
			player.playSound(loc, Sound.WOLF_HOWL, 0.5F, getRandomNumber(0, fox.length - 1));
		}
		if(commandLabel.equalsIgnoreCase("broken")) {
			player.sendMessage(ChatColor.RED + "#BlameEndain");
			player.playSound(loc, Sound.ITEM_BREAK, 0.5F, 1F);
		}
		if(commandLabel.equalsIgnoreCase("timey")) { 
			player.sendMessage(ChatColor.BLUE + "People assume that time is a strict progression of cause to effect," +
					" but actually from a non-linear, non-subjective viewpoint, it's more like a big ball" +
					" of wibbly wobbly timey wimey...stuff.");
			player.playSound(loc, Sound.PORTAL_TRAVEL, 0.5F, 10F);
			player.playSound(loc, Sound.PORTAL_TRIGGER, 0.5F, 1F);
		}
		if(commandLabel.equalsIgnoreCase("chickencannon")) {
			player.sendMessage(ChatColor.GOLD + "Cluck...BOOM!");
			player.playSound(loc, Sound.CHICKEN_IDLE, 2F, 1F);
			player.playSound(loc, Sound.EXPLODE, 0.5F, 1F);
			Vector dir = new Vector (10, 1, 1);
			loc.getWorld().spawnEntity(loc, EntityType.CHICKEN).setVelocity(loc.getDirection().multiply(4));
		}
		return false;
	}
}
