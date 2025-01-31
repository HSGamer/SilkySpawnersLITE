package us.thezircon.play.silkyspawnerslite.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import us.thezircon.play.silkyspawnerslite.SilkySpawnersLITE;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Random;
import java.util.logging.Logger;

public class UpdateConfigs {

    private static final SilkySpawnersLITE plugin = SilkySpawnersLITE.getPlugin(SilkySpawnersLITE.class);
    private static final Logger log = Logger.getLogger("Minecraft");

    public static void config() {
        //Config.yml
        //YamlConfiguration conf = (YamlConfiguration) plugin.getConfig().getDefaults();
        Random r = new Random();
        InputStream input = plugin.getResource("config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(new InputStreamReader(input));

       // Debug
       // System.out.println("Plugin:" + plugin.getConfig().getKeys(true));
       // System.out.println("Defaults:" + conf.getKeys(true));

        for (String key : conf.getKeys(true)) {
            if (!plugin.getConfig().getKeys(true).contains(key)) {
                File oldConf = new File(plugin.getDataFolder(), "config.yml");
                File newConf = new File(plugin.getDataFolder(), "outdated-config-" + LocalDate.now() + " (as of "+ plugin.getDescription().getVersion() +")"+".yml");

                oldConf.renameTo(newConf);
                plugin.getConfig().options().copyDefaults();
                plugin.saveDefaultConfig();

                log.severe("[SilkySpawnersLITE] Outdated Config for SilkySpawners! Please check your old config as a new one has been generated");

                break;
            }
        }
    }

    public static void lang() {
        //Lang.yml
        Random r = new Random();
        InputStream input = plugin.getResource("lang.yml");
        YamlConfiguration lang = YamlConfiguration.loadConfiguration(new InputStreamReader(input));
        for (String key : lang.getKeys(true)) {
            if (!plugin.getLangConfig().getKeys(true).contains(key)) {
                File oldLang = new File(plugin.getDataFolder(), "lang.yml");
                File newLang = new File(plugin.getDataFolder(),"outdated-lang-"+ LocalDate.now()+" ("+r.nextInt(1000)+")"+".yml");
                oldLang.renameTo(newLang);
                plugin.saveResource("lang.yml", false);
                plugin.langReload();

                log.severe("[SilkySpawnersLITE] Outdated Lang file for SilkySpawners! Please check your old config as a new one has been generated");

                break;
            }
        }
    }
}