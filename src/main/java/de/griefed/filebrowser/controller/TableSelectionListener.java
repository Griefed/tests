package de.griefed.filebrowser.controller;

import de.griefed.filebrowser.model.FileNode;
import de.griefed.filebrowser.view.FileBrowserFrame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TableSelectionListener implements ListSelectionListener {

    private final FileBrowserFrame FRAME;
    private final JTable TABLE;
    private int rowCount;

    public TableSelectionListener(FileBrowserFrame frame, JTable table) {
        this.FRAME = frame;
        this.TABLE = table;

    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    @Override
    public void valueChanged(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()) {

            ListSelectionModel lsm =
                    (ListSelectionModel) event.getSource();

            int row = lsm.getMinSelectionIndex();

            if ((row >= 0) && (row < rowCount)) {

                row = TABLE.convertRowIndexToModel(row);

                FileNode fileNode = (FileNode) TABLE.getModel()
                        .getValueAt(row, 5);

                FRAME.updateFileDetail(fileNode);
                FRAME.setDesktopButtonFileNode(fileNode);

            }
        }
    }

}