package de.griefed;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.file.FileNotFoundAction;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.electronwill.nightconfig.toml.TomlFormat;
import com.electronwill.nightconfig.toml.TomlParser;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main {

  public static void main(String[] args) throws IOException {

    TomlParser parser = new TomlParser();
    parser.setInitialListCapacity(10000);
    parser.setInitialStringBuilderCapacity(10000);

    File beforeConf = new File("before.conf");
    File afterConf = new File("after.conf");

    CommentedConfig conf = parser.parse(beforeConf, FileNotFoundAction.THROW_ERROR);

    ConfigurationModel configurationModel = new ConfigurationModel(beforeConf);

    CommentedConfig manualConfig = TomlFormat.instance().createConfig();
    manualConfig.set("includeServerInstallation",
        configurationModel.getIncludeServerInstallation());
    manualConfig.setComment("includeServerInstallation",
        " Whether to install a Forge/Fabric/Quilt server for the serverpack. Must be true or false.\n Default value is true.");

    manualConfig.setComment("serverIconPath",
        " Path to a custom server-icon.png-file to include in the server pack.");
    manualConfig.set("serverIconPath", configurationModel.getServerIconPath());

    manualConfig.setComment("copyDirs",
        " Name of directories or files to include in serverpack.\n When specifying \"saves/world_name\", \"world_name\" will be copied to the base directory of the serverpack\n for immediate use with the server. Automatically set when projectID,fileID for modpackDir has been specified.\n Example: [config,mods,scripts]");
    manualConfig.set("copyDirs", configurationModel.getCopyDirs());

    manualConfig.setComment("serverPackSuffix",
        " Suffix to append to the server pack to be generated. Can be left blank/empty.");
    manualConfig.set("serverPackSuffix", configurationModel.getServerPackSuffix());

    manualConfig.setComment("clientMods",
        " List of client-only mods to delete from serverpack.\n No need to include version specifics. Must be the filenames of the mods, not their project names on CurseForge!\n Example: [AmbientSounds-,ClientTweaks-,PackMenu-,BetterAdvancement-,jeiintegration-]");
    manualConfig.set("clientMods", configurationModel.getClientMods());

    manualConfig.setComment("serverPropertiesPath",
        " Path to a custom server.properties-file to include in the server pack.");
    manualConfig.set("serverPropertiesPath", configurationModel.getServerPropertiesPath());

    manualConfig.setComment("includeServerProperties",
        " Include a server.properties in your serverpack. Must be true or false.\n If no server.properties is provided but is set to true, a default one will be provided.\n Default value is true.");
    manualConfig.set("includeServerProperties", configurationModel.getIncludeServerProperties());

    manualConfig.setComment("javaArgs",
        " Java arguments to set in the start-scripts for the generated server pack. Default value is \"empty\".\n Leave as \"empty\" to not have Java arguments in your start-scripts.");
    manualConfig.set("javaArgs", configurationModel.getJavaArgs());

    manualConfig.setComment("javaPath",
        " Path to the Java executable. On Linux systems it would be something like \"/usr/bin/java\".\n Only needed if includeServerInstallation is true.");
    manualConfig.set("javaPath", configurationModel.getJavaPath());

    manualConfig.setComment("modpackDir",
        " Path to your modpack. Can be either relative or absolute.\n Example: \"./Some Modpack\" or \"C:/Minecraft/Some Modpack\"");
    manualConfig.set("modpackDir", configurationModel.getModpackDir());

    manualConfig.setComment("includeServerIcon",
        " Include a server-icon.png in your serverpack. Must be true or false\n Default value is true.");
    manualConfig.set("includeServerIcon", configurationModel.getIncludeServerIcon());

    manualConfig.setComment("includeZipCreation",
        " Create zip-archive of serverpack. Must be true or false.\n Default value is true.");
    manualConfig.set("includeZipCreation", configurationModel.getIncludeZipCreation());

    manualConfig.setComment("modLoaderVersion",
        " The version of the modloader you want to install. Example for Fabric=\"0.7.3\", example for Forge=\"36.0.15\".\n Automatically set when projectID,fileID for modpackDir has been specified.\n Only needed if includeServerInstallation is true.");
    manualConfig.set("modLoaderVersion", configurationModel.getModLoaderVersion());

    manualConfig.setComment("minecraftVersion",
        " Which Minecraft version to use. Example: \"1.16.5\".\n Automatically set when projectID,fileID for modpackDir has been specified.\n Only needed if includeServerInstallation is true.");
    manualConfig.set("minecraftVersion", configurationModel.getMinecraftVersion());

    manualConfig.setComment("modLoader",
        " Which modloader to install. Must be either \"Forge\", \"Fabric\", \"Quilt\" or \"LegacyFabric\".\n Automatically set when projectID,fileID for modpackDir has been specified.\n Only needed if includeServerInstallation is true.");
    manualConfig.set("modLoader", configurationModel.getModLoader());

    Config addons = TomlFormat.instance().createConfig();
    addons.valueMap().putAll(configurationModel.getAddonsConfigs());
    manualConfig.set("addons", addons);

    TomlFormat.instance().createWriter()
        .write(manualConfig, afterConf, WritingMode.REPLACE, StandardCharsets.UTF_8);
  }
}
