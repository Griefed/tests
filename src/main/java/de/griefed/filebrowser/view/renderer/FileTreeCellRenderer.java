package de.griefed.filebrowser.view.renderer;

import de.griefed.filebrowser.model.FileBrowserModel;
import de.griefed.filebrowser.model.FileNode;
import java.awt.Color;
import java.awt.Component;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

public class FileTreeCellRenderer implements TreeCellRenderer {

  private final FileBrowserModel MODEL;

  private final JLabel LABEL;

  public FileTreeCellRenderer(FileBrowserModel model) {
    this.MODEL = model;
    this.LABEL = new JLabel(" ");
    LABEL.setOpaque(true);
  }

  @Override
  public Component getTreeCellRendererComponent(
      JTree tree,
      Object value,
      boolean selected,
      boolean expanded,
      boolean leaf,
      int row,
      boolean hasFocus) {

    DefaultMutableTreeNode node =
        (DefaultMutableTreeNode) value;
    FileNode fileNode = (FileNode) node.getUserObject();
    if (fileNode != null) {
      File file = fileNode.getFile();
      LABEL.setIcon(MODEL.getFileIcon(file));
      LABEL.setText(MODEL.getFileText(file));
    } else {
      LABEL.setText(value.toString());
    }

    if (selected) {
      LABEL.setBackground(Color.BLUE);
      LABEL.setForeground(Color.WHITE);
    } else {
      LABEL.setBackground(Color.WHITE);
      LABEL.setForeground(Color.BLACK);
    }

    return LABEL;
  }

}