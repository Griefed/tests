package de.griefed.filebrowser.model;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.File;
import java.util.Enumeration;

public class FileBrowserModel {

    private final RootManager ROOTMANAGER;
    private final DefaultTreeModel TREEMODEL;

    public FileBrowserModel() {
        ROOTMANAGER = new RootManager();
        TREEMODEL = createTreeModel();
    }

    public void reload() {
        TREEMODEL.setRoot(updateRoot());
    }

    public DefaultTreeModel getTreeModel() {
        return TREEMODEL;
    }

    public DefaultTreeModel createTreeModel() {
        return new DefaultTreeModel(updateRoot());
    }

    private DefaultMutableTreeNode updateRoot() {
        DefaultMutableTreeNode root = ROOTMANAGER.getRoot();

        addChildNodes(root);
        addGrandchildNodes(root);

        return root;
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

                if (file != null && file.isDirectory()) {
                    try {
                        for (File child : file.listFiles()) {

                            node.add(new DefaultMutableTreeNode(
                                    new FileNode(child)));

                        }
                    } catch (NullPointerException npe) {
                        npe.printStackTrace();
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