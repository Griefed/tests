package de.griefed.filebrowser.view;

import de.griefed.filebrowser.controller.action.ModpackDirectoryAction;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.io.File;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

public class DirectoryPopMenu extends MouseAdapter {

  private final JPopupMenu MENU = new JPopupMenu();
  private final ModpackDirectoryAction ACTION;

  public DirectoryPopMenu(JTextField jtextField) {
    ACTION = new ModpackDirectoryAction(jtextField);
    MENU.add(ACTION);
  }

  public void show(Component invoker, int x, int y, File file) {
    ACTION.setDirectory(file);
    MENU.show(invoker, x, y);
  }
}
