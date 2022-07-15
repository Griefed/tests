package de.griefed;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class QuiltIntermediary {

  private final ObjectMapper OBJECT_MAPPER;
  private final HashMap<String,Intermediary> intermediaries = new HashMap<>();

  public QuiltIntermediary() throws IOException {
    OBJECT_MAPPER = new ObjectMapper();
    OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    OBJECT_MAPPER.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

    for (Intermediary intermediary : fillIntermediaries()) {
      intermediaries.put(intermediary.getVersion(), intermediary);
    }
  }

  private List<Intermediary> fillIntermediaries() throws IOException {
    return
        OBJECT_MAPPER.readValue(
            new URL("https://meta.fabricmc.net/v2/versions/intermediary/"),
            new TypeReference<List<Intermediary>>(){});
  }

  public HashMap<String, Intermediary> getIntermediaries() {
    return intermediaries;
  }

  public Optional<Intermediary> getIntermediary(String minecraftVersion) {
    return Optional.of(intermediaries.get(minecraftVersion));
  }

  public static class Intermediary {
    private String maven;
    private String version;
    private boolean stable;

    private Intermediary() {}

    public Intermediary(String maven, String version, boolean stable) {
      this.maven = maven;
      this.version = version;
      this.stable = stable;
    }

    public String getMaven() {
      return maven;
    }

    public String getVersion() {
      return version;
    }

    public boolean isStable() {
      return stable;
    }
  }
}
