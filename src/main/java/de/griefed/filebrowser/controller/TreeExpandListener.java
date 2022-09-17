package de.griefed.filebrowser.controller;

import de.griefed.filebrowser.model.FileBrowserModel;
import de.griefed.filebrowser.runnable.AddNodes;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

public class TreeExpandListener implements TreeWillExpandListener {

  private final FileBrowserModel MODEL;

  public TreeExpandListener(FileBrowserModel model) {
    this.MODEL = model;
  }

  @Override
  public void treeWillCollapse(TreeExpansionEvent event)
      throws ExpandVetoException {
  }

  @Override
  public void treeWillExpand(TreeExpansionEvent event)
      throws ExpandVetoException {
    TreePath path = event.getPath();
    DefaultMutableTreeNode node =
        (DefaultMutableTreeNode) path.getLastPathComponent();

    AddNodes addNodes = new AddNodes(MODEL, node);
    new Thread(addNodes).start();
  }

}