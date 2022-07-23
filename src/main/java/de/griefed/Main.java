package de.griefed;

import com.electronwill.nightconfig.toml.TomlParser;
import java.io.File;
import java.util.List;
import java.util.Map;

public class Main {

  public static void main(String[] args) {

    TomlParser parser = new TomlParser();

    TomlScanner tomlScanner = new TomlScanner(parser);

    File[] files =
        new File("C:\\Minecraft\\Game\\Instances\\Fantasia Adventures\\mods").listFiles();

    Map<String, List<String>> result = tomlScanner.scan(files);

    System.out.println();
    System.out.println("Excluded:");
    result.get("excluded").forEach(mod -> System.out.println("  " + mod));

    files =
        new File("C:\\Minecraft\\Game\\Instances\\Survive Create Prosper 4 (1)\\mods").listFiles();

    result = tomlScanner.scan(files);

    System.out.println();
    System.out.println("Excluded:");
    result.get("excluded").forEach(mod -> System.out.println("  " + mod));
  }
}
