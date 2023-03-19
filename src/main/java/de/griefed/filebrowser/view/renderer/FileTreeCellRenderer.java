package de.griefed.filebrowser.view.renderer;

import de.griefed.filebrowser.model.FileBrowserModel;
import de.griefed.filebrowser.model.FileNode;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;
import java.io.File;

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

        return LABEL;
    }

}