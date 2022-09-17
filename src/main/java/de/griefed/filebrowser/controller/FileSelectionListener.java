package de.griefed.filebrowser.controller;

import de.griefed.filebrowser.model.FileBrowserModel;
import de.griefed.filebrowser.model.FileNode;
import de.griefed.filebrowser.runnable.AddNodes;
import de.griefed.filebrowser.view.FileBrowserFrame;
import java.io.File;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class FileSelectionListener implements TreeSelectionListener {

  private final FileBrowserFrame FRAME;

  private final FileBrowserModel MODEL;

  public FileSelectionListener(FileBrowserFrame frame,
      FileBrowserModel model) {
    this.FRAME = frame;
    this.MODEL = model;
  }

  @Override
  public void valueChanged(TreeSelectionEvent event) {
    DefaultMutableTreeNode node =
        (DefaultMutableTreeNode)
            event.getPath().getLastPathComponent();

    FileNode fileNode = (FileNode) node.getUserObject();

    AddNodes addNodes = new AddNodes(MODEL, node);
    new Thread(addNodes).start();

    File file = fileNode.getFile();
    FRAME.updateFileDetail(fileNode);
    FRAME.setDesktopButtonFileNode(fileNode);
    if (file.isDirectory()) {
      FRAME.setDefaultTableModel(node);
    } else {
      FRAME.clearDefaultTableModel();
    }
  }
}