package de.griefed.filebrowser.view.renderer;

import javax.swing.table.DefaultTableCellRenderer;
import java.text.SimpleDateFormat;

public class DateRenderer extends DefaultTableCellRenderer {

    private final SimpleDateFormat FORMATTER;

    public DateRenderer() {
        String pattern = "dd MMM yyyy";
        this.FORMATTER = new SimpleDateFormat(pattern);
    }

    @Override
    protected void setValue(Object value) {
        setText((value == null) ? "" : FORMATTER.format(value));
    }

}