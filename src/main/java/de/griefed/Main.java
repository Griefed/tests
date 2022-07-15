package de.griefed;

import de.griefed.FabricLoaderDetails.Details;
import de.griefed.FabricLoaderDetails.Library;
import de.griefed.QuiltIntermediary.Intermediary;
import java.io.IOException;
import java.util.Map;

public class Main {

  public static void main(String[] args) throws IOException {
    QuiltIntermediary quiltIntermediary = new QuiltIntermediary();

    for (Map.Entry<String, Intermediary> entry : quiltIntermediary.getIntermediaries().entrySet()) {
      System.out.println(entry.getKey());
      System.out.println("    " + entry.getValue().getVersion());
      System.out.println("    " + entry.getValue().getMaven());
      System.out.println("    " + entry.getValue().isStable());
    }

    FabricLoaderDetails fabricLoaderDetails = new FabricLoaderDetails();

    Details details = fabricLoaderDetails.getDetails("1.18.2","0.14.8");

    System.out.println(details.getId());
    System.out.println(details.getInheritsFrom());
    System.out.println(details.getReleaseTime());
    System.out.println(details.getTime());
    System.out.println(details.getType());
    System.out.println(details.getMainClass());
    System.out.println(details.getArguments());
    for (Library library : details.getLibraries()) {
      System.out.println(library.getName());
      System.out.println(library.getUrl());
    }
  }
}
