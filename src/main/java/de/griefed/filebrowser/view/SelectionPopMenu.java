package de.griefed.filebrowser.view;

import de.griefed.filebrowser.controller.action.ModpackDirectoryAction;
import de.griefed.filebrowser.controller.action.OpenAction;
import de.griefed.filebrowser.controller.action.ServerIconAction;
import de.griefed.filebrowser.controller.action.ServerPropertiesAction;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.util.Locale;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class SelectionPopMenu extends MouseAdapter {

  private final JPopupMenu MENU = new JPopupMenu();
  private final ModpackDirectoryAction DIRECTORY;
  private final ServerIconAction ICON;
  private final ServerPropertiesAction PROPERTIES;
  private final OpenAction OPEN;

  public SelectionPopMenu(JTextField jtextField) {
    DIRECTORY = new ModpackDirectoryAction(jtextField);
    ICON = new ServerIconAction();
    PROPERTIES = new ServerPropertiesAction();
    OPEN = new OpenAction();
    MENU.add(OPEN);
    MENU.add(new JSeparator());
    MENU.add(DIRECTORY);
    MENU.add(ICON);
    MENU.add(PROPERTIES);
  }

  public void show(Component invoker, int x, int y, File file) {
    setVisibilities(file);
    MENU.show(invoker, x, y);
  }

  private void setVisibilities(File file) {
    OPEN.setFile(file);

    if (file.isDirectory()) {
      DIRECTORY.setDirectory(file);
      DIRECTORY.setEnabled(true);
      ICON.setEnabled(false);
      PROPERTIES.setEnabled(false);
      return;
    }

    if (file.isFile() && file.getName().toLowerCase(Locale.ROOT).matches(".*\\.(png|jpg|jpeg|bmp)")) {
      DIRECTORY.setEnabled(false);
      ICON.setEnabled(true);
      PROPERTIES.setEnabled(false);
      return;
    }

    if (file.isFile() && file.getName().toLowerCase(Locale.ROOT).endsWith("properties")) {
      DIRECTORY.setEnabled(false);
      ICON.setEnabled(false);
      PROPERTIES.setEnabled(true);
    }
  }
}
