package de.griefed.filebrowser.controller;

import de.griefed.filebrowser.model.FileNode;
import de.griefed.filebrowser.view.DirectoryPopMenu;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TableMouseListener extends DirectoryPopMenu {

  private final JTable TABLE;

  public TableMouseListener(JTable table, JTextField textField) {
    super(textField);
    TABLE = table;
  }

  @Override
  public void mousePressed(MouseEvent mouseEvent) {
    int r = TABLE.rowAtPoint(mouseEvent.getPoint());

    if (r >= 0 && r < TABLE.getRowCount()) {
      TABLE.setRowSelectionInterval(r, r);
    } else {
      TABLE.clearSelection();
    }

    int rowindex = TABLE.getSelectedRow();

    if (rowindex >= 0) {

      FileNode fileNode = (FileNode) TABLE.getModel().getValueAt(rowindex, 5);
      File file = fileNode.getFile();

      if (file.isDirectory()) {
        show(TABLE, mouseEvent.getX(), mouseEvent.getY(),file);
      }
    }
  }
}
