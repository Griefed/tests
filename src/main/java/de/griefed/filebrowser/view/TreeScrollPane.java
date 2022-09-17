package de.griefed.filebrowser.view;

import de.griefed.filebrowser.controller.FileSelectionListener;
import de.griefed.filebrowser.controller.TreeMouseListener;
import de.griefed.filebrowser.controller.TreeExpandListener;
import de.griefed.filebrowser.model.FileBrowserModel;
import de.griefed.filebrowser.view.renderer.FileTreeCellRenderer;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;

public class TreeScrollPane {

  private final FileBrowserFrame FRAME;

  private final FileBrowserModel MODEL;
  private final JTextField FIELD;

  private JScrollPane scrollPane;

  private JTree tree;

  public TreeScrollPane(
      FileBrowserFrame frame,
      FileBrowserModel model,
      JTextField textField) {

    FRAME = frame;
    MODEL = model;
    FIELD = textField;
    createPartControl();
  }

  private void createPartControl() {
    tree = new JTree(MODEL.createTreeModel());
    tree.addTreeSelectionListener(
        new FileSelectionListener(FRAME, MODEL));
    tree.addTreeWillExpandListener(
        new TreeExpandListener(MODEL));
    tree.expandRow(1);
    tree.setRootVisible(false);
    tree.setCellRenderer(new FileTreeCellRenderer(MODEL));
    tree.setShowsRootHandles(true);

    tree.addMouseListener(new TreeMouseListener(tree, FIELD));

    scrollPane = new JScrollPane(tree);

    Dimension preferredSize = scrollPane.getPreferredSize();
    Dimension widePreferred = new Dimension(
        300, (int) preferredSize.getHeight());
    scrollPane.setPreferredSize(widePreferred);
  }

  public JTree getTree() {
    return tree;
  }

  public JScrollPane getScrollPane() {
    return scrollPane;
  }

}