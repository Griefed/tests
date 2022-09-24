package de.griefed.filebrowser.controller;

import de.griefed.filebrowser.model.FileNode;
import de.griefed.filebrowser.view.SelectionPopMenu;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class TreeMouseListener extends SelectionPopMenu {

  private final JTree TREE;

  public TreeMouseListener(JTree tree, JTextField textField) {
    super(textField);
    TREE = tree;
  }

  @Override
  public void mousePressed(MouseEvent mouseEvent) {
    if (mouseEvent.getButton() == MouseEvent.BUTTON3) {

      if (TREE.getPathForLocation(mouseEvent.getX(), mouseEvent.getY()) != null) {

        TreePath treePath = TREE.getPathForLocation(mouseEvent.getX(), mouseEvent.getY());
        DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();
        FileNode fileNode = (FileNode) treeNode.getUserObject();
        File file = fileNode.getFile();

        show(TREE, mouseEvent.getX(), mouseEvent.getY(), file);
      }

    }
  }
}
