package de.griefed.filebrowser.model;

import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.util.Locale;

public class RootManager {

    private final String OS_NAME = System.getProperty("os.name");
    private final FileSystemView FILESYSTEMVIEW;

    public RootManager() {
        FILESYSTEMVIEW = FileSystemView.getFileSystemView();
    }

    public DefaultMutableTreeNode getRoot() throws IllegalStateException {

        if (isWindows()) {
            return getMyComputer();
        }

        File[] roots = File.listRoots();

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();

        if (roots.length >= 1) {

            for (File root : roots) {
                rootNode.add(new DefaultMutableTreeNode(new FileNode(root)));
            }

        } else {
            throw new IllegalStateException("No roots available");
        }

        return rootNode;
    }

    private DefaultMutableTreeNode getMyComputer() throws IllegalStateException {
        for (File file : FILESYSTEMVIEW.getRoots()) {

            if (file.getName().equalsIgnoreCase("::{20D04FE0-3AEA-1069-A2D8-08002B30309D}")) {
                return new DefaultMutableTreeNode(new FileNode(file));
            }

            System.out.println(file.getName());
            if (file.isDirectory()) {

                for (File child : file.listFiles()) {

                    System.out.println(child.getName());
                    if (child.getName().equalsIgnoreCase("::{20D04FE0-3AEA-1069-A2D8-08002B30309D}")) {
                        return new DefaultMutableTreeNode(new FileNode(child));
                    }
                }

            }
        }
        throw new IllegalStateException("My Computer not available!");
    }

    public FileSystemView getFileSystemView() {
        return FILESYSTEMVIEW;
    }

    private boolean isOSX() {
        return OS_NAME.equalsIgnoreCase("Max OS X");
    }

    private boolean isLinux() {
        return OS_NAME.equalsIgnoreCase("Linux");
    }

    private boolean isSolaris() {
        return OS_NAME.equalsIgnoreCase("SunOS");
    }

    public boolean isWindows() {
        return !(isOSX() || isLinux() || isSolaris()) && OS_NAME.toLowerCase(Locale.ROOT).contains("windows");
    }

}
