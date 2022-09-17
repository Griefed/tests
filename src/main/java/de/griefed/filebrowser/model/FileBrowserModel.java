package de.griefed.filebrowser.model;

import java.io.File;
import java.util.Enumeration;
import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class FileBrowserModel {

  private final RootManager ROOTMANAGER;

  public FileBrowserModel() {
    ROOTMANAGER = new RootManager();
  }

  public DefaultTreeModel createTreeModel() {
    DefaultMutableTreeNode root = ROOTMANAGER.getRoot();

    addChildNodes(root);
    addGrandchildNodes(root);

    return new DefaultTreeModel(root);
  }

  public void addGrandchildNodes(DefaultMutableTreeNode root) {

    Enumeration<?> enumeration = root.children();

    while (enumeration.hasMoreElements()) {

      DefaultMutableTreeNode node =
          (DefaultMutableTreeNode) enumeration.nextElement();
      addChildNodes(node);

    }
  }

  private void addChildNodes(DefaultMutableTreeNode root) {

    if (ROOTMANAGER.isWindows()) {
      FileNode fileNode = (FileNode) root.getUserObject();
      File file = fileNode.getFile();

      if (file.isDirectory()) {

        File[] files = file.listFiles();

        for (File child : files) {

          //if (Files.isReadable(child.toPath())) {
          root.add(new DefaultMutableTreeNode(
              new FileNode(child)));
          //}
        }
      }

    } else {

      Enumeration<?> enumeration = root.children();

      while (enumeration.hasMoreElements()) {

        DefaultMutableTreeNode node =
            (DefaultMutableTreeNode) enumeration.nextElement();

        FileNode fileNode = (FileNode) node.getUserObject();
        File file = fileNode.getFile();

        if (file.isDirectory()) {

          for (File child : file.listFiles()) {

            node.add(new DefaultMutableTreeNode(
                new FileNode(child)));

          }
        }
      }

    }

  }

  public RootManager getRootManager() {
    return ROOTMANAGER;
  }

  public Icon getFileIcon(File file) {
    return ROOTMANAGER.getFileSystemView().getSystemIcon(file);
  }

  public String getFileText(File file) {
    return ROOTMANAGER.getFileSystemView().getSystemDisplayName(file);
  }

}