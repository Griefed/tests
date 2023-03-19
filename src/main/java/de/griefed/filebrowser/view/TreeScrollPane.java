package de.griefed.filebrowser.view;

import de.griefed.filebrowser.controller.FileSelectionListener;
import de.griefed.filebrowser.controller.TreeExpandListener;
import de.griefed.filebrowser.controller.TreeMouseListener;
import de.griefed.filebrowser.model.FileBrowserModel;
import de.griefed.filebrowser.view.renderer.FileTreeCellRenderer;

import javax.swing.*;
import javax.swing.text.Position.Bias;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.io.File;

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
        tree = new JTree(MODEL.getTreeModel());
        tree.addTreeSelectionListener(
                new FileSelectionListener(FRAME, MODEL));
        tree.addTreeWillExpandListener(
                new TreeExpandListener(MODEL));
        tree.expandRow(1);
        tree.setRootVisible(true);
        tree.setCellRenderer(new FileTreeCellRenderer(MODEL));
        tree.setShowsRootHandles(true);
        tree.addMouseListener(new TreeMouseListener(tree, FIELD));

        scrollPane = new JScrollPane(tree);

        Dimension preferredSize = scrollPane.getPreferredSize();
        Dimension widePreferred = new Dimension(
                300, (int) preferredSize.getHeight());
        scrollPane.setPreferredSize(widePreferred);

        expandPaths();
    }

    private void expandPaths() {
        if (!FIELD.getText().isEmpty() && new File(FIELD.getText()).isDirectory()) {

            String[] prefixes = FIELD.getText().replace("\\", "/").split("/");
            TreePath path = null;

            for (String prefix : prefixes) {
                path = tree.getNextMatch(prefix, 0, Bias.Backward);
                tree.expandPath(path);
            }

            if (path != null) {
                tree.setSelectionPath(path);
            }
        }
    }

    public JTree getTree() {
        return tree;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

}