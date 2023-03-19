package de.griefed.filebrowser.model;

import java.io.File;

public class FileNode {

    private final File PARENT;
    private boolean generateGrandchildren;

    public FileNode(File file) {
        this.PARENT = file;
        this.generateGrandchildren = true;
    }

    public File getFile() {
        return PARENT;
    }

    public boolean isGenerateGrandchildren() {
        return generateGrandchildren;
    }

    public void setGenerateGrandchildren(boolean generateGrandchildren) {
        this.generateGrandchildren = generateGrandchildren;
    }

    @Override
    public String toString() {

        String name = PARENT.getName();

        if (name.equals("")) {

            return PARENT.getAbsolutePath();

        } else {

            return name;
        }
    }
}
