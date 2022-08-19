package de.griefed;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class Meta {

  private final List<String> RELEASES = new ArrayList<>();
  private final List<String> SNAPSHOTS = new ArrayList<>();
  private final List<String> ALL = new ArrayList<>();
  private final ObjectMapper MAPPER;
  private final URL ENDPOINT;

  public Meta(URL endpoint, ObjectMapper mapper) throws IOException {
    this.MAPPER = mapper;
    this.ENDPOINT = endpoint;
    update();
  }

  void update() throws IOException {
    MAPPER.readTree(ENDPOINT).forEach(node -> {
      String version = node.get("version").asText();
      ALL.add(version);
      if (node.get("stable").asBoolean()) {
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
