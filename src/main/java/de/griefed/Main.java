package de.griefed;

import org.jetbrains.annotations.NotNull;

public class Main {

  public static void main(String[] args) {

    String defaultSettingG,defaultSettingM, g, m,gdefault,mdefault,noChangeBigG,noChangeSmallG,noChangeBigM,noChangeSmallM;

    defaultSettingG = "-Xms10G -Xmx10G -XX:+UseG1GC -XX:+ParallelRefProcEnabled -XX:MaxGCPauseMillis=200 -XX:+UnlockExperimentalVMOptions -XX:+DisableExplicitGC -XX:+AlwaysPreTouch -XX:G1NewSizePercent=30 -XX:G1MaxNewSizePercent=40 -XX:G1HeapRegionSize=8M -XX:G1ReservePercent=20 -XX:G1HeapWastePercent=5 -XX:G1MixedGCCountTarget=4 -XX:InitiatingHeapOccupancyPercent=15 -XX:G1MixedGCLiveThresholdPercent=90 -XX:G1RSetUpdatingPauseTimePercent=5 -XX:SurvivorRatio=32 -XX:+PerfDisableSharedMem -XX:MaxTenuringThreshold=1 -Dusing.aikars.flags=https://mcflags.emc.gs -Daikars.new.flags=true";
    defaultSettingM = "-Xms10000M -Xmx10000M -XX:+UseG1GC -XX:+ParallelRefProcEnabled -XX:MaxGCPauseMillis=200 -XX:+UnlockExperimentalVMOptions -XX:+DisableExplicitGC -XX:+AlwaysPreTouch -XX:G1NewSizePercent=30 -XX:G1MaxNewSizePercent=40 -XX:G1HeapRegionSize=8M -XX:G1ReservePercent=20 -XX:G1HeapWastePercent=5 -XX:G1MixedGCCountTarget=4 -XX:InitiatingHeapOccupancyPercent=15 -XX:G1MixedGCLiveThresholdPercent=90 -XX:G1RSetUpdatingPauseTimePercent=5 -XX:SurvivorRatio=32 -XX:+PerfDisableSharedMem -XX:MaxTenuringThreshold=1 -Dusing.aikars.flags=https://mcflags.emc.gs -Daikars.new.flags=true";
    g = "-Xms13G -Xmx13G -XX:+UseG1GC -XX:+ParallelRefProcEnabled -XX:MaxGCPauseMillis=200 -XX:+UnlockExperimentalVMOptions -XX:+DisableExplicitGC -XX:+AlwaysPreTouch -XX:G1NewSizePercent=30 -XX:G1MaxNewSizePercent=40 -XX:G1HeapRegionSize=8M -XX:G1ReservePercent=20 -XX:G1HeapWastePercent=5 -XX:G1MixedGCCountTarget=4 -XX:InitiatingHeapOccupancyPercent=15 -XX:G1MixedGCLiveThresholdPercent=90 -XX:G1RSetUpdatingPauseTimePercent=5 -XX:SurvivorRatio=32 -XX:+PerfDisableSharedMem -XX:MaxTenuringThreshold=1 -Dusing.aikars.flags=https://mcflags.emc.gs -Daikars.new.flags=true";
    m = "-Xms13000M -Xmx13000M -XX:+UseG1GC -XX:+ParallelRefProcEnabled -XX:MaxGCPauseMillis=200 -XX:+UnlockExperimentalVMOptions -XX:+DisableExplicitGC -XX:+AlwaysPreTouch -XX:G1NewSizePercent=30 -XX:G1MaxNewSizePercent=40 -XX:G1HeapRegionSize=8M -XX:G1ReservePercent=20 -XX:G1HeapWastePercent=5 -XX:G1MixedGCCountTarget=4 -XX:InitiatingHeapOccupancyPercent=15 -XX:G1MixedGCLiveThresholdPercent=90 -XX:G1RSetUpdatingPauseTimePercent=5 -XX:SurvivorRatio=32 -XX:+PerfDisableSharedMem -XX:MaxTenuringThreshold=1 -Dusing.aikars.flags=https://mcflags.emc.gs -Daikars.new.flags=true";
    gdefault = "-Xms10G -Xmx10G -XX:+UseG1GC -XX:+ParallelRefProcEnabled -XX:MaxGCPauseMillis=200 -XX:+UnlockExperimentalVMOptions -XX:+DisableExplicitGC -XX:+AlwaysPreTouch -XX:G1NewSizePercent=40 -XX:G1MaxNewSizePercent=50 -XX:G1HeapRegionSize=16M -XX:G1ReservePercent=15 -XX:G1HeapWastePercent=5 -XX:G1MixedGCCountTarget=4 -XX:InitiatingHeapOccupancyPercent=20 -XX:G1MixedGCLiveThresholdPercent=90 -XX:G1RSetUpdatingPauseTimePercent=5 -XX:SurvivorRatio=32 -XX:+PerfDisableSharedMem -XX:MaxTenuringThreshold=1 -Dusing.aikars.flags=https://mcflags.emc.gs -Daikars.new.flags=true";
    mdefault = "-Xms10000M -Xmx10000M -XX:+UseG1GC -XX:+ParallelRefProcEnabled -XX:MaxGCPauseMillis=200 -XX:+UnlockExperimentalVMOptions -XX:+DisableExplicitGC -XX:+AlwaysPreTouch -XX:G1NewSizePercent=40 -XX:G1MaxNewSizePercent=50 -XX:G1HeapRegionSize=16M -XX:G1ReservePercent=15 -XX:G1HeapWastePercent=5 -XX:G1MixedGCCountTarget=4 -XX:InitiatingHeapOccupancyPercent=20 -XX:G1MixedGCLiveThresholdPercent=90 -XX:G1RSetUpdatingPauseTimePercent=5 -XX:SurvivorRatio=32 -XX:+PerfDisableSharedMem -XX:MaxTenuringThreshold=1 -Dusing.aikars.flags=https://mcflags.emc.gs -Daikars.new.flags=true";
    noChangeBigG = "-Xms13G -Xmx13G";
    noChangeSmallG = "-Xms10G -Xmx10G";
    noChangeBigM = "-Xms13000M -Xmx13000M";
    noChangeSmallM = "-Xms10000M -Xmx10000M";

    if (!defaultSettingG.equals(checkFlags(defaultSettingG))) {
      System.out.println("1");
    }

    if (!defaultSettingM.equals(checkFlags(defaultSettingM))) {
      System.out.println("2");
    }

    if (g.equals(checkFlags(g))) {
      System.out.println("3");
    }

    if (m.equals(checkFlags(m))) {
      System.out.println("4");
    }

    if (!defaultSettingG.equals(checkFlags(gdefault))) {
      System.out.println("5");
    }

    if (!defaultSettingM.equals(checkFlags(mdefault))) {
      System.out.println("6");
    }

    if (!noChangeBigG.equals(checkFlags(noChangeBigG))) {
      System.out.println("7");
    }

    if (!noChangeSmallG.equals(checkFlags(noChangeSmallG))) {
      System.out.println("8");
    }

    if (!noChangeBigM.equals(checkFlags(noChangeBigM))) {
      System.out.println("9");
    }

    if (!noChangeSmallM.equals(checkFlags(noChangeSmallM))) {
      System.out.println("10");
    }
  }

  private static @NotNull String checkFlags(@NotNull String flags) {
    if (allocMemoryGreater(flags)) {
      flags = updateSetting(flags, "-XX:G1NewSizePercent=", "40");
      flags = updateSetting(flags, "-XX:G1MaxNewSizePercent=", "50");
      flags = updateSetting(flags, "-XX:G1HeapRegionSize=", "16");
      flags = updateSetting(flags, "-XX:G1ReservePercent=", "15");
      flags = updateSetting(flags, "-XX:InitiatingHeapOccupancyPercent=", "20");
    } else if (allocMemorySmaller(flags)) {
      flags = updateSetting(flags, "-XX:G1NewSizePercent=", "30");
      flags = updateSetting(flags, "-XX:G1MaxNewSizePercent=", "40");
      flags = updateSetting(flags, "-XX:G1HeapRegionSize=", "8");
      flags = updateSetting(flags, "-XX:G1ReservePercent=", "20");
      flags = updateSetting(flags, "-XX:InitiatingHeapOccupancyPercent=", "15");
    }

    return flags;
  }

  private static @NotNull String updateSetting(@NotNull String flags, @NotNull String setting,
      @NotNull String settingValue) {

    if (!flags.contains(setting)) {
      return flags;
    }

    int settingLength = setting.length();
    int start = flags.indexOf(setting) + settingLength;
    int end;

    for (end = start; end < flags.length(); end++) {
      if (!String.valueOf(flags.charAt(end)).matches("\\d")) {
        break;
      }
    }

    String check;
    if (start == end) {
      check = String.valueOf(flags.charAt(start));
    } else {
      check = flags.substring(start, end);
    }

    if (Integer.parseInt(check) != Integer.parseInt(
        settingValue)) {

      flags = flags.substring(0, start)
          + settingValue
          + flags.substring(end);

    }

    return flags;
  }

  private static boolean allocMemorySmaller(@NotNull String flags) {
    int start = flags.indexOf("-Xmx");
    int end;

    if (start == -1) {
      return false;
    } else {
      start = start + 4;
    }

    if (flags.matches(".*-Xmx\\d+G.*")) {

      end = getEnd(flags, 'G', start);
      return Integer.parseInt(flags.substring(start, end)) < 12;

    } else if (flags.matches(".*-Xmx\\d+M.*")) {

      end = getEnd(flags, 'M', start);
      return Integer.parseInt(flags.substring(start, end)) < 12000;

    }

    return false;
  }

  private static boolean allocMemoryGreater(@NotNull String flags) {
    int start = flags.indexOf("-Xmx");
    int end;

    if (start == -1) {
      return false;
    } else {
      start = start + 4;
    }

    if (flags.matches(".*-Xmx\\d+G.*")) {

      end = getEnd(flags, 'G', start);
      return Integer.parseInt(flags.substring(start, end)) > 12;

    } else if (flags.matches(".*-Xmx\\d+M.*")) {

      end = getEnd(flags, 'M', start);
      return Integer.parseInt(flags.substring(start, end)) > 12000;

    }

    return false;
  }

  private static int getEnd(@NotNull String flags, Character selector, int start) {
    for (int xmx = start; xmx < flags.length(); xmx++) {

      if (flags.charAt(xmx) == selector) {
        return xmx;

      }
    }
    return 0;
  }
}
