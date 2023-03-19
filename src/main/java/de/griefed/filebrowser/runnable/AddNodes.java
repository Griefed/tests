package de.griefed.filebrowser.runnable;

import de.griefed.filebrowser.model.FileBrowserModel;
import de.griefed.filebrowser.model.FileNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class AddNodes implements Runnable {

    private final DefaultMutableTreeNode NODE;

    private final FileBrowserModel MODEL;

    public AddNodes(FileBrowserModel model, DefaultMutableTreeNode node) {
        this.MODEL = model;
        this.NODE = node;
    }

    @Override
    public void run() {
        FileNode fileNode = (FileNode) NODE.getUserObject();
        if (fileNode.isGenerateGrandchildren()) {
            MODEL.addGrandchildNodes(NODE);
            fileNode.setGenerateGrandchildren(false);
        }
    }

}