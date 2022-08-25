package de.griefed;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AvailableLoaders {

  private final List<String> RELEASES = new ArrayList<>();
  private final List<String> SNAPSHOTS = new ArrayList<>();
  private final List<String> ALL = new ArrayList<>();
  private final ObjectMapper MAPPER;
  private final String MC_VERSION;
  private final String AVAILABLE_LOADERS = "https://meta.legacyfabric.net/v2/versions/loader/%s";

  public AvailableLoaders(String mcVersions, ObjectMapper mapper) throws IOException {
    this.MAPPER = mapper;
    this.MC_VERSION = mcVersions;
    update(MC_VERSION);
  }

  void update(String mcVersions) throws IOException {
    MAPPER.readTree(new URL(String.format(AVAILABLE_LOADERS,mcVersions))).forEach(node -> {
      String version = node.get("loader").get("version").asText();
      ALL.add(version);
      if (node.get("loader").get("stable").asBoolean()) {
        RELEASES.add(version);
      } else {
        SNAPSHOTS.add(version);
      }
    });
  }

  public List<String> releases() {
    return RELEASES;
  }

  public List<String> snapshots() {
    return SNAPSHOTS;
  }

  public List<String> all() {
    return ALL;
  }
}
