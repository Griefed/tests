package de.griefed;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;

public class Main {

  public static void main(String[] args) throws IOException {
    String shell = FileUtils.readFileToString(new File("template_sh.sh"), StandardCharsets.UTF_8);

    shell =
        shell
            .replace("SPC_SERVERPACKCREATOR_VERSION_SPC", "3.8.0")
            .replace("SPC_MINECRAFT_VERSION_SPC", "1.16.5")
            .replace(
                "SPC_MINECRAFT_SERVER_URL_SPC",
                "https://launcher.mojang.com/v1/objects/1b557e7b033b583cd9f66746b7a9ab1ec1673ced/server.jar")
            .replace("SPC_MODLOADER_VERSION_SPC", "0.17.1-beta.5")
            .replace("SPC_MODLOADER_SPC", "Quilt")
            .replace(
                "SPC_JAVA_ARGS_SPC",
                "-Xmx8192M -Xms1024M -XX:+UseG1GC -XX:+ParallelRefProcEnabled -XX:MaxGCPauseMillis=200 -XX:+UnlockExperimentalVMOptions -XX:+DisableExplicitGC -XX:+AlwaysPreTouch -XX:G1HeapWastePercent=5 -XX:G1MixedGCCountTarget=4 -XX:G1MixedGCLiveThresholdPercent=90 -XX:G1RSetUpdatingPauseTimePercent=5 -XX:SurvivorRatio=32 -XX:+PerfDisableSharedMem -XX:MaxTenuringThreshold=1 -XX:G1NewSizePercent=30 -XX:G1MaxNewSizePercent=40 -XX:G1HeapRegionSize=8M -XX:G1ReservePercent=20 -XX:InitiatingHeapOccupancyPercent=15 -Dusing.aikars.flags=https://mcflags.emc.gs -Daikars.new.flags=true")
            .replace("SPC_JAVA_SPC", "java")
            .replace("SPC_FABRIC_INSTALLER_VERSION_SPC", "0.11.0")
            .replace("SPC_QUILT_INSTALLER_VERSION_SPC", "0.4.3");

    FileUtils.writeStringToFile(new File("start.sh"),shell,StandardCharsets.UTF_8);
  }
}